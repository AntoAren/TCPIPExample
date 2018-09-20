package com.pintusov;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {

    public static void main(String[] args) throws Exception {
        String message;
        try (Socket client = new Socket("0.0.0.0", 9999)) {
            Scanner scanner = new Scanner(System.in);
            PrintWriter out = new PrintWriter(client.getOutputStream());
            do {
                message = scanner.nextLine();
                out.println(message);
                out.flush();
                System.out.println(message + " has been sent.");
            } while (!message.equalsIgnoreCase("stop"));
        }
    }
}
