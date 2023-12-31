package object3;

import object2.Object2Window;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Object_3 implements Runnable{

    private Object3Window object3Window;
    private double[] xList;
    private double[] yList;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(7000);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Подключен клиент к объекту 3: " + clientSocket.getInetAddress());

                readObject_2Data(clientSocket);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readObject_2Data(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            String receivedData = "";
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                receivedData = new String(buffer, 0, bytesRead);
            }
            System.out.println("Получены данные от Object_2: " + receivedData);
            getDataFromObject_3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getDataFromObject_3(){

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String receivedData = "";

        if (contents != null && contents.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            try {
                receivedData = (String) contents.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        }

        String[] allPoints = receivedData.split("\\|");
        xList = new double[allPoints.length];
        yList = new double[allPoints.length];

        for (int i = 0; i < allPoints.length; i++){

            String[] currentXandY = allPoints[i].split(" ");

            double currentX = Double.parseDouble(currentXandY[0]);
            double currentY = Double.parseDouble(currentXandY[1]);

            xList[i] = currentX;
            yList[i] = currentY;
        }

        SwingUtilities.invokeLater(() -> {
            if(object3Window == null){
                object3Window = new Object3Window(this, xList, yList);
            } else {
                object3Window.updateGraphic(xList, yList);
            }
        });

    }

    protected void destroyObject3Window() {
        this.object3Window = null;
    }
}

