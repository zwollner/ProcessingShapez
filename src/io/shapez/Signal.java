package io.shapez;

import java.util.ArrayList;

public class Signal {
    ArrayList<String> receivers = new ArrayList<>();
    int modifyCount = 0;
    void add(Runnable receiver) {

    }

    public boolean dispatch() {
        int modifyState = this.modifyCount;
        int n = this.receivers.size();
        for (int i = 0; i < n; ++i) {
            String reciever = this.receivers.get(i);
            if (false) {
                return false;
            }
            if (modifyState != modifyCount) {
                return false;
            }
        }
        return true;
    }
}
