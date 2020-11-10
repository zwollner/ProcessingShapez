package io.shapez.core;

import io.shapez.Application;
import processing.core.PConstants;
import processing.core.PGraphics;

import java.util.ArrayList;
import java.util.HashMap;

public class LoaderImpl {
    Application app = null;
    HashMap<String, BaseSprite> sprites = new HashMap<>();
    ArrayList<Object> rawImages = new ArrayList<>();
    private AtlasSprite spriteNotFoundSprite;


    public void linkAppAfterBoot(Application app) {
        this.app = app;
//        this.makeSpriteNotFoundCanvas();
    }

    private void makeSpriteNotFoundCanvas() {
        int dims = 128;
        PGraphics context = new PGraphics();
        context.setSize(dims, dims);
        context.fill(0xf77);
        context.rect(0, 0, dims, dims);
        context.textAlign(PConstants.CENTER);
        context.fill(0xeee);
        context.textFont(app.context.createFont("Arial", 32));
        context.text("???", dims / 2, dims / 2);
        AtlasSprite sprite = new AtlasSprite("not-found");
        for(String resolution : new String[]{"0.1", "0.25", "0.5", "0.75", "1"}) {
            sprite.linksByResolution.put(resolution, new SpriteAtlasLink(dims, dims, 0, 0, 0, 0, dims, dims, context));
        }
        this.spriteNotFoundSprite = sprite;
    }
}
