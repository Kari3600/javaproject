package com.kari3600.me.javaproject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.kari3600.me.javaproject.agents.Agent;
import com.kari3600.me.javaproject.agents.Farmer;
import com.kari3600.me.javaproject.agents.Rabbit;
import com.kari3600.me.javaproject.objects.Carrot;
import com.kari3600.me.javaproject.objects.Position;

public class Field {
    private final int size;
    private final ConcurrentHashMap<Position,Carrot> carrots;
    private final CopyOnWriteArrayList<Agent> agents;

    public CopyOnWriteArrayList<Agent> getAgents() {
        return agents;
    }

    public void addRabbit(Position pos) {
        agents.add(new Rabbit(this,pos));
    }

    public void addFarmer(Position pos) {
        agents.add(new Farmer(this,pos));
    }

    public void plantCarrot(Position pos) {
        carrots.put(pos, new Carrot());
    }

    public void eatCarrot(Position pos) {
        Carrot carrot = new Carrot();
        carrot.damage();
        carrots.put(pos, carrot);
    }

    public void repairField(Position pos) {
        carrots.remove(pos);
    }

    public Carrot getCarrot(Position pos) {
        return carrots.get(pos);
    }

    public Set<Position> getNearPositions(Position pos) {
        Set<Position> p = new HashSet<>();
        if (pos.x-1 >= 0) {
            p.add(new Position(pos.x-1, pos.y));
        }
        if (pos.x+1 < size) {
            p.add(new Position(pos.x+1, pos.y));
        }
        if (pos.y-1 >= 0) {
            p.add(new Position(pos.x, pos.y-1));
        }
        if (pos.y+1 < size) {
            p.add(new Position(pos.x, pos.y+1));
        }
        return p;
    }

    public void print() {
        StringBuilder spacer = new StringBuilder("+");
        for (int n=0;n<size;++n) {
            spacer.append("----+");
        }
        System.out.println(spacer);
        Multimap<Position,Agent> agentsMap = ArrayListMultimap.create();
        for (Agent agent : agents) {
            agentsMap.put(agent.getPosition(), agent);
        }
        for (int y=0;y<size;++y) {
            StringBuilder upper = new StringBuilder("|");
            StringBuilder lower = new StringBuilder("|");
            for (int x=0;x<size;++x) {
                Position pos = new Position(x, y);
                ArrayList<Agent> a = new ArrayList<>(agentsMap.get(pos));
                char c;
                if (carrots.get(pos) != null) {
                    if (carrots.get(pos).isDamaged()) {
                        c = '#';
                    } else {
                        c = ':';
                    }
                } else {
                    c = ' ';
                }
                upper.append(c);
                lower.append(c);
                if (a.size() > 0) {
                    upper.append(a.get(0).getClass().getSimpleName().charAt(0));
                } else {
                    upper.append(c);
                }
                if (a.size() > 1) {
                    upper.append(a.get(1).getClass().getSimpleName().charAt(0));
                } else {
                    upper.append(c);
                }
                if (a.size() > 2) {
                    lower.append(a.get(2).getClass().getSimpleName().charAt(0));
                } else {
                    lower.append(c);
                }
                if (a.size() > 4) {
                    lower.append("+");
                } else if (a.size() == 4) {
                    lower.append(a.get(3).getClass().getSimpleName().charAt(0));
                } else {
                    lower.append(c);
                }
                upper.append(c+"|");
                lower.append(c+"|");
            }
            System.out.println(upper);
            System.out.println(lower);
            System.out.println(spacer);
        }
    }


    public Field(int size) {
        this.size = size;
        carrots = new ConcurrentHashMap<>();
        agents = new CopyOnWriteArrayList<>();
    }
}
