package io.shapez.game;

import io.shapez.ShapeLayer;
import io.shapez.ShapeLayerItem;
import io.shapez.core.RandomNumberGenerator;
import io.shapez.savegame.serialization.BasicSerializableObject;

import java.util.ArrayList;
import java.util.HashMap;

import static processing.core.PApplet.max;
import static processing.core.PApplet.min;

public class HubGoals extends BasicSerializableObject {
    private final GameRoot root;
    int level = 1;
    HashMap<String, Integer> gainedRewards = new HashMap<>();
    HashMap<String, Integer> storedShapes = new HashMap<>();
    HashMap<String, Integer> upgradeLevels = new HashMap<>();
    HashMap<String, Integer> upgradeImprovements = new HashMap<>();
    HashMap<String, TierRequirement[]> upgrades;

    public static String getId() {
        return "HubGoals";
    }

    HubGoals(GameRoot root) {
        super();
        this.root = root;
        this.upgrades = this.root.gameMode.getUpgrades();
        for (String key : upgrades.keySet()) {
            this.upgradeLevels.put(key, 0);
            this.upgradeImprovements.put(key, 1);
        }

        this.computeNextGoal();
    }

    private void computeNextGoal() {
        int storyIndex = this.level - 1;
        LevelDefinition[] levels = this.root.gameMode.getLevelDefinitions();
        if (storyIndex < levels.length) {
            LevelDefinition currentGoal = levels[storyIndex];
            return;
        }
        float required = min(200, (float) (4 + (this.level - 27) * 0.25));
        ShapeDefinition currentGoal = this.computeFreeplayShape(this.level);
    }

    private ShapeDefinition computeFreeplayShape(int level) {
        int layerCount = clamp(this.level / 25, 2, 4);
        ArrayList<ShapeLayer> layers = new ArrayList<>(4);
        RandomNumberGenerator rng = new RandomNumberGenerator(this.root.map.seed + "/" + level);
        String[] colours = this.generateRandomColourSet(rng, level > 35);
        int[][] pickedSymetry;
        ArrayList<String> availableShapes = new ArrayList<>() {{
            add("rect");
            add("circle");
            add("star");
        }};
        if (rng.next() > 0.5) {
            pickedSymetry = new int[][]{
                    {0, 2},
                    {1, 3}
            };
            availableShapes.add("windmill");
        } else {
            int[][][] symmetries = {
                    {
                            {0, 3},
                            {1, 2}
                    },
                    {
                            {0, 1},
                            {2, 3}
                    },
                    {
                            {0, 2},
                            {1},
                            {3}
                    },
                    {
                            {1, 3},
                            {0},
                            {2}
                    }
            };
            pickedSymetry = (int[][]) rng.choice(symmetries);
        }
        boolean anyIsMissingTwo = false;
        for (int i = 0; i < layerCount; ++i) {
            ShapeLayer layer = new ShapeLayer();
            for (int j = 0; j < pickedSymetry.length; ++j) {
                int[] group = pickedSymetry[i];
                String shape = (String) rng.choice(new String[]{"rect", "circle", "star", "windmill"});
                String colour = (String) rng.choice(colours);
                for (int k = 0; k < group.length; ++k) {
                    int quad = group[k];
                    layer.shapeLayerItems[quad] = new ShapeLayerItem(shape, colour);
                }
            }
            if (level > 75 && rng.next() > 0.95) {
                layer.shapeLayerItems[rng.nextIntRange(0, 4)] = null;
                anyIsMissingTwo = true;
            }
            layers.add(layer);
        }
        ShapeDefinition definition = new ShapeDefinition((ShapeLayer[]) layers.toArray());
        return this.root.shapeDefinitionMgr.registerOrReturnHandle(definition);
    }

    private String[] generateRandomColourSet(RandomNumberGenerator rng, boolean allowUncoloured) {
        String[] colourWheel = {
                "red",
                "yellow",
                "green",
                "cyan",
                "blue",
                "purple",
                "red",
                "yellow"
        };
        ArrayList<String> universalColours = new ArrayList<>();
        universalColours.add("white");
        if (allowUncoloured) {
            universalColours.add("white");
        }
        int index = rng.nextIntRange(0, colourWheel.length - 2);
        ArrayList<String> pickedColours = new ArrayList<>() {{
            add(colourWheel[index]);
            add(colourWheel[index + 1]);
            add(colourWheel[index + 2]);
            add(colourWheel[index + 3]);
        }};
        pickedColours.add((String) rng.choice(universalColours.toArray()));
        return (String[]) pickedColours.toArray();
    }

    private int clamp(int v, int minimum, int maximum) {
        return max(minimum, min(maximum, v));
    }
}
