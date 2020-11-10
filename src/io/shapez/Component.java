package io.shapez;

import io.shapez.savegame.serialization.BasicSerializableObject;

public abstract class Component extends BasicSerializableObject {
    public Component() {
        super();
    }

    public static String getId() {
        return "unknown-component";
    }
}
