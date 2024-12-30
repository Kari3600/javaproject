package com.kari3600.me.javaproject;

import com.kari3600.me.javaproject.objects.Position;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(6);
        field.addFarmer(new Position(0, 0));
        field.addRabbit(new Position(3, 4));
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            field.print();
        }
    }
}