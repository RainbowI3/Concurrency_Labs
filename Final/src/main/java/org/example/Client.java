package org.example;

public class Client {
    private final String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void sendPackage(Package pkg, Client receiver, PostOffice postOffice, ParcelLocker destinationLocker, DeliveryService service) {

        Order order = new Order(this, receiver, pkg, postOffice, destinationLocker);
        service.processNewOrder(order);
    }


    public void receivePackage(Package pkg) {
        System.out.println(name + " получил посылку: " + pkg.getTrackingNumber());
    }
}//11