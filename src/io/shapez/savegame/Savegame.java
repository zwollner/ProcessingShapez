package io.shapez.savegame;

import io.shapez.Application;
import io.shapez.core.ExplainedResult;
import io.shapez.core.ReadWriteProxy;

public class Savegame extends ReadWriteProxy {
    String internalId;
    SavegameMetadata metaDataRef;

    SavegameData currentData = this.getDefaultData();
    Savegame(Application app, String internalId, SavegameMetadata metaDataRef) {
        super(app, "savegame-"+internalId+".bin");
        this.internalId = internalId;
        this.metaDataRef = metaDataRef;
    }

    SavegameData getDefaultData() {
        return new SavegameData();
    }

    @Override
    protected ExplainedResult verify(Object data) {
        return null;
    }

    @Override
    public int getCurrentVersion() {
        return 0;
    }
}
