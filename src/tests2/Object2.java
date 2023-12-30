package tests2;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Object2 extends JFrame {

    public Object2() {
        setTitle("Object2");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Получаем поток ввода для чтения данных из Object1
        new Thread(() -> {
            try {
                // Поток для чтения данных из Object1
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String inputData;
                while ((inputData = reader.readLine()) != null) {
                    System.out.println("Object2 received data: " + inputData);
                    // Здесь можно обработать полученные данные
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Object2 object2 = new Object2();
            object2.setVisible(true);
        });
    }
}
