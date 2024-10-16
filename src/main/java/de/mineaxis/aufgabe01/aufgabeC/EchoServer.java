package de.mineaxis.aufgabe01.aufgabeC;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    public static void main(String[] args) {
        int port = 31002;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo Server gestartet. Warte auf Verbindungen auf Port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client verbunden: " + clientSocket.getRemoteSocketAddress());

                    BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

                    String receivedLine;
                    while ((receivedLine = input.readLine()) != null) {
                        System.out.println("Empfangen: " + receivedLine);
                        output.println(receivedLine); // Echo an den Client
                    }

                    System.out.println("Verbindung zum Client geschlossen.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
