package com.kari3600.me.javaproject.objects;

import java.util.Objects;

public class Position {
    public final int x;
    public final int y;
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Position)) return false;
        Position pos = (Position) object;
        if (pos.x != x) return false;
        if (pos.y != y) return false;
        return true;
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
    @Override
    public String toString() {
        return "("+x+", "+y+")";
    }
    public Position(int x, int y) {
        this.x=x;
        this.y=y;
    }
}
