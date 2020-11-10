package io.shapez.core;

import io.shapez.Application;
import io.shapez.savegame.SavegamesData;

public class RestrictionManager extends ReadWriteProxy {
    SavegamesData currentData = this.getDefaultData();

    private SavegamesData getDefaultData() {
        return new SavegamesData(this.getCurrentVersion(), false);
    }

    @Override
    protected ExplainedResult verify(Object data) {
        return null;
    }

    public int getCurrentVersion() {
        return 1;
    }

    public RestrictionManager(Application app) {
        super(app, "restriction-flags.bin");
    }

    public void initialize() {
        this.readAsync();
    }
}
