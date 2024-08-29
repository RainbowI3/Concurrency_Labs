package org.example;

public class LockerThread extends Thread{
    private final ParcelLocker locker;
    private final DeliveryService deliveryService;

    public LockerThread(ParcelLocker locker, DeliveryService deliveryService) {
        this.locker = locker;
        this.deliveryService = deliveryService;
    }

    @Override
    public void run() {
        while (true) {
            Order order;
            synchronized (deliveryService) {
                while ((order = deliveryService.getNextOrderForLocker(locker)) == null) {
                    try {
                        deliveryService.wait(); // Ожидаем появления доступного заказа для камеры хранения
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            // Синхронизация на уровне постамата для предотвращения одновременной обработки
            synchronized (locker) {
                deliveryService.receiveOrder(order); // Обработка прибытия и получения заказа в постамате
            }
        }
    }
}
