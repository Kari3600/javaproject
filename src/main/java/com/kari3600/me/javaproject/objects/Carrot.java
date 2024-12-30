package com.kari3600.me.javaproject.objects;

public class Carrot {
    private boolean damaged = false;

    public void damage() {
        damaged = true;
    }

    public boolean isDamaged() {
        return damaged;
    }
}
