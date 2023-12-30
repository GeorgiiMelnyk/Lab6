package tests2;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class Object1 extends JFrame {

    private Process object2Process;
    private JButton createObject2Button;

    public Object1() {
        setTitle("Object1");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeObject2();
                System.out.println("Object1 is closing");
                super.windowClosing(e);
            }
        });

        JPanel panel = new JPanel();
        createObject2Button = new JButton("Create Object2");
        // Запуск Object2
        createObject2Button.addActionListener(e -> {
            startObject2();
        });
        panel.add(createObject2Button);
        this.add(panel);
    }

    private void startObject2() {
        // Закрыть предыдущий процесс Object2, если он существует
        closeObject2();

        try {
            // Запускаем Object2
            ProcessBuilder builder = new ProcessBuilder("java", "src/tests2/Object2.java");
            builder.redirectErrorStream(true);
            object2Process = builder.start();

            // Получаем поток вывода для передачи данных в Object2
            OutputStream outputStream = object2Process.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            // Передаем данные в Object2
            writer.write("Hello World");
            writer.newLine();
            writer.flush();

            // Ждем завершения Object2
            object2Process.waitFor();

            // Поток для чтения вывода Object2
            System.out.println("Object2 exited with code: " + object2Process.exitValue());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error starting Object2", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void closeObject2() {
        if (object2Process != null) {
            // Закрываем потоки ввода/вывода
            try {
                object2Process.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Закрываем процесс
            object2Process.destroy();
            System.out.println("Object2 is closed");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Object1 object1 = new Object1();
            object1.setVisible(true);
        });
    }
}
