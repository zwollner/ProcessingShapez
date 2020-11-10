package io.shapez.game.components;

import io.shapez.Component;
import io.shapez.core.Vector;

public class StaticMapEntityComponent extends Component {
    private final Vector origin;
    private final int rotation;
    private final int code;
    private final int originalRotation;

    public static String getId() {
        return "StaticMapEntity";
    }
    public StaticMapEntityComponent(Vector origin,/* Vector tileSize,*/ int rotation, int originalRotation, int code) {
        super();
        this.origin = origin;
        this.rotation = rotation;
        this.code = code;
        this.originalRotation = originalRotation;
    }

    public Vector localTileToWorld(Vector localTile) {
        Vector result = localTile.rotateFastMultipleOf90(this.rotation);
        result.x += this.origin.x;
        result.y += this.origin.y;
        return result;
    }

    public String localDirectionToWorld(String direction) {
        return Vector.transformDirectionFromMultipleOf90(direction, this.rotation);
    }

    public String worldDirectionToLocal(String direction) {
        return Vector.transformDirectionFromMultipleOf90(direction, 360 - this.rotation);
    }

    public Vector worldToLocalTile(Vector worldTile) {
        Vector localUnrotated = worldTile.sub(this.origin);
        return this.unapplyRotationToVector(localUnrotated);
    }

    private Vector unapplyRotationToVector(Vector vector) {
        return vector.rotateFastMultipleOf90(360 - this.rotation);
    }
}
