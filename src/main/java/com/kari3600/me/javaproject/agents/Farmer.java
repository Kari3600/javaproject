package com.kari3600.me.javaproject.agents;

import com.kari3600.me.javaproject.Field;
import com.kari3600.me.javaproject.objects.Position;
import com.kari3600.me.javaproject.utils.CollectionUtil;

public class Farmer extends Agent {
    private static final int minWait = 150;
    private static final int maxWait = 250;

    private final Field field;
    private final Dog dog;

    private void loop() {
        try {
            Thread.sleep((long) (minWait+Math.random()*(maxWait-minWait)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        position = CollectionUtil.getRandomElement(field.getNearPositions(position));
        if (field.getCarrot(position) != null) {
            if (field.getCarrot(position).isDamaged()) {
                for (int n=0;n<10;++n) {
                    try {
                        Thread.sleep((long) (minWait+Math.random()*(maxWait-minWait)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (field.getAgents().stream().filter(agent -> agent instanceof Rabbit).anyMatch(rabbit -> rabbit.getPosition().equals(position))) {
                        dog.call(position);
                    }
                }
                field.repairField(position);
            } else {
                return;
            }
        }
        for (int n=0;n<10;++n) {
            try {
                Thread.sleep((long) (minWait+Math.random()*(maxWait-minWait)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (field.getAgents().stream().filter(agent -> agent instanceof Rabbit).anyMatch(rabbit -> rabbit.getPosition().equals(position))) {
                dog.call(position);
            }
            if (field.getCarrot(position) != null && field.getCarrot(position).isDamaged()) {
                return;
            }
        }
        field.plantCarrot(position);   
    }

    public Farmer(Field field, Position position) {
        super(position);
        this.field = field;
        this.dog = new Dog(field,position);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    loop();
                }
            }
        }).start();
    }
}
