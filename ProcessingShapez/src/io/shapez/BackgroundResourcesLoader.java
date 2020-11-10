package io.shapez;

import java.util.ArrayList;

public class BackgroundResourcesLoader {
    boolean registerReady = false;
    boolean mainMenuReady = false;
    boolean bareGameReady = false;
    boolean additionalReady = false;
    Signal signalMainMenuLoaded = new Signal();
    Signal signalBareGameLoaded = new Signal();
    Signal signalAdditionalLoaded = new Signal();
    int numAssetsLoaded = 0;
    int numAssetsToLoadTotal = 0;
    ArrayList<String> spritesLoaded = new ArrayList<>();
    ArrayList<String> soundsLoaded = new ArrayList<>();
}
