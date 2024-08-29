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

    // Добавление нового заказа в очередь для обработки
    public synchronized void processNewOrder(Order order) {
        postOfficeOrders.add(order);
        System.out.println("Новый заказ добавлен: " + order);
        notifyAll(); // Уведомляем потоки, что есть новый заказ
    }

    // Получение следующего доступного заказа для почтового офиса
    public synchronized Order getNextOrderForPostOffice(PostOffice postOffice) {
        for (Order order : postOfficeOrders) {
            if (order.getPostOffice().equals(postOffice)) {
                return order;
            }
        }
        return null; // Если нет подходящих заказов
    }

    // Удаление заказа из очереди после начала обработки
    public synchronized void removeOrder(Order order) {
        postOfficeOrders.remove(order);
    }

    // Получение следующего заказа для камеры хранения
    public synchronized Order getNextOrderForLocker(ParcelLocker locker) {
        return lockerOrders.poll(); // Возвращаем и удаляем следующий заказ для камеры хранения
    }

    // Этап упаковки посылки с рандомной задержкой
    public void packOrder(Order order) {
        try {
            System.out.println("Упаковка посылки " + order.getPackage().getTrackingNumber() + "...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000)); // Рандомная задержка от 5 до 10 секунд
            order.getPackage().updateStatus("Упаковано в " + order.getPostOffice().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Этап отправки посылки с рандомной задержкой
    public void sendOrder(Order order) {
        try {
            System.out.println("Посылка " + order.getPackage().getTrackingNumber() + " уехала из " + order.getPostOffice().getName() + " в "  + order.getDestinationLocker().getLocation()+ ".");
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000)); // Рандомная задержка от 5 до 10 секунд
            order.getPackage().updateStatus("В пути к " + order.getDestinationLocker().getLocation());
            lockerOrders.add(order); // Добавляем в очередь камер хранения
            synchronized (this) {
                notifyAll(); // Уведомляем, что есть новый заказ для камер хранения
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Метод для обработки заказа в постамате — получение и уведомление о завершении

    public void receiveOrder(Order order) {
        synchronized (order.getDestinationLocker()) { // Синхронизация на уровне конкретного постамата
            try {
                System.out.println("Посылка " + order.getPackage().getTrackingNumber() + " прибыла в " + order.getDestinationLocker().getLocation() + ".");
                Thread.sleep(ThreadLocalRandom.current().nextInt(5000, 10000)); // Рандомная задержка от 5 до 10 секунд
                order.getPackage().updateStatus("Прибыла в " + order.getDestinationLocker().getLocation());
                System.out.println(order.getReceiver().getName() + " забирает посылку " + order.getPackage().getTrackingNumber() +
                        " из " + order.getDestinationLocker().getLocation() + " предъявив документ.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Добавление почтового офиса и запуск его потока
    public synchronized void addPostOffice(PostOffice postOffice) {
        postOffices.add(postOffice);
        PostOfficeThread postOfficeThread = new PostOfficeThread(postOffice, this);
        postOfficeThread.start();
        System.out.println("Поток для почтового отделения " + postOffice.getName() + " запущен.");
    }

    // Добавление камеры хранения и запуск ее потока
    public synchronized void addParcelLocker(ParcelLocker locker) {
        parcelLockers.add(locker);
        LockerThread lockerThread = new LockerThread(locker, this);
        lockerThread.start();
        System.out.println("Поток для постамата " + locker.getLocation() + " запущен.");
    }
}