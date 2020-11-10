package io.shapez;

public class Application {
    ApplicationSettings settings = new ApplicationSettings(this);
    AnimationFrame ticker = new AnimationFrame();
    boolean unloaded = false;
    StateManager stateMgr = new StateManager(this);
    SavegameManager savegameMgr = new SavegameManager(this);
    InputDistributor inputMgr = new InputDistributor(this);
    BackgroundResourcesLoader backgroundResourceLoader = new BackgroundResourcesLoader();
    StorageInterface storage = null;
    SoundInterface sound = null;
    boolean visible = true;
    boolean applicationPaused = false;
    void boot() {
        System.out.println("Booting...");
        this.registerStates();
    }
    public void registerStates() {
        GameState[] states = {
                new PreloadState(),
                new MainMenuState(),
                new InGameState(),
//                new SettingsState(),
//                new KeybindingsState(),
//                new AboutState(),
//                new ChangelogState()
        };
    }
}
