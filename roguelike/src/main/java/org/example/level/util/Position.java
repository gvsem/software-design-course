package org.example.level.util;

public record Position(int x, int y) implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
