package object1;

import object2.Object_2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Object_1 extends JFrame {

    private static Object_1 instance;
    private SetCordsWindow setCordsWindow;
    private Socket socketObject2;
    private Image icon = new ImageIcon("res/Lab6ImageIcon.png").getImage();

    private Object_1(){
        super("Lab6");
        createFrame();
        this.setVisible(true);
        Thread thread = new Thread(new Object_2());
        thread.start();
        //createConectionWithObj2();
        pushUpdates();
        pushUpdates();
        pushUpdates();
    }

    public static Object_1 getInstance(){
        if(instance == null){
            instance = new Object_1();
        }
        return instance;
    }

    private void createFrame(){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        this.setBounds(dimension.width / 2 - 225, dimension.height / 2 - 250, 450, 500);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        this.add(toolBar, BorderLayout.NORTH);

        JButton createCordButton = new JButton("Задати точки");
        createCordButton.setFocusPainted(false);
        createCordButton.setForeground(Color.BLACK);
        createCordButton.addActionListener(e -> {
                setCordsWindow = new SetCordsWindow(this);
        } );

        toolBar.add(createCordButton);

        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createConectionWithObj2(){
        try{
            socketObject2 = new Socket("localhost", 12345);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void pushUpdates(){
        try {
             // IP и порт сервера
            Socket socket = new Socket("localhost", 12345);
            // Отправка данных на сервер
            OutputStream outputStream = socket.getOutputStream();
            String dataToSend = "Привет, сервер!";
            outputStream.write(dataToSend.getBytes());
            socket.close();
            // Закрываем соединение и завершаем выполнение программы
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
