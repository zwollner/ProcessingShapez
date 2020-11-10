package io.shapez;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class KeyActionMapper {
    GameRoot root;
    InputReceiver inputReceiver;
    HashMap<String, Keybinding> keybindings;
//    ApplicationSettings overrides = root.app.settings.getKeybindingOverrides();

    KeyActionMapper(GameRoot root, InputReceiver inputReceiver) {
        this.root = root;
        this.inputReceiver = inputReceiver;
//        Consumer handleKeydown = () -> handleKeydown();
//        inputReceiver.keydown.add(handleKeydown);
//        inputReceiver.keydown.add(handleKeyup);
    }

    private void handleKeydown(int keyCode, boolean initial) {
        boolean stop = false;
        for (Map.Entry key : this.keybindings.entrySet()) {
            Keybinding binding = this.keybindings.get(key.getKey());
            if (binding.keyCode == keyCode && initial || binding.repeated) {
                Signal signal = this.keybindings.get(key.getKey()).signal;
                if (signal.dispatch() == false) {
                    return;
                }
            }
        }
        if (stop) {
            return;
        }
    }

    void handleKeyup() {

    }
}
