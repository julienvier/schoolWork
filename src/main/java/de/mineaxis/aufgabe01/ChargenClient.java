package de.mineaxis.aufgabe01;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChargenClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 31001;
        int linesToReceive = 20;
        String outputFilePath = "output.txt";

        try (Socket socket = new Socket(host, port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             FileWriter fileWriter = new FileWriter(outputFilePath)) {

            System.out.println("Mit dem Chargen-Server auf Port " + port + " verbunden.");

            for (int i = 0; i < linesToReceive; i++) {
                String line = input.readLine();
                    System.out.println("Empfangene Zeile: " + line);
                    fileWriter.write(line + System.lineSeparator());
            }

            System.out.println("20 Zeilen empfangen und in '" + outputFilePath + "' gespeichert.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
