package org.example;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class Server {
    private static final int PORT = 8080;
    private int[] array;
    private static final int DEFAULT_SIZE = 300;

    public Server() {

        array = generateArray(DEFAULT_SIZE);
    }


    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);


            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintWriter out = new PrintWriter(outputStream, true);

                    out.println(Arrays.toString(array));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int[] generateArray(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(256);
        }
        return array;
    }


    public void startSorting() {

        new Thread(() -> {
            while (true) {
                int[] sortedArray = array.clone();
                Arrays.sort(sortedArray);
                System.out.println("Sorted ascending: " + Arrays.toString(sortedArray));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                int[] sortedArray = array.clone();
                Arrays.sort(sortedArray);
                reverseArray(sortedArray);
                System.out.println("Sorted descending: " + Arrays.toString(sortedArray));
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private void reverseArray(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - i - 1];
            array[array.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.startSorting();
        server.startServer();
    }
}
