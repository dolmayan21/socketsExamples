package org.java.server.controller;

import org.java.server.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ObjectServer {

    public static void main(String[] args) {
        int portNumber = 12345;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Server is waiting for client connections...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                // Handle client communication in a separate thread
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())
        ) {
            Object receivedObject;

            while ((receivedObject = in.readObject()) != null) {
                if (receivedObject instanceof Message) {
                    Message receivedMessage = (Message) receivedObject;
                    System.out.println("Received from client: " + receivedMessage.getContent());

                    // Process the message (you can customize this part based on your needs)
                    Message responseMessage = new Message("Server", "Hello, " + receivedMessage.getContent() + "!");

                    // Send the response back to the client
                    out.writeObject(responseMessage);
                }
            }

            System.out.println("Client disconnected: " + clientSocket);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
