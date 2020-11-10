package io.shapez.game;

import io.shapez.core.Vector;
import io.shapez.game.components.BeltComponent;
import io.shapez.game.components.ItemAcceptorComponent;
import io.shapez.game.components.StaticMapEntityComponent;
import io.shapez.savegame.serialization.BasicSerializableObject;

import java.util.HashMap;

public class BeltPath extends BasicSerializableObject {
    private final GameRoot root;
    private final Entity[] entityPath;
    HashMap<Integer, BaseItem> items = new HashMap<>();
    Object acceptorTarget;

    public static String getId() {
        return "BeltPath";
    }

    BeltPath(GameRoot root, Entity[] entityPath) {
        super();
        this.root = root;
        this.entityPath = entityPath;
        this.init();
    }

    private void init() {
        this.onPathChanged();
    }

    private void onPathChanged() {
        this.acceptorTarget = this.computeAcceptingEntityAndSlot();
    }

    private EntityAndSlot computeAcceptingEntityAndSlot() {
        Entity lastEntity = this.entityPath[this.entityPath.length - 1];
        StaticMapEntityComponent lastStatic = lastEntity.components.StaticMapEntity;
        BeltComponent lastBeltComp = lastEntity.components.Belt;
        Vector ejectSlotsWsTile = lastStatic.localTileToWorld(new Vector(0, 0));
        String ejectSlotWsDirection = lastStatic.localDirectionToWorld(lastBeltComp.direction);
        Vector ejectSlotWsDirectionVector = enumLocalDirectionToVector(ejectSlotWsDirection);
        Vector ejectSlotTargetWsTile = ejectSlotsWsTile.add(ejectSlotWsDirectionVector);
        Entity targetEntity = this.root.map.getLayerContentXY(ejectSlotTargetWsTile.x, ejectSlotsWsTile.y, "regular");
        if (targetEntity != null) {
            StaticMapEntityComponent targetStaticComp = targetEntity.components.StaticMapEntity;
            BeltComponent targetBeltComp = targetEntity.components.Belt;
            if (targetBeltComp != null) {
                String beltAcceptingDirection = targetStaticComp.localDirectionToWorld("top");
                if (ejectSlotWsDirection.equals(beltAcceptingDirection)) {
                    return new EntityAndSlot(targetEntity, 0, null);
                }
            }
            ItemAcceptorComponent targetAcceptorComp = targetEntity.components.ItemAcceptor;
            if (targetAcceptorComp == null) {
                return null;
            }
            String ejectingDirection = targetStaticComp.worldDirectionToLocal(ejectSlotWsDirection);
            io.shapez.game.components.ItemAcceptorLocatedSlot matchingSlot = targetAcceptorComp.findMatchingSlot(
                    targetStaticComp.worldToLocalTile(ejectSlotTargetWsTile),
                    ejectingDirection
            );
            if (matchingSlot == null) {
                return null;
            }
            return new EntityAndSlot(targetEntity, matchingSlot.index, targetAcceptorComp.invertDirection(ejectingDirection));
        }
        return null;
    }

    private Vector enumLocalDirectionToVector(String ejectSlotWsDirection) {
        switch (ejectSlotWsDirection) {
            case "top":
                return new Vector(0, -1);
            case "right":
                return new Vector(1, 0);
            case "bottom":
                return new Vector(0, 1);
            case "left":
                return new Vector(-1, 0);
            default:
                return new Vector();
        }
    }
}

class EntityAndSlot {
    private final int slot;
    private final String direction;
    Entity entity;

    public EntityAndSlot(Entity entity, int slot, String direction) {
        this.slot = slot;
        this.direction = direction;
        this.entity = entity;
    }
}