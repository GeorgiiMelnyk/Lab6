package object2;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class Object_2 implements Runnable{

    private Object2Window object2Window;
    private double[] xList;
    private double[] yList;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Порт для прослушивания

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Подключен клиент: " + clientSocket.getInetAddress());

                readObject_1Data(clientSocket);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readObject_1Data(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            String receivedData = "";
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                receivedData = new String(buffer, 0, bytesRead);
            }
            System.out.println("Получены данные от Object_1: " + receivedData);
            createWindowOrUpdate(receivedData);
            pushUpdatesToObject_3(getFormatDataForObject_3(this.xList, this.yList));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createWindowOrUpdate(String receivedData){
        String[] allNumbers = receivedData.split("\\|");

        int numOfGenPoints = Integer.parseInt(allNumbers[0]);
        double xmin = Double.parseDouble(allNumbers[1]);
        double xmax = Double.parseDouble(allNumbers[2]);
        double ymin = Double.parseDouble(allNumbers[3]);
        double ymax = Double.parseDouble(allNumbers[4]);

        double[] randomXList = new double[numOfGenPoints];
        double[] randomYList = new double[numOfGenPoints];

        Random random = new Random();

        for(int i = 0; i < numOfGenPoints; i++){

            double randomX = xmin + (xmax - xmin) * random.nextDouble();
            double formatedRandX = Math.round(randomX * 100.0) / 100.0;

            randomXList[i] = formatedRandX;


            double randomY = ymin + (ymax - ymin) * random.nextDouble();
            double formatedRandY = Math.round(randomY * 100.0) / 100.0;

            randomYList[i] = formatedRandY;
        }

        Arrays.sort(randomXList);

        this.xList = Arrays.copyOf(randomXList, randomXList.length);
        this.yList = Arrays.copyOf(randomYList, randomYList.length);

        SwingUtilities.invokeLater(() -> {
            if(object2Window == null){
                object2Window = new Object2Window(this, this.xList, this.yList);
            }  else {
                object2Window.setTextArea(randomXList, randomYList);
            }
        });
    }

    private void pushUpdateStatusToObject_3(){
        try {
            // IP и порт сервера
            Socket socket = new Socket("localhost", 7000);
            String status = "update";
            // Отправка данных на сервер
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(status.getBytes());
            socket.close();
            // Закрываем соединение и завершаем выполнение программы
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pushUpdatesToObject_3(String datas){
        StringSelection stringSelection = new StringSelection(datas);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        clipboard.getContents(null);
        clipboard.setContents(stringSelection, null);

        pushUpdateStatusToObject_3();
    }

    private String getFormatDataForObject_3(double[] xList, double[] yList){

        String result;
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < xList.length; i++){
            stringBuilder.append(String.valueOf(xList[i])).append(" ").append(String.valueOf(yList[i])).append("|");
        }
        int length = stringBuilder.length();
        stringBuilder.deleteCharAt(length - 1);
        result = stringBuilder.toString();

        return result;
    }

    protected void destroyObject2Window() {
        this.object2Window = null;
    }
}
