package io.shapez;

public interface PerformanceNavigation {
    int redirectCount = 0;
    int type = 0;
    Object toJSON();
    final int TYPE_BACK_FORWARD = 0;
    final int TYPE_NAVIGATE = 0;
    final int TYPE_RELOAD = 0;
    final int TYPE_RESERVED = 0;
}
