package io.shapez;

public class Keybinding {
    KeyActionMapper keyMapper;
    int keyCode;
    boolean builtin;
    boolean repeated;
    Signal signal = new Signal();
    Signal toggled = new Signal();
    Keybinding(KeyActionMapper keyMapper, int keyCode, boolean builtin, boolean repeated) {
        this.keyMapper = keyMapper;
        this.keyCode = keyCode;
        this.builtin = builtin;
        this.repeated = repeated;
    }
}
