package org.java.client.controller;

import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLOutput;

public class ClientJSON {


    public static void main(String[] args) throws IOException {
        final String serverAddress = "localhost";
        final int portNumber = 3333;

        BufferedReader in = null;
        PrintWriter out = null;

        Socket socket = null;

        DataInputStream inputTerminal =null;
        DataOutputStream outputTerminal = null;

        try {
            socket = new Socket(serverAddress, portNumber);
            System.out.println("Connected to the server");



        }catch (UnknownHostException u){
            System.out.println(u);
        }
        catch(IOException i){
            System.out.println(i);
            return;
        }

        inputTerminal = new DataInputStream(System.in);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);

            // Create a JSON object to send to the server
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "Alice");
            jsonObject.put("age", 30);

            while(!jsonObject.toString().equals("over")) {
                // Send JSON data to the server
                out.println(jsonObject.toString());

                // Receive and print the response from the server
                String response = in.readLine();
                System.out.println("Server response: " + response);
            }


        try{
            // Close resources
            in.close();
            out.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
