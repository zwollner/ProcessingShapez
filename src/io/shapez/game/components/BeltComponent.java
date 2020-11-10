package io.shapez.game.components;

import io.shapez.Component;
import io.shapez.game.BeltPath;

public class BeltComponent extends Component {
    public final String direction;
    BeltPath assignedPath = null;

    public BeltComponent(String direction) {
        super();
        this.direction = direction;
    }

    public static String getId() {
        return "Belt";
    }
}
