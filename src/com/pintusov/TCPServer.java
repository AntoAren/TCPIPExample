package com.pintusov;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class TCPServer {

    private static final Set<String> connections = new HashSet<>();

    public static void main(String[] args) throws Exception {
        try (ServerSocket server = new ServerSocket(9999)) {
            while (true) {
                Socket connection = server.accept();
                String ipAddress = connection.getInetAddress().getHostAddress();
                if (!connections.contains(ipAddress)) {
                    connections.add(ipAddress);
                    Thread listener = new Thread(new Listener(connection));
                    listener.start();
                } else {
                    System.out.println("Connection to " + ipAddress + " has been rejected. It has already been connected.");
                    connection.close();
                }
            }
        }
    }
}
