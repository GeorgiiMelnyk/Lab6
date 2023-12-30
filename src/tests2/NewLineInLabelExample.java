package tests2;

import javax.swing.*;
import java.awt.*;

public class NewLineInLabelExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Пример JLabel с новой строкой");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // Создаем JLabel с HTML-разметкой для перехода на новую строку
        JLabel label = new JLabel("<html>Первая строка<br>Вторая строка<br>Третья строка</html>");

        // Устанавливаем черный цвет текста
        label.setForeground(Color.BLACK);

        panel.add(label);
        frame.getContentPane().add(panel);

        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
