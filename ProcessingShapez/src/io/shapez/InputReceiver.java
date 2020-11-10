package io.shapez;

public class InputReceiver {
    String context = "unknown";
    Signal backButton = new Signal();
    Signal keydown = new Signal();
    Signal keyup = new Signal();
    Signal pageBlur = new Signal();
    Signal destroyed = new Signal();

    InputReceiver(String context) {
        this.context = context;
    }
}
