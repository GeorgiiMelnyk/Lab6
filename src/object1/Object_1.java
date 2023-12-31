package object1;

import object2.Object_2;
import object3.Object_3;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Object_1 extends JFrame {

    private static Object_1 instance;
    private final Image icon = new ImageIcon("res/Lab6ImageIcon.png").getImage();

    private Object_1(){
        super("Lab6");
        createFrame();

        Thread object2Thread = new Thread(new Object_2());
        Thread object3Thread = new Thread(new Object_3());

        object2Thread.start();
        object3Thread.start();

        this.setVisible(true);
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

        this.setBounds(50, 200, 450, 500);

        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        this.add(toolBar, BorderLayout.NORTH);

        JButton createCordButton = new JButton("Задати точки");
        createCordButton.setFocusPainted(false);
        createCordButton.setForeground(Color.BLACK);
        createCordButton.addActionListener(e -> {
                new SetCordsWindow(this);
        } );

        toolBar.add(createCordButton);

        this.setIconImage(icon);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    protected void setDatasToUpdateObject_2(int points, double xMin, double xMax, double yMin, double yMax){

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(points).append("|");
        stringBuilder.append(xMin).append("|");
        stringBuilder.append(xMax).append("|");
        stringBuilder.append(yMin).append("|");
        stringBuilder.append(yMax);

        String result = stringBuilder.toString();

        pushUpdatesToObject_2(result);
    }

    private void pushUpdatesToObject_2(String dataToSend){
        try {
            Socket socket = new Socket("localhost", 12345);

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(dataToSend.getBytes());
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Object_1.getInstance();
    }

}
