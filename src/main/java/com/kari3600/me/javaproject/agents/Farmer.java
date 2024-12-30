package com.kari3600.me.javaproject.agents;

import com.kari3600.me.javaproject.Field;
import com.kari3600.me.javaproject.objects.Position;

public class Farmer extends Agent {
    private static final int minWait = 150;
    private static final int maxWait = 250;

    private final Field field;
    private final Dog dog;

    public Farmer(Field field, Position position) {
        super(position);
        this.field = field;
        this.dog = new Dog(field,position);
        while (true) {
            for (int n=0;n<10;++n) {
                try {
                    Thread.sleep((long) (minWait+Math.random()*(maxWait-minWait)));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (field.getRabbits().stream().anyMatch(rabbit -> rabbit.getPosition().equals(position))) {
                    dog.call(position);
                }
                //if (field.getCarrots())
            }
        }
    }
}
