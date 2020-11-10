package io.shapez;

import java.util.function.Consumer;

public class AnimationFrame {
    Performance performance;
    Signal frameEmitted = new Signal();
    Signal bgFrameEmitted = new Signal();
    long lastTime = performance.now();
    int maxDtMs = 1000;
    int resetDtMs = 16;
    Consumer<Integer> boundMethod = Integer -> this.handleAnimationFrame(Integer);
    void handleAnimationFrame(long time) {
        int dt = (int) (time - this.lastTime);
        if (dt > maxDtMs) {
            dt = resetDtMs;
        }
        this.frameEmitted.dispatch();
        this.lastTime = time;

    }
}
