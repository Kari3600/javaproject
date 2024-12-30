package com.kari3600.me.javaproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.kari3600.me.javaproject.agents.Agent;
import com.kari3600.me.javaproject.agents.Rabbit;
import com.kari3600.me.javaproject.objects.Carrot;
import com.kari3600.me.javaproject.objects.Position;

public class Field {
    private final int size;
    private final ConcurrentHashMap<Position,Carrot> carrots;
    private final CopyOnWriteArrayList<Rabbit> rabbits;

    public CopyOnWriteArrayList<Rabbit> getRabbits() {
        return rabbits;
    }

    public void addRabbit(Position pos) {
        rabbits.add(new Rabbit(pos));
    }

    public void print() {
        StringBuilder spacer = new StringBuilder("+");
        for (int n=0;n<size;++n) {
            spacer.append("----+");
        }
        System.out.println(spacer);
        Multimap<Position,Agent> agents = ArrayListMultimap.create();
        for (Agent agent : rabbits) {
            agents.put(agent.getPosition(), agent);
        }
        for (int y=0;y<size;++y) {
            StringBuilder upper = new StringBuilder("| ");
            StringBuilder lower = new StringBuilder("| ");
            for (int x=0;x<size;++x) {
                Position pos = new Position(x, y);
                ArrayList<Agent> a = new ArrayList<>(agents.get(pos));
                if (a.size() > 0) {
                    upper.append(a.get(0).getClass().getSimpleName().charAt(0));
                } else {
                    upper.append(" ");
                }
                if (a.size() > 1) {
                    upper.append(a.get(1).getClass().getSimpleName().charAt(0));
                } else {
                    upper.append(" ");
                }
                if (a.size() > 2) {
                    lower.append(a.get(2).getClass().getSimpleName().charAt(0));
                } else {
                    lower.append(" ");
                }
                if (a.size() > 4) {
                    lower.append("+");
                } else if (a.size() == 4) {
                    lower.append(a.get(3).getClass().getSimpleName().charAt(0));
                } else {
                    lower.append(" ");
                }
                upper.append(" | ");
                lower.append(" | ");
            }
            System.out.println(upper);
            System.out.println(lower);
            System.out.println(spacer);
        }
    }


    public Field(int size) {
        this.size = size;
        carrots = new ConcurrentHashMap<>();
        rabbits = new CopyOnWriteArrayList<>();
    }
}
