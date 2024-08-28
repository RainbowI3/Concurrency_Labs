package org.example;
import javax.swing.*;
import java.awt.*;

public class ServerFrame extends JFrame{
    private JTextArea textArea;

    public ServerFrame() {
        setTitle("Server Application");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);


        ClientFrame clientFrame = new ClientFrame();


        KeySendListener.addKeyBindings(this, clientFrame);
    }

    public void log(String message) {
        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
    }

    public static void main(String[] args) {
        ServerFrame frame = new ServerFrame();
        frame.setVisible(true);
        Server server = new Server();
        frame.log("Server started...");
        server.startSorting();
        new Thread(server::startServer).start();
    }
}
