package de.mineaxis.aufgabe02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class DateTimeClient {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 31000;

        try (Socket socket = new Socket(host, port)) {
            System.out.println("Verbunden mit dem Server auf Port " + port);

            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String serverResponse = input.readLine();

            System.out.println("Serverantwort: " + serverResponse);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
