package com.kari3600.me.javaproject.agents;

import com.kari3600.me.javaproject.Field;
import com.kari3600.me.javaproject.objects.Position;
import com.kari3600.me.javaproject.utils.CollectionUtil;

public class Rabbit extends Agent {

    private static final int minWait = 150;
    private static final int maxWait = 250;
    private final Field field;

    private void loop() {
        try {
            Thread.sleep((long) (minWait+Math.random()*(maxWait-minWait)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        position = CollectionUtil.getRandomElement(field.getNearPositions(position));
        if (field.getCarrot(position) == null || field.getCarrot(position).isDamaged()) {
            return;
        }
        for (int n=0;n<10;++n) {
            try {
                Thread.sleep((long) (minWait+Math.random()*(maxWait-minWait)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        field.eatCarrot(position);
    }

    public Rabbit(Field field, Position position) {
        super(position);
        this.field = field;
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
