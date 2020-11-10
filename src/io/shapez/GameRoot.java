package io.shapez;

public class GameRoot {
    Application app;
    Savegame savegame = null;
    InGameState gameState = null;
    KeyActionMapper keyMapper = null;
    GameRoot(Application app) {
        this.app = app;
    }
}
