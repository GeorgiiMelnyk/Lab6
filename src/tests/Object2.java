package tests;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Object2 extends JFrame {
    private JLabel label;

    public Object2() {
        setTitle("Object2");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 100);

        label = new JLabel("Значение:");
        add(label);

        // Создаем новый поток для прослушивания данных
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Принимаем данные на порт 12345
                    ServerSocket serverSocket = new ServerSocket(8000);
                    Socket socket = serverSocket.accept();
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    String data = in.readUTF();
                    in.close();
                    socket.close();
                    serverSocket.close();

                    // Обновляем значение на втором окне
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            label.setText("Значение: " + data);
                        }
                    });
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Object2().setVisible(true);
            }
        });
    }
}
