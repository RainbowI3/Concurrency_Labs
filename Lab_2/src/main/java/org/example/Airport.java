package org.example;

import java.util.concurrent.ThreadLocalRandom;

public class Airport {
    public static void main(String[] args) {
        AirportManager airportManager = new AirportManager(3, 2); // 3 terminals and 2 ladders

        Destination destination1 = new Destination("Lviv", 4); // Зона дальности 4
        Destination destination2 = new Destination("Kharkov", 6); // Зона дальности 6
        Destination destination3 = new Destination("Kyiv", 3); // Зона дальности 3
        Destination destination4 = new Destination("Frankfurt", 5); // Зона дальности 5

        Plane plane1 = new Plane("Plane1(Boeing 777-300ER)", 200, 5000,destination1);
        Plane plane2 = new Plane("Plane2(Boeing 747-400)", 300, 7000,destination2);
        Plane plane3 = new Plane("Plane3(Airbus A320-200)", 150, 3000,destination3);
        Plane plane4 = new Plane("Plane4(Sukhoi Superjet 100-95B)", 180, 4000,destination4);

        Runnable task1 = () -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000,5000));
                airportManager.handlePlane(plane1);
            } catch (NotEnoughTerminalsException | NotEnoughLaddersException  | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable task2 = () -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                airportManager.handlePlane(plane2);
            } catch (NotEnoughTerminalsException | NotEnoughLaddersException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable task3 = () -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                airportManager.handlePlane(plane3);
            } catch (NotEnoughTerminalsException | NotEnoughLaddersException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable task4 = () -> {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
                airportManager.handlePlane(plane4);
            } catch (NotEnoughTerminalsException | NotEnoughLaddersException | InterruptedException e) {
                System.out.println(e.getMessage());
            }
        };


        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);
        Thread thread4 = new Thread(task4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}
