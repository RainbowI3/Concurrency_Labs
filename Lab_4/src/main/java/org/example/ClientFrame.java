package org.example;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
public class ClientFrame extends JFrame {
    private DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    private Client client = new Client();

    public ClientFrame() {
        setTitle("Client Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JFreeChart chart = ChartFactory.createLineChart(
                "Array Values",
                "Index",
                "Value",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter some text:");
        JTextField textField = new JTextField(20);


        textPanel.add(label);
        textPanel.add(textField);


        add(textPanel, BorderLayout.SOUTH);

        Timer timer = new Timer(2000, e -> updateChart());
        timer.start();


        KeySendListener.addKeyBindings(this, null); // Додаємо обробку клавіш для цього фрейму
    }

    private void updateChart() {
        int[] array = client.getDataFromServer();
        dataset.clear();
        for (int i = 0; i < array.length; i++) {
            dataset.addValue(array[i], "Random Values", Integer.toString(i));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClientFrame frame = new ClientFrame();
            frame.setVisible(true);
        });
    }
}
