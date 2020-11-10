package io.shapez;

public class Savegame extends ReadWriteProxy {
    String internalId;
    SavegameMetadata metaDataRef;

    SavegameData currentData = this.getDefaultData();
    Savegame(Application app, String internalId, SavegameMetadata metaDataRef) {
        super("savegame-"+internalId+".bin", app);
        this.internalId = internalId;
        this.metaDataRef = metaDataRef;
    }

    SavegameData getDefaultData() {
        return new SavegameData();
    }
}
