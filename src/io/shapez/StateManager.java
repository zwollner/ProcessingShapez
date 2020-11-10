package io.shapez;

import java.util.HashMap;

public class StateManager {
    HashMap<String, GameState> stateClasses = new HashMap<>();
    GameState currentState = null;
    Application app;
    StateManager(Application app) {
        this.app = app;
    }
}
