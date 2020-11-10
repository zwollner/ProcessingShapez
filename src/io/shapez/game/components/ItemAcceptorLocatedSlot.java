package io.shapez.game.components;

public class ItemAcceptorLocatedSlot {
    public int index;
    ItemAcceptorSlot slot;
    String acceptedDirection;

    public ItemAcceptorLocatedSlot(ItemAcceptorSlot slot, int index, String acceptedDirection) {
        this.slot = slot;
        this.index = index;
        this.acceptedDirection = acceptedDirection;
    }
}
