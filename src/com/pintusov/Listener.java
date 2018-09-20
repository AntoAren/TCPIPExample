package com.pintusov;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Listener implements Runnable {

    private Socket connection;

    public Listener(Socket connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        String clientMessage;
        Integer messageNumber = 0;
        try {
            BufferedReader serverReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            clientMessage = serverReader.readLine();
            while (!"stop".equalsIgnoreCase(clientMessage)) {
                System.out.println("Received message: '" + clientMessage + "', message number = " + ++messageNumber);
                clientMessage = serverReader.readLine();
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        } finally {
            try {
                connection.close();
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
