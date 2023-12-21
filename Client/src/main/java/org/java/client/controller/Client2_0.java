package org.java.client.controller;

import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2_0{

    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 33334;

        try (Socket socket = new Socket(hostName, portNumber);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner in = new Scanner(socket.getInputStream());
             Scanner userInput = new Scanner(System.in)
        ) {
            // Read and print the welcome message from the server
            System.out.println("Server: " + in.nextLine());

            while (true) {
                // Get user input
                System.out.print("CLIENT Enter a message (type 'exit' to quit): ");
                String message = userInput.nextLine();

                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", message);
                jsonObject.put("age", 30);


                // Send the message to the server
                out.println(message);

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                // Receive and print the response from the server
                String serverResponse = in.nextLine();
                System.out.println("Server: " + serverResponse);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}  /// {"Name":"Jhon","Attributes":"Play Chess"}
