package io.shapez;

import java.util.ArrayList;

public class SavegameManager extends ReadWriteProxy {
    SavegamesData currentData = this.getDefaultData();

    SavegameManager(Application app) {
        super("savegames.bin", app);
    }

    SavegamesData getDefaultData() {
        return new SavegamesData(this.getCurrentVersion(), new ArrayList());
    }

    int getCurrentVersion() {
        return 1002;
    }
}
