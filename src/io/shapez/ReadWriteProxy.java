package io.shapez;

public class ReadWriteProxy {
    String filename;
    String currentData = null;

    ReadWriteProxy(String filename, Application app) {
        this.filename = filename;

    }
}
