package org.java.servidor.Controller;

import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server2_0 {

    private static final Integer PORT_NUMBER = 33334;
    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
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
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Scanner in = new Scanner(clientSocket.getInputStream());
                Scanner userInput = new Scanner(System.in);
        ) {

            out.println("Welcome to the server!");

            while (true) {
                String clientMessage = in.nextLine();

                System.out.println(" ****   CLIENT MESSAGE *****\t\t" + clientMessage);

                if ("exit".equalsIgnoreCase(clientMessage)) {
                    break;
                }

                /** Once we have a clear idea about what we gonna send back
                 * We'll avoid this part below...
                 */
                String message = null;
               // System.out.print("SERVER Enter a message (type 'exit' to quit): ");
                //String message = userInput.nextLine();
                // Process the message as needed
                /**
                 *  Finish part
                 */
                    if(!clientMessage.contains("json")){
                        message = "Json not correct !!!!";
                    }else{
                        message = "Thats right!!! ";
                    }
                // Send a response back to the client
                out.println("Server pin ---->  " + message); //clientMessage);
            }

            System.out.println("Client disconnected: " + clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
