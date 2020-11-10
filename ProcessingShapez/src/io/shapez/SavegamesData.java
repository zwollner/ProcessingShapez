package io.shapez;

import java.util.ArrayList;

public class SavegamesData {
    int version;
    ArrayList<SavegameMetadata> savegames = new ArrayList<>();

    SavegamesData(int version, ArrayList savegames) {

    }
}
