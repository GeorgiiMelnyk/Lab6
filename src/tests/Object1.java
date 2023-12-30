package tests;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Object1 extends JFrame {
    private JTextField textField;

    public Object1() {
        setTitle("Object1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);

        textField = new JTextField();
        JButton button = new JButton("Отправить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendData(/*textField.getText()*/ "Hello World");
            }
        });

        JPanel panel = new JPanel();
        panel.add(textField);
        panel.add(button);

        add(panel);
    }

    private void sendData(String data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Object2().setVisible(true);
                        }
                    });

                    Socket socket = new Socket("localhost", 8000);
                    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                    out.writeUTF(data);
                    out.close();
                    socket.close();

                    // После отправки данных, создаем и отображаем Object2

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
                new Object1().setVisible(true);
            }
        });
    }
}
