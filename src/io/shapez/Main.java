package io.shapez;

import processing.core.PApplet;

public class Main extends PApplet {
    Application app = new Application(this);
    long tick = 0L;

    public void settings() {
//        fullScreen(P2D);
        fullScreen();
    }

    public void setup() {
        app.boot();
        surface.setResizable(true);
        surface.setTitle("Shapez.io");
    }

    public void draw() {
        thread("backgroundTick");
        app.ticker.handleAnimationFrame(tick);
        app.onFrameEmitted(System.nanoTime());
        app.onBackgroundFrame(System.nanoTime());
        this.backgroundTick();
    }

    public void backgroundTick() {
        app.ticker.handleBackgroundTick();
    }

    public static void main(String... args) {
        PApplet.main(new String[]{Main.class.getName()});
    }
}
