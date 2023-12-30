package object2;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Object_2 implements Runnable{

    private String receivedDatas;
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(12345); // Порт для прослушивания

            while (true) {
                Socket clientSocket = serverSocket.accept();

                System.out.println("Подключен клиент: " + clientSocket.getInetAddress());

                handleClient(clientSocket);

                // Закрываем соединение с клиентом после обработки данных
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Object2Window();

    }

    private static void handleClient(Socket clientSocket) {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            //OutputStream outputStream = clientSocket.getOutputStream();

            // Пример чтения данных от клиента
            byte[] buffer = new byte[1024];
            int bytesRead;
            String receivedData = "";
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                receivedData = new String(buffer, 0, bytesRead);

                //System.out.println("Получены данные от клиента: " + receivedData);
                new Object2Window();
                // Обработка данных и отправка ответа клиенту
                /*String responseData = "Данные получены успешно";
                outputStream.write(responseData.getBytes());*/
            }
            System.out.println("Получены данные от клиента: " + receivedData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
