package io.shapez;

public interface Performance extends EventTarget {
    PerformanceNavigation navigation = null;
    Object onresourcetimingbufferfull(Performance this, Event ev);
    int timeOrigin = 0;
    @Deprecated
    PerformanceTiming timing = null;
    void clearMarks(String markName);
    void clearMeasures(String measureName);
    void clearResourceTimings();
    PerformanceEntry[] getEntries();
    PerformanceEntry[] getEntriesByName(String name, String type);
    PerformanceEntry[] getEntriesByType(String type);
    void mark(String markName);
    void measure(String measureName, String startMark, String endMark);
    int now();
    void setResourceTimingBufferSize(int maxSize);
    Object toJSON();
}
