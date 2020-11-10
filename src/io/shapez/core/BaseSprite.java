package io.shapez.core;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class BaseSprite {
    abstract PImage getRawTexture();
    abstract void draw(PApplet context, int x, int y, int w, int h);
}
