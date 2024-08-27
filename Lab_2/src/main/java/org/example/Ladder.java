package org.example;

public class Ladder {
    private final int id;
    private boolean isOccupied;

    public Ladder(int id) {
        this.id = id;
        this.isOccupied = false;
    }

    public synchronized boolean occupy() {
        if (!isOccupied) {
            isOccupied = true;
            return true;
        }
        return false;
    }

    public synchronized void release() {
        isOccupied = false;
        notifyAll();
    }

    public int getId() {
        return id;
    }
    public synchronized boolean isOccupied() {
        return isOccupied;
    }
}
