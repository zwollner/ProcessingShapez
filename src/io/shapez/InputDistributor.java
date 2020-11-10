package io.shapez;

import java.util.ArrayList;

public class InputDistributor {
    ArrayList<InputReceiver> receiverStack = new ArrayList<>();
    ArrayList<Boolean> filters = new ArrayList<>();
    Set keysDown;
    Application app;

    InputDistributor(Application app) {
        this.app = app;
        this.bindToEvents();
    }

    void bindToEvents() {

    }
}
