package org.example;

public class DeliveryVehicle {
    private String id;

    public DeliveryVehicle(String id) {
        this.id = id;
    }

    public void deliver(Order order) {
        System.out.println("Vehicle " + id + " delivering " + order);
        order.getPackage().updateStatus("Доставлено");
    }
}
