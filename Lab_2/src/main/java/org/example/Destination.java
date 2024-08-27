package org.example;

public class Destination {
    private final String name;
    private final int rangeZone; // Зона дальности

    public Destination(String name, int rangeZone) {
        this.name = name;
        this.rangeZone = rangeZone;
    }

    public String getName() {
        return name;
    }

    public int getRangeZone() {
        return rangeZone;
    }
}
