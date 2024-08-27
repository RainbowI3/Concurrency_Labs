package org.example;

public class Plane {
    private final String id;
    private final int size;
    private final int range;
    private final Destination destination;

    public Plane(String id, int size, int range,Destination destination) {
        this.id = id;
        this.size = size;
        this.range = range;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public int getRange() {
        return range;
    }
    public Destination getDestination() {
        return destination;
    }
    public boolean canReachDestination() {
        return range >= destination.getRangeZone() * 1000; // Проверка, может ли самолет достичь пункта назначения
    }
}
