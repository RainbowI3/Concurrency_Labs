package org.example;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.ActionEvent;
public class KeySendListener {



    public static void addKeyBindings(JFrame mainFrame, JFrame secondaryFrame) {

        Action ctrlAAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ctrl + A pressed");
                if (mainFrame != null) {
                    activateWindow(mainFrame);
                }
            }
        };


        Action ctrlBAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ctrl + B pressed");
                if (secondaryFrame != null) {
                    activateWindow(secondaryFrame);
                }
            }
        };


        InputMap inputMap = mainFrame.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = mainFrame.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("control A"), "ctrlA");
        actionMap.put("ctrlA", ctrlAAction);

        inputMap.put(KeyStroke.getKeyStroke("control B"), "ctrlB");
        actionMap.put("ctrlB", ctrlBAction);
    }


    private static void activateWindow(JFrame frame) {

        frame.setVisible(true);
        frame.setExtendedState(JFrame.NORMAL);


        EventQueue.invokeLater(() -> {
            frame.setAlwaysOnTop(true);
            frame.toFront();
            frame.requestFocusInWindow();
            frame.setAlwaysOnTop(false);
        });
    }
}
