package io.shapez.game;

import io.shapez.core.Signal;
import io.shapez.core.Vector;
import io.shapez.savegame.serialization.BasicSerializableObject;
import processing.data.JSONObject;

import java.io.File;

import static processing.core.PApplet.loadJSONObject;
import static processing.core.PApplet.min;

public class Camera extends BasicSerializableObject {
    JSONObject keymapping = loadJSONObject(new File("res\\keymap.json"));
    JSONObject config = loadJSONObject(new File("res\\config.json"));
    final int tileSize = config.getInt("tileSize");
    final int mapMoveUp = keymapping.getInt("navigation.mapMoveUp");
    final int mapMoveDown = keymapping.getInt("navigation.mapMoveDown");
    final int mapMoveRight = keymapping.getInt("navigation.mapMoveRight");
    final int mapMoveLeft = keymapping.getInt("navigation.mapMoveLeft");
    final int mapZoomIn = keymapping.getInt("navigation.mapZoomIn");
    final int mapZoomOut = keymapping.getInt("navigation.mapZoomOut");
    final int centerMap = keymapping.getInt("navigation.centerMap");
    GameRoot root;
    Integer zoomLevel = this.findInitialZoom();
    Vector center = new Vector(0, 0);
    boolean currentlyMoving = false;
    Vector lastMovingPosition = null;
    Vector lastMovingPositionLastTick = null;
    Integer numTicksStandingStill = null;
    double cameraUpdateTimeBucket = 0.0;
    boolean didMoveSinceTouchStart = false;
    boolean currentlyPinching = false;
    Vector lastPinchPositions = null;
    Vector keyboardForce = new Vector();
    Signal userInteraction = new Signal();
    Vector currentShake = new Vector(0, 0);
    Vector currentPan = new Vector(0, 0);
    Vector desiredPan = new Vector(0, 0);
    Vector desiredCenter = null;
    Double desiredZoom = null;
    Vector touchPostMoveVelocity = new Vector(0, 0);

    Camera(GameRoot root) {
        super();
        this.root = root;
        this.bindKeys();
    }

    private void bindKeys() {
        KeyActionMapper mapper = this.root.keyMapper;
        mapper.getBinding(mapMoveUp).add(this::negY);
        mapper.getBinding(mapMoveDown).add(this::posY);
        mapper.getBinding(mapMoveRight).add(this::posX);
        mapper.getBinding(mapMoveLeft).add(this::negX);
        mapper.getBinding(mapZoomIn).add(this::zoomIn);
        mapper.getBinding(mapZoomOut).add(this::zoomOut);
        mapper.getBinding(centerMap).add(this::centerOnMap);
    }

    private void centerOnMap() {
        this.desiredCenter = new Vector(0, 0);
    }

    int findInitialZoom() {
        int desiredWorldSpaceWidth = 15 * tileSize;
        int zoomLevelX = 1920 / desiredWorldSpaceWidth;
        int zoomLevelY = 1080 / desiredWorldSpaceWidth;
        return min(zoomLevelX, zoomLevelY);
    }

    public static String getId() {
        return null;
    }

    void negY() {
        this.keyboardForce.y = -1;
    }

    void posY() {
        this.keyboardForce.y = 1;
    }

    void negX() {
        this.keyboardForce.y = -1;
    }

    void posX() {
        this.keyboardForce.y = 1;
    }

    void zoomIn() {
        this.desiredZoom = this.zoomLevel * 1.2;
    }

    private void zoomOut() {
        this.desiredZoom = this.zoomLevel / 1.2;
    }
}
