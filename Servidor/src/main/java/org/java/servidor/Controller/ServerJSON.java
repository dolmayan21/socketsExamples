package org.java.servidor.Controller;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerJSON {
    public static void main(String[] args) {
        final int portNumber = 3333;

        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            System.out.println("Server is listening on port " + portNumber);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from client");

                // Create a new thread to handle the client
                new Thread(() -> handleClient(clientSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Read JSON data from the client
            String jsonStr = in.readLine();
            System.out.println("Received JSON from client: " + jsonStr);


            while(!jsonStr.equals("Over")){
            // Parse JSON
            JSONObject jsonObject = new JSONObject(jsonStr);

            // Process JSON data
            String name = jsonObject.getString("name");
            int age = jsonObject.getInt("age");

            // Example processing
            String response = "Hello, " + name + "! You are " + age + " years old.";

            // Send response back to the client
            out.println(response);
            }
            // Close resources
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

