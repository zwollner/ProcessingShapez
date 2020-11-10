package io.shapez;

import java.util.HashMap;

public class SoundInterface {
    String soundClass;
    String musicClass;
    HashMap<String, SoundInstanceInterface> sounds = new HashMap<>();
    HashMap<String, MusicInstanceInterface> music = new HashMap<>();
    MusicInstanceInterface currentMusic = null;
    boolean isVisible = true;
    float musicVolume = 1.0f;
    float soundVolume = 1.0f;

    SoundInterface(String soundClass, String musicClass) {
        this.soundClass = soundClass;
        this.musicClass = musicClass;
    }
}
