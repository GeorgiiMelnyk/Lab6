package object2;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Object_2 implements Runnable{

    private Object2Window object2Window;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Порт для прослушивания

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Подключен клиент: " + clientSocket.getInetAddress());

                handleClient(clientSocket);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void handleClient(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;
            String receivedData = "";
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                receivedData = new String(buffer, 0, bytesRead);

                /*String responseData = "Данные получены успешно";
                outputStream.write(responseData.getBytes());*/
            }
            System.out.println("Получены данные от клиента: " + receivedData);
            createWindowOrUpdate(receivedData);

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

        if(object2Window == null){
            object2Window = new Object2Window(this, randomXList, randomYList);
        }  else {
            object2Window.setTextArea(randomXList, randomYList);
        }

    }

    public void setObject2Window(Object2Window object2Window) {
        this.object2Window = object2Window;
    }
}
