package io.shapez.game;

public class AutomaticSave {
    private final GameRoot root;
    int saveImportance = 2;
    int lastSaveAttempt = -1000;

    public AutomaticSave(GameRoot root) {
        this.root = root;
    }
}
