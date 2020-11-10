package io.shapez;

public interface EventTarget {
    void addEventListener(String type, EventListenerObject listener, boolean AddEventListenerOptions);
}
