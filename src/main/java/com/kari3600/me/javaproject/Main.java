package com.kari3600.me.javaproject;

import com.kari3600.me.javaproject.objects.Position;

public class Main {
    public static void main(String[] args) {
        Field field = new Field(6);
        field.addRabbit(new Position(3, 4));
        field.print();
    }
}