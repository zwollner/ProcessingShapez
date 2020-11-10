package io.shapez.savegame;

import java.util.ArrayList;

public class SavegamesData {
    public int version;
    ArrayList<SavegameMetadata> savegames = new ArrayList<>();

    SavegamesData(int version, ArrayList savegames) {

    }

    public SavegamesData(int version, boolean savegameV1119Imported) {

    }
}
