package org.example;


import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.*;

public class AirportManager {
    private final Terminal[] terminals;
    private final Ladder[] ladders;
    private final ReentrantLock lock = new ReentrantLock();

    public AirportManager(int terminalCount, int ladderCount) {
        terminals = new Terminal[terminalCount];
        ladders = new Ladder[ladderCount];
        for (int i = 0; i < terminalCount; i++) {
            terminals[i] = new Terminal(i + 1);
        }
        for (int i = 0; i < ladderCount; i++) {
            ladders[i] = new Ladder(i + 1);
        }
    }

    public void handlePlane(Plane plane) throws NotEnoughTerminalsException, NotEnoughLaddersException {
        Terminal terminal = null;
        Ladder ladder = null;
        lock.lock();

        try {
            if (!plane.canReachDestination()) {
                System.out.println("Plane " + plane.getId() + " cannot reach its destination: " + plane.getDestination().getName());
                return;
            }
            // Ожидание доступного терминала
            while ((terminal = findAvailableTerminal()) == null) {
                System.out.println("Plane " + plane.getId() + " is waiting for a terminal.");
                waitForResource(); // Ждет, пока не освободится терминал
            }

            // Ожидание доступного трапа
            while ((ladder = findAvailableLadder()) == null) {
                System.out.println("Plane " + plane.getId() + " is waiting for a ladder.");
                waitForResource(); // Ждет, пока не освободится трап
            }

            System.out.println("Plane " + plane.getId() + " is boarding at Terminal " + terminal.getId() + " using Ladder " + ladder.getId());

            terminal.occupy();
            ladder.occupy();

        } finally {
            lock.unlock();
        }
        // Simulate boarding/landing process
        try {
            int boardingTime = ThreadLocalRandom.current().nextInt(1000, 5000); // Случайное время для имитации процесса
            Thread.sleep(boardingTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Освобождение терминала и трапа после завершения работы
        synchronized (this) {
            terminal.release();
            ladder.release();
            notifyAll(); // Уведомляем другие потоки, что ресурс освободился
        }

        System.out.println("Plane " + plane.getId() + " has finished boarding/landing and is ready to depart to " + plane.getDestination().getName() + ".");


    }

    private Terminal findAvailableTerminal() {
        for (Terminal terminal : terminals) {
            if (!terminal.isOccupied()) {
                return terminal;
            }
        }
        return null;
    }

    private Ladder findAvailableLadder() {
        for (Ladder ladder : ladders) {
            if (!ladder.isOccupied()) {
                return ladder;
            }
        }
        return null;
    }
    private synchronized void waitForResource() {
        try {
            wait(); // Ждет, пока не будет доступен терминал или трап
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
