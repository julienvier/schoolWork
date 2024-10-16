package de.mineaxis.aufgabe01;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ChargenServer {

    public static void main(String[] args) {
        int port = 31001;
        int maxLines = 100000;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Chargen Server gestartet. Warte auf Verbindungen auf Port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client verbunden: " + clientSocket.getRemoteSocketAddress());

                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);

                    Random random = new Random();
                    for (int i = 0; i < maxLines; i++) {
                        StringBuilder line = new StringBuilder();
                        for (int j = 0; j < 40; j++) {
                            char randomChar = (char) (random.nextInt(26) + 'A');
                            line.append(randomChar);
                        }
                        writer.println(line.toString());
                    }

                    System.out.println("100 Zeilen zufälliger Daten an den Client gesendet.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
