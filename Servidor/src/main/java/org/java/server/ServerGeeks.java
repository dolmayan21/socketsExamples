package org.java.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerGeeks {
    //initialize socket and input stream
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in =  null;

    private DataInputStream inServer =  null;

    private DataOutputStream out = null;

    private DataOutputStream outServer = null;

    // constructor with port
    public ServerGeeks(int port)
    {
        // starts server and waits for a connection
        try
        {
            server = new ServerSocket(port);
            System.out.println("Server started" + server);

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");




            // takes input from the client socket
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            inServer = new DataInputStream(System.in);

            outServer = new DataOutputStream(socket.getOutputStream());

            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println( "****" + line);
/*
                    in = new DataInputStream(System.in);

                    System.out.println(in.toString());
                    System.out.println(in.readUTF());
                    out = new DataOutputStream(socket.getOutputStream());
*/
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        ServerGeeks server = new ServerGeeks(5000);
    }
}