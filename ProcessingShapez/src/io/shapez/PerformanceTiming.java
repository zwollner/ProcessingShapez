package io.shapez;

public interface PerformanceTiming {
    int connectEnd = 0;
    int ConnectStart = 0;
    int domComplete = 0;
    int domContentLoadedEventEnd = 0;
    int domContentLoadedEventStart = 0;
    int domInteractive = 0;
    int domLoading = 0;
    int domainLookupEnd = 0;
    int domainLookupStart = 0;
    int fetchStart = 0;
    int loadEventEnd = 0;
    int loadEventStart = 0;
    int navigationStart = 0;
    int redirectEnd = 0;
    int redirectStart = 0;
    int requestStart = 0;
    int responseEnd = 0;
    int responseStart = 0;
    int secureConnectionStart = 0;
    int unloadEventEnd = 0;
    int unloadEventStart = 0;
    Object toJSON();
}
