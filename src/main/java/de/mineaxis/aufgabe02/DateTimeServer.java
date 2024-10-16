package de.mineaxis.aufgabe02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeServer {

    public static void main(String[] args) {
        int port = 31000;
        final boolean[] running = {true};

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server gestartet. Warte auf Verbindungen auf Port " + port);

            BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

            new Thread(() -> {
                try {
                    while (running[0]) {
                        String command = consoleInput.readLine();
                        if (command.equalsIgnoreCase("q")) {
                            running[0] = false;
                            System.out.println("Server wird beendet...");
                            serverSocket.close();
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();

            while (running[0]) {
                try (Socket clientSocket = serverSocket.accept()) {
                    LocalDateTime lDT = LocalDateTime.now();
                    String dateTimeStr = lDT.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));

                    OutputStream outputStream = clientSocket.getOutputStream();
                    PrintWriter writer = new PrintWriter(outputStream, true);
                    writer.println("Aktuelles Datum und Uhrzeit: " + dateTimeStr);

                    System.out.println("Datum und Uhrzeit an Client gesendet: " + dateTimeStr);
                } catch (Exception e) {
                    if (running[0]) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Server gestoppt.");
    }
}
