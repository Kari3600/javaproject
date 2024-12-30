package com.kari3600.me.javaproject.agents;

import com.kari3600.me.javaproject.objects.Position;

public abstract class Agent {
    protected Position position;

    public Position getPosition() {
        return position;
    }

    public Agent(Position pos) {
        this.position = pos;
    }
}
