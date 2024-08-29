package org.example;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();

        // Создаем клиентов
        List<Client> clients = new ArrayList<>();
        clients.add(new Client("Alex"));
        clients.add(new Client("Maria"));
        clients.add(new Client("Katy"));
        clients.add(new Client("Ruslan"));


        // Перемешиваем список клиентов
        Collections.shuffle(clients);

        // Создаем посылки
        List<Package> packages = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            packages.add(new Package());
        }

        // Создаем почтовые офисы и камеры хранения
        // Создаем почтовые офисы и камеры хранения
        PostOffice postOffice1 = new PostOffice("Почта №1");
        PostOffice postOffice2 = new PostOffice("Почта №2");
        PostOffice postOffice3 = new PostOffice("Почта №3");
        ParcelLocker locker1 = new ParcelLocker("Постамат №1");
        ParcelLocker locker2 = new ParcelLocker("Постамат №2");
        ParcelLocker locker3 = new ParcelLocker("Постамат №3");
        ParcelLocker locker4 = new ParcelLocker("Постамат №4");

        // Добавляем офисы и камеры хранения в службу доставки
        deliveryService.addPostOffice(postOffice1);
        deliveryService.addPostOffice(postOffice2);
        deliveryService.addPostOffice(postOffice3);

        deliveryService.addParcelLocker(locker1);
        deliveryService.addParcelLocker(locker2);
        deliveryService.addParcelLocker(locker3);
        deliveryService.addParcelLocker(locker4);

        // Запускаем потоки клиентов для отправки посылок
        for (int i = 0; i < packages.size(); i++) {
            Client sender = clients.get(i % clients.size());
            Client receiver = clients.get((i + 1) % clients.size()); // Следующий клиент в списке
            PostOffice selectedPostOffice = i % 3 == 0 ? postOffice1 : (i % 3 == 1 ? postOffice2 : postOffice3); // Чередуем офисы
            ParcelLocker selectedLocker = i % 2 == 0 ? locker2 : locker3; // Чередуем постаматы
            new ClientThread(sender, receiver, packages.get(i), selectedPostOffice, selectedLocker, deliveryService).start();
        }
    }
}

