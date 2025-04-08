package codice;
// package me.ms;

import java.io.*;
import java.net.*;

/**
 *
 * @author skibidiToiletGuacho
 */
public class ServerRun {

    public static final int PORT = 1050; // porta al di fuori del range 1-1024 !

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("EchoServer: started ");
            System.out.println("Server Socket: " + serverSocket);

            while (true) {
                Socket clientSocket = serverSocket.accept();

                ServerThreads thread = new ServerThreads(clientSocket);
                thread.start();

            }


        } catch (IOException ioe) {
            System.err.println("Server error:" + ioe.getMessage());
            System.exit(1);
        }


    }
}


