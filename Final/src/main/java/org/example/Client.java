package org.example;

public class Client {
    private final String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Метод для отправки посылки
    public void sendPackage(Package pkg, Client receiver, PostOffice postOffice, ParcelLocker destinationLocker, DeliveryService service) {
        // Создаем новый заказ и отправляем его в службу доставки
        Order order = new Order(this, receiver, pkg, postOffice, destinationLocker);
        service.processNewOrder(order);
    }

    // Метод для получения посылки
    public void receivePackage(Package pkg) {
        System.out.println(name + " получил посылку: " + pkg.getTrackingNumber());
    }
}