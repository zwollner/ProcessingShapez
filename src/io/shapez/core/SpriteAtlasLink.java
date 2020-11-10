package io.shapez.core;

import processing.core.PGraphics;

public class SpriteAtlasLink {
    int packedX;
    int packedY;
    int packedW;
    int packedH;
    int packOffsetX;
    int packOffsetY;
    PGraphics atlas;
    int w;
    int h;

    SpriteAtlasLink(int w, int h, int packedX, int packedY, int packOffsetX, int packOffsetY, int packedW, int packedH, PGraphics atlas) {
        this.packedX = packedX;
        this.packedY = packedY;
        this.packedW = packedW;
        this.packedH = packedH;
        this.packOffsetX = packOffsetX;
        this.packOffsetY = packOffsetY;
        this.atlas = atlas;
        this.w = w;
        this.h = h;
    }
}
