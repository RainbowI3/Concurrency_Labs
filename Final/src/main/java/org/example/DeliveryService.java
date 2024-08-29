package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ThreadLocalRandom;
public class DeliveryService {
    private final List<PostOffice> postOffices = new ArrayList<>();
    private final List<ParcelLocker> parcelLockers = new ArrayList<>();
    private final ConcurrentLinkedQueue<Order> postOfficeOrders = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<Order> lockerOrders = new ConcurrentLinkedQueue<>();


    public synchronized void processNewOrder(Order order) {
        postOfficeOrders.add(order);
        System.out.println("Новый заказ добавлен: " + order);
        notifyAll();
    }


    public synchronized Order getNextOrderForPostOffice(PostOffice postOffice) {
        for (Order order : postOfficeOrders) {
            if (order.getPostOffice().equals(postOffice)) {
                return order;
            }
        }
        return null;
    }


    public synchronized void removeOrder(Order order) {
        postOfficeOrders.remove(order);
    }


    public synchronized Order getNextOrderForLocker(ParcelLocker locker) {
        return lockerOrders.poll();
    }


    public void packOrder(Order order) {
        try {
            System.out.println("Упаковка посылки " + order.getPackage().getTrackingNumber() + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000));
            order.getPackage().updateStatus("Упаковано в " + order.getPostOffice().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void sendOrder(Order order) {
        try {
            System.out.println("Посылка " + order.getPackage().getTrackingNumber() + " уехала из " + order.getPostOffice().getName() + " в "  + order.getDestinationLocker().getLocation()+ ".");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000));
            order.getPackage().updateStatus("В пути к " + order.getDestinationLocker().getLocation());
            lockerOrders.add(order);
            synchronized (this) {
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public void receiveOrder(Order order) {
        synchronized (order.getDestinationLocker()) {
            try {
                System.out.println("Посылка " + order.getPackage().getTrackingNumber() + " прибыла в " + order.getDestinationLocker().getLocation() + ".");
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000));
                order.getPackage().updateStatus("Прибыла в " + order.getDestinationLocker().getLocation());
                System.out.println(order.getReceiver().getName() + " забирает посылку " + order.getPackage().getTrackingNumber() +
                        " из " + order.getDestinationLocker().getLocation() + " предъявив документ.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized void addPostOffice(PostOffice postOffice) {
        postOffices.add(postOffice);
        PostOfficeThread postOfficeThread = new PostOfficeThread(postOffice, this);
        postOfficeThread.start();
        System.out.println("Поток для почтового отделения " + postOffice.getName() + " запущен.");
    }


    public synchronized void addParcelLocker(ParcelLocker locker) {
        parcelLockers.add(locker);
        LockerThread lockerThread = new LockerThread(locker, this);
        lockerThread.start();
        System.out.println("Поток для постамата " + locker.getLocation() + " запущен.");
    }
}
