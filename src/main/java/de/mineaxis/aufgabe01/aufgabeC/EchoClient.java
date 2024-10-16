package de.mineaxis.aufgabe01.aufgabeC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EchoClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 31002;

        try (Socket socket = new Socket(host, port);
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            System.out.println("Mit dem Echo-Server auf Port " + port + " verbunden.");
            System.out.println("Geben Sie eine Nachricht ein. Tippen Sie 'exit', um die Verbindung zu beenden.");

            String userInput;
            while (true) {
                userInput = consoleInput.readLine();

                if ("exit".equalsIgnoreCase(userInput)) {
                    System.out.println("Verbindung zum Server wird beendet.");
                    break;
                }

                output.println(userInput);

                String serverResponse = input.readLine();
                System.out.println("Echo vom Server: " + serverResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
