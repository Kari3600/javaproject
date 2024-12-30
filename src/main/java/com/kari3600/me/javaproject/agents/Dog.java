package com.kari3600.me.javaproject.agents;

import com.kari3600.me.javaproject.Field;
import com.kari3600.me.javaproject.objects.Position;

public class Dog extends Agent {
    private final Field field;

    public void call(Position pos) {
        
    }

    public Dog(Field field, Position pos) {
        super(pos);
        this.field = field;
    }
}
