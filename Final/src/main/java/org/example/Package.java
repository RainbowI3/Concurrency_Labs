package org.example;

public class Package {
    private static int counter = 1;
    private final int trackingNumber;
    private String status;

    public Package() {
        this.trackingNumber = counter++;
        this.status = "Отправлено";
    }

    public int getTrackingNumber() {
        return trackingNumber;
    }

    public synchronized void updateStatus(String status) {
        this.status = status;
        notifyAll();
    }

    public synchronized String getStatus() {
        return status;
    }
}
