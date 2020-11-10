package io.shapez.game;

import io.shapez.savegame.serialization.BasicSerializableObject;
import processing.data.JSONObject;

import java.io.File;
import java.util.HashMap;

import static processing.core.PApplet.loadJSONObject;

public class BaseMap extends BasicSerializableObject {
    private final GameRoot root;
    int seed = 0;
    HashMap<String, MapChunkView> chunksById = new HashMap<>();
    JSONObject config = loadJSONObject(new File("\\res\\JSON\\config"));
    int mapChunkSize = config.getInt("globalConfig.tileSize");

    BaseMap(GameRoot root) {
        this.root = root;
    }

    public static String getId() {
        return null;
    }

    public Entity getLayerContentXY(float x, float y, String layer) {
        MapChunkView chunk = this.getChunkAtTileOrNull(x, y);
        return chunk.getLayerContentFromWorldCoords(x, y, layer);
    }

    private MapChunkView getChunkAtTileOrNull(float tileX, float tileY) {
        int chunkX = (int) Math.floor(tileX / mapChunkSize);
        int chunkY = (int) Math.floor(tileY / mapChunkSize);
        return this.getChunk(chunkX, chunkY, false);
    }

    private MapChunkView getChunk(int chunkX, int chunkY, boolean createIfNotExistent) {
        String chunkIdentifier = chunkX + "|" + chunkY;
        MapChunkView storedChunk = null;
//        if ((storedChunk == this.chunksById.get(chunkIdentifier))) {
//            return storedChunk;
//        }
        if (createIfNotExistent) {
            MapChunkView instance = new MapChunkView(this.root, chunkX, chunkY);
            this.chunksById.put(chunkIdentifier, instance);
            return instance;
        }
        return null;
    }
}
