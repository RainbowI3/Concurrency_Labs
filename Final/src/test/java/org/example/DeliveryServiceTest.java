package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest {
    private DeliveryService deliveryService;
    private Client sender;
    private Client receiver;
    private PostOffice postOffice;
    private ParcelLocker locker;

    @BeforeEach
    void setUp() {
        deliveryService = new DeliveryService();
        sender = new Client("Алексей");
        receiver = new Client("Мария");
        postOffice = new PostOffice("Почта №1");
        locker = new ParcelLocker("Постамат №1");
    }

    @Test
    void testReceiveOrderSynchronization() throws InterruptedException {
        // Создаем заказы
        Package pkg1 = new Package();
        Package pkg2 = new Package();
        Order order1 = new Order(sender, receiver, pkg1, postOffice, locker);
        Order order2 = new Order(sender, receiver, pkg2, postOffice, locker);

        // Добавляем заказы в очередь обработки
        deliveryService.processNewOrder(order1);
        deliveryService.sendOrder(order1);
        deliveryService.processNewOrder(order2);
        deliveryService.sendOrder(order2);

        // Запускаем поток для постамата
        LockerThread lockerThread = new LockerThread(locker, deliveryService);
        lockerThread.start();

        // Проверяем, что не обрабатываются несколько заказов одновременно
        synchronized (locker) {
            Thread.sleep(100); // Короткая пауза для проверки блокировки
            assertNull(deliveryService.getNextOrderForLocker(locker),
                    "Постамат не должен обрабатывать несколько заказов одновременно.");
        }

        lockerThread.join(); // Ждем завершения потока для корректного завершения теста
    }
}
