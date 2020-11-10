package io.shapez.game;

import io.shapez.ColorItem;
import io.shapez.Patches;
import io.shapez.Record;
import io.shapez.Rectangle;
import io.shapez.core.RandomNumberGenerator;
import io.shapez.core.Vector;
import io.shapez.game.items.ShapeItem;
import processing.data.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import static processing.core.PApplet.*;

public class MapChunk {
    final GameRoot root;
    private final HashMap<String, ColorItem> color_item_singletons = new HashMap<>();
    String[] enumColours = {"red", "green", "blue", "yellow", "purple", "cyan", "white", "uncoloured"};
    int x;
    int y;
    int tileX;
    int tileY;
    JSONObject json = loadJSONObject(new File("res\\config.json"));
    int mapChunkSize = json.getInt("globalConfig.mapChunkSize");
    int tileSize = json.getInt("globalConfig.tileSize");
    Integer mapChunkWorldSize = (Integer) json.get("globalConfig.mapChunkWorldSize");

    BaseItem[][] lowerLayer = new BaseItem[mapChunkSize][mapChunkSize];
    Entity[][] contents = new Entity[mapChunkSize][mapChunkSize];
    Entity[][] wireContents = new Entity[mapChunkSize][mapChunkSize];
    ArrayList<Entity> containedEntities = new ArrayList<>();
    Rectangle worldSpaceRectangle = new Rectangle(
            this.tileX * tileSize,
            this.tileY * tileSize,
            mapChunkWorldSize,
            mapChunkWorldSize
    );
    Rectangle tileSpaceRectangle = new Rectangle(
            this.tileX,
            this.tileY,
            mapChunkSize,
            mapChunkSize
    );
    Record<String, Entity[]> containedEntitiesByLayer;
    ArrayList<Patches> patches = new ArrayList<>();

    MapChunk(GameRoot root, int x, int y) {
        this.root = root;
        this.x = x;
        this.y = y;
        this.tileX = x * mapChunkSize;
        this.tileY = y * mapChunkSize;
        for (String i : enumColours) {
            color_item_singletons.put(i, new ColorItem(i));
        }
        this.generateLowerLayer();
    }

    private void generateLowerLayer() {
        RandomNumberGenerator rng = new RandomNumberGenerator(this.x + "|" + this.y + "|" + this.root.map.seed);

        if (this.generatePredefined(rng)) {
            return;
        }
        Vector chunkCenter = new Vector(this.x, this.y).addScalar(.5f);
        int distanceToOriginInChunks = round(chunkCenter.length());

        float colourPatchChance = (float) (0.9 - distanceToOriginInChunks / 25 * 0.5);
        if (rng.next() < colourPatchChance / 4) {
            int colorPatchSize = Math.max(2, Math.round(1 + distanceToOriginInChunks));
            this.internalGenerateColorPatch(rng, colorPatchSize, distanceToOriginInChunks);
        }
    }

    private void internalGenerateColorPatch(RandomNumberGenerator rng, int colorPatchSize, int distanceToOriginInChunks) {
        ArrayList<String> availableColours = new ArrayList<>() {{
            add("red");
            add("green");
        }};
        if (distanceToOriginInChunks > 2) {
            availableColours.add("blue");
        }
        this.internalGeneratePatch(rng, colorPatchSize, new ColorItem((String) rng.choice(availableColours.toArray())));
    }

    private boolean generatePredefined(RandomNumberGenerator rng) {
        if (this.x == 0 && this.y == 0) {
            this.internalGeneratePatch(rng, 2, color_item_singletons.get("red"), 7, 7);
            return true;
        }
        if (this.x == -1 && this.y == 0) {
            ShapeItem item = new ShapeItem(this.root.shapeDefinitionMgr.getShapeItemFromShortKey("CuCuCuCu"));
            this.internalGeneratePatch(rng, 2, item, mapChunkSize - 9, 7);
            return true;
        }
        if (this.x == 0 && this.y == -1) {
            ShapeItem item = new ShapeItem(this.root.shapeDefinitionMgr.getShapeItemFromShortKey("RuRuRuRu"));
            this.internalGeneratePatch(rng, 2, item, mapChunkSize - 9, 7);
            return true;
        }
        if (this.x == -1 && this.y == -1) {
            this.internalGeneratePatch(rng, 2, color_item_singletons.get("green"));
            return true;
        }
        if (this.x == 5 && this.y == -2) {
            ShapeItem item = new ShapeItem(this.root.shapeDefinitionMgr.getShapeItemFromShortKey("SuSuSuSu"));
            this.internalGeneratePatch(rng, 2, item, 5, mapChunkSize - 7);
            return true;
        }
        return false;
    }

    private void internalGeneratePatch(RandomNumberGenerator rng, int patchSize, BaseItem item, Integer overrideX, Integer overrideY) {
        int border = ceil(patchSize / 2 + 3);

        int patchX = rng.nextIntRange(border, mapChunkSize - border - 1);
        int patchY = rng.nextIntRange(border, mapChunkSize - border - 1);

        if (overrideX != null) {
            patchX = overrideX;
        }

        if (overrideY != null) {
            patchY = overrideY;
        }

        Vector avgPos = new Vector(0, 0);
        int patchesDrawn = 0;

        for (int i = 0; i <= patchSize; ++i) {
            int circleRadius = min(1 + i, patchSize);
            int circleRadiusSquare = circleRadius * circleRadius;
            int circleOffsetRadius = (patchSize - i) / 2 + 2;

            float circleScaleX = rng.nextRange(0.9, 1.1);
            float circleScaleY = rng.nextRange(0.9, 1.1);

            int circleX = patchX + rng.nextIntRange(-circleOffsetRadius, circleOffsetRadius);
            int circleY = patchY + rng.nextIntRange(-circleOffsetRadius, circleOffsetRadius);

            for (float dx = (-circleRadius * circleScaleX - 2); dx <= circleRadius * circleScaleX + 2; ++dx) {
                for (float dy = (-circleRadius * circleScaleY - 2); dy <= circleRadius * circleScaleY + 2; ++dy) {
                    int x = round(circleX + dx);
                    int y = round(circleY + dy);
                    if (x >= 0 && x < mapChunkSize && y >= 0 && y <= mapChunkSize) {
                        float originalDx = dx / circleScaleX;
                        float originalDy = dy / circleScaleY;
                        if (originalDx * originalDx + originalDy * originalDy <= circleRadiusSquare) {
                            if (this.lowerLayer[x][y] != null) {
                                this.lowerLayer[x][y] = item;
                                ++patchesDrawn;
                                avgPos.x += x;
                                avgPos.y += y;
                            }
                        }
                    }
                }
            }
        }
        this.patches.add(new Patches(avgPos.divideScalar(patchesDrawn), item, patchSize));
    }

    private void internalGeneratePatch(RandomNumberGenerator rng, int patchSize, BaseItem item) {
        int border = ceil(patchSize / 2 + 3);

        int patchX = rng.nextIntRange(border, mapChunkSize - border - 1);
        int patchY = rng.nextIntRange(border, mapChunkSize - border - 1);

        Vector avgPos = new Vector(0, 0);
        int patchesDrawn = 0;

        for (int i = 0; i <= patchSize; ++i) {
            int circleRadius = min(1 + i, patchSize);
            int circleRadiusSquare = circleRadius * circleRadius;
            int circleOffsetRadius = (patchSize - i) / 2 + 2;

            float circleScaleX = rng.nextRange(0.9, 1.1);
            float circleScaleY = rng.nextRange(0.9, 1.1);

            int circleX = patchX + rng.nextIntRange(-circleOffsetRadius, circleOffsetRadius);
            int circleY = patchY + rng.nextIntRange(-circleOffsetRadius, circleOffsetRadius);

            for (float dx = (-circleRadius * circleScaleX - 2); dx <= circleRadius * circleScaleX + 2; ++dx) {
                for (float dy = (-circleRadius * circleScaleY - 2); dy <= circleRadius * circleScaleY + 2; ++dy) {
                    int x = round(circleX + dx);
                    int y = round(circleY + dy);
                    if (x >= 0 && x < mapChunkSize && y >= 0 && y <= mapChunkSize) {
                        float originalDx = dx / circleScaleX;
                        float originalDy = dy / circleScaleY;
                        if (originalDx * originalDx + originalDy * originalDy <= circleRadiusSquare) {
                            if (this.lowerLayer[x][y] != null) {
                                this.lowerLayer[x][y] = item;
                                ++patchesDrawn;
                                avgPos.x += x;
                                avgPos.y += y;
                            }
                        }
                    }
                }
            }
        }
        this.patches.add(new Patches(avgPos.divideScalar(patchesDrawn), item, patchSize));
    }

    public Entity getLayerContentFromWorldCoords(float worldX, float worldY, String layer) {
        float localX = worldX - this.tileX;
        float localY = worldY - this.tileY;
        if (layer.equals("regular")) {
            return this.contents[(int) localX][(int) localY];
        } else {
            return this.wireContents[(int) localX][(int) localY];
        }
    }
}
