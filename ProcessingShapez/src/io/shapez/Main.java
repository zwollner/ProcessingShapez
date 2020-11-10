package io.shapez;

import processing.core.PApplet;

public class Main extends PApplet {
    Application app;
    long tick = 0l;
    public void bootApp() {
        app = new Application();
        app.boot();
    }

    public void settings() {
        fullScreen();
    }

    public void setup() {
        bootApp();
    }

    public void draw() {

        app.ticker.handleAnimationFrame(tick);
    }

    public static void main(String... args) {
        PApplet.main(new String[]{Main.class.getName()});
    }
}
