package io.shapez.game;

import processing.core.PApplet;
import processing.data.JSONObject;

import java.io.File;

import static processing.core.PApplet.loadJSONObject;

public class MapView extends BaseMap {
    private final PApplet context;
    int backgroundCacheDPI = 2;
    JSONObject config = loadJSONObject(new File("res\\JSON\\config.json"));
    int tileSize = config.getInt("globalConfig.tileSize");
    JSONObject theme = loadJSONObject(new File("res\\JSON\\themes\\light.json"));
    int background = theme.getInt("background");
    int gridLineWidth = theme.getInt("map.gridLineWidth");
    int grid = theme.getInt("map.grid");

    MapView(GameRoot root, PApplet context) {
        super(root);
        this.context = context;
        this.internalInitializeCachedBackgroundCanvases();
    }

    void internalInitializeCachedBackgroundCanvases() {
        int dims = tileSize;
        int dpi = this.backgroundCacheDPI;
        context.scale(dpi, dpi);
        context.fill(background);
        context.rect(0, 0, dims, dims);
        int borderWidth = gridLineWidth;
        context.fill(grid);
        context.rect(0, 0, dims, borderWidth);
        context.rect(0, borderWidth, borderWidth, dims);
        context.rect(dims - borderWidth, borderWidth, borderWidth, dims - 2 * borderWidth);
        context.rect(borderWidth, dims - borderWidth, dims, borderWidth);

    }
}
