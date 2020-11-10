package io.shapez.core;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.HashMap;

public class AtlasSprite extends BaseSprite {
    private final String spriteName;
    HashMap<String, SpriteAtlasLink> linksByResolution;
    public AtlasSprite(String spriteName) {
        super();
        this.spriteName = spriteName;
    }

    @Override
    PImage getRawTexture() {
        return this.linksByResolution.get("0.75").atlas;
    }

    @Override
    void draw(PApplet context, int x, int y, int w, int h) {
        SpriteAtlasLink link = this.linksByResolution.get("0.75");
        int scaleW = w / link.w;
        int scaleH = h / link.w;
        context.image(link.atlas, link.packedX, link.packedY);
    }
}
