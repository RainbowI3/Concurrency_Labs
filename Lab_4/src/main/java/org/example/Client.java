package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 8080;

    public int[] getDataFromServer() {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String receivedData = in.readLine();

            String[] stringValues = receivedData.replace("[", "").replace("]", "").split(", ");
            int[] array = new int[stringValues.length];
            for (int i = 0; i < stringValues.length; i++) {
                array[i] = Integer.parseInt(stringValues[i]);
            }
            return array;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new int[0];
    }

    public static void main(String[] args) {
        Client client = new Client();
        int[] data = client.getDataFromServer();
        System.out.println("Received data: " + Arrays.toString(data));
    }

}
