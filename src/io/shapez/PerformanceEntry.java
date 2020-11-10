package io.shapez;

public interface PerformanceEntry {
    int duration = 0;
    String entryType = null;
    String name = null;
    int startTime = 0;
    Object toJSON();
}
