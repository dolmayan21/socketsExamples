package org.java.cliente.controller;

import org.java.cliente.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ObjectClient {

    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 12345;

        try (Socket socket = new Socket(hostName, portNumber);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())
        ) {
            // Create a scanner for user input
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter your message (type 'exit' to quit): ");
                String userMessage = scanner.nextLine();

                System.out.println(userMessage);

                if ("exit".equalsIgnoreCase(userMessage)) {
                    break;
                }

                // Create a Message object with a string content
                Message message = new Message("Client", userMessage);

                // Send the Message object to the server
                out.writeObject(message);

                // Receive and print the response from the server
                Message responseMessage = (Message) in.readObject();
                System.out.println("Server response: " + responseMessage.getContent());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
