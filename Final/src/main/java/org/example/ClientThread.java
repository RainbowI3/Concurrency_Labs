package org.example;

public class ClientThread extends Thread {

    private final Client sender;
    private final Client receiver;
    private final Package pkg;
    private final PostOffice postOffice;
    private final ParcelLocker locker;
    private final DeliveryService service;

    public ClientThread(Client sender, Client receiver, Package pkg, PostOffice postOffice, ParcelLocker locker, DeliveryService service) {
        this.sender = sender;
        this.receiver = receiver;
        this.pkg = pkg;
        this.postOffice = postOffice;
        this.locker = locker;
        this.service = service;
    }

    @Override
    public void run() {
        // Симулируем задержку перед отправкой посылки, чтобы показывать реалистичную работу системы
        try {
            Thread.sleep((long) (Math.random() * 3000)); // Рандомная задержка перед отправкой (до 3 секунд)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Отправка посылки через клиента
        sender.sendPackage(pkg, receiver, postOffice, locker, service);
    }
}
