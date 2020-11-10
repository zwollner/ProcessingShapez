package io.shapez;

import java.util.ArrayList;

public abstract class GameState {
    String key;
    boolean fadingOut;
    StateManager stateManager = null;
    ArrayList<ClickDetector> clickDetectors = new ArrayList<>();
    InputReceiver inputReceiver;
    RequestChannel asyncChannel  = new RequestChannel();

    GameState(String key) {
        this.key = key;
        inputReceiver = new InputReceiver("state-" + key);
        Runnable onBackButton = () -> onBackButton();
        inputReceiver.backButton.add(onBackButton);
    }

    void onBackButton() { }
}
