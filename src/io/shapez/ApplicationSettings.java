package io.shapez;

public class ApplicationSettings extends ReadWriteProxy {
    ApplicationSettings(Application app) {
        super("app_settings.bin", app);
    }
//    ApplicationSettings getKeybindingOverrides() {
//        return this.getAllSettings().keybindingOverrides;
//    }
//    String getAllSettings() {
//        return this.currentData.settings;
//    }
}
