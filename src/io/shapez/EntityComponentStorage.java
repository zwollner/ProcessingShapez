package io.shapez;

import io.shapez.game.components.*;

public class EntityComponentStorage {
    public StaticMapEntityComponent StaticMapEntity;
    public BeltComponent Belt;
    public ItemEjectorComponent ItemEjector;
    public ItemAcceptorComponent ItemAcceptor;
    public MinerComponent Miner;
    public ItemProcessorComponent ItemProcessor;
    public UndergroundBeltComponent UndergroundBelt;
    public HubComponent Hub;
    public StorageComponent Storage;
    public WiredPinsComponent WiredPins;

    public boolean get(String requiredComponentId) {
        return true;
    }
}
