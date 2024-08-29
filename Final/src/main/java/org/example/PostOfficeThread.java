package org.example;

public class PostOfficeThread extends Thread{
    private final PostOffice postOffice;
    private final DeliveryService deliveryService;

    public PostOfficeThread(PostOffice postOffice, DeliveryService deliveryService) {
        this.postOffice = postOffice;
        this.deliveryService = deliveryService;
    }

    @Override
    public void run() {
        while (true) {
            Order order;
            synchronized (deliveryService) {
                while ((order = deliveryService.getNextOrderForPostOffice(postOffice)) == null) {
                    try {
                        deliveryService.wait(); // Ожидание появления доступного заказа
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // Удаляем заказ из очереди сразу после получения
                deliveryService.removeOrder(order);
            }

            // Обработка заказа вне блока синхронизации
            System.out.println(postOffice.getName() + " начала обработку заказа номер " + order.getPackage().getTrackingNumber());
            deliveryService.packOrder(order); // Упаковка посылки
            deliveryService.sendOrder(order); // Отправка посылки

            // Уведомляем о завершении обработки заказа
            synchronized (deliveryService) {
                deliveryService.notifyAll(); // Уведомляем, что офис освобожден
            }
        }
    }
}
