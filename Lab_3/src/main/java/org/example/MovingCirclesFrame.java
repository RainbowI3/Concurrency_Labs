package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class MovingCirclesFrame extends JFrame{


    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int RADIUS = 25;
    private static final int CIRCLE_COUNT = 5;

    private List<Circle> circles = new ArrayList<>();
    private Random random = new Random();

    public MovingCirclesFrame() {
        setTitle("MovingCircles");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Инициализация кругов
        for (int i = 0; i < CIRCLE_COUNT; i++) {
            int x = random.nextInt(WIDTH - 2 * RADIUS) + RADIUS;
            int y = random.nextInt(HEIGHT - 2 * RADIUS) + RADIUS;
            int dx = random.nextBoolean() ? 2 : -2;  // Случайное направление по X
            int dy = random.nextBoolean() ? 2 : -2;  // Случайное направление по Y
            circles.add(new Circle(x, y, dx, dy));
        }

        DrawingPanel panel = new DrawingPanel();
        add(panel);

        // Таймер для обновления положения кругов
        Timer timer = new Timer(16, new ActionListener() { //Больше герцовки монитора не советую ставить анимация зависнет
            @Override
            public void actionPerformed(ActionEvent e) {
                moveCircles();
                panel.repaint();
            }
        });
        timer.start();
    }

    private void moveCircles() {
        for (int i = 0; i < circles.size(); i++) {
            Circle circle = circles.get(i);


            if (circle.x <= RADIUS || circle.x >= WIDTH - RADIUS) {
                circle.dx = -circle.dx;  // Меняем направление по оси X
            }
            if (circle.y <= RADIUS || circle.y >= HEIGHT - RADIUS) {
                circle.dy = -circle.dy;  // Меняем направление по оси Y
            }

            // Проверка столкновения с другими кругами
            for (int j = i + 1; j < circles.size(); j++) {
                Circle other = circles.get(j);
                int dx = circle.x - other.x;
                int dy = circle.y - other.y;
                double distance = Math.sqrt(dx * dx + dy * dy);

                if (distance <= 2 * RADIUS) {  // Если круги сталкиваются
                    circle.dx = -circle.dx;  // Меняем направление по оси X
                    circle.dy = -circle.dy;  // Меняем направление по оси Y
                    other.dx = -other.dx;
                    other.dy = -other.dy;
                }
            }

            // Изменение координат круга
            circle.x += circle.dx;
            circle.y += circle.dy;
        }
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, WIDTH, HEIGHT);

            g.setColor(Color.BLACK);
            for (Circle circle : circles) {
                g.fillOval(circle.x - RADIUS, circle.y - RADIUS, 2 * RADIUS, 2 * RADIUS);  // Рисуем круг
            }
        }
    }

    private class Circle {
        int x, y, dx, dy;

        Circle(int x, int y, int dx, int dy) {
            this.x = x;
            this.y = y;
            this.dx = dx;
            this.dy = dy;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MovingCirclesFrame frame = new MovingCirclesFrame();
                frame.setVisible(true);
            }
        });
    }
}


