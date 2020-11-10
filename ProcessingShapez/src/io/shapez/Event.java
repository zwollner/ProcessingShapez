package io.shapez;

public interface Event {
    boolean bubbles = false;
    boolean cancelBubble = false;
    boolean cancelable = false;
    EventTarget currentTarget = null;
    boolean defaultPrevented = false;
    int eventPhase = 0;
    boolean isTrusted = false;
    boolean returnValue = false;
    @Deprecated
    EventTarget srcElement = null;
    EventTarget target = null;
    int timeStamp = 0;
    String type = null;
    EventTarget[] composedPath();
    void initEvent(String type, boolean bubbles, boolean cancelable);
    void preventDefault();
    void stopImmediatePropagation();
    void stopPropagation();
    final int AT_TARGET = 0;
    final int BUBBLING_PHASE = 0;
    final int CAPTURING_PHASE = 0;
    final int NONE = 0;
}
