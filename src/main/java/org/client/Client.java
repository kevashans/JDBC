package org.client;

/**
 * CLIENT                                                  March 2021
 * <p>
 * This Client program asks the user to input commands to be sent to the server.
 * <p>
 * There are only two valid commands in the protocol: "Time" and "Echo"
 * <p>
 * If user types "Time" the server should reply with the current server time.
 * <p>
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 * <p>
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 * <p>
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */


import org.client.menus.PlayerMenu;
import org.client.menus.ReportMenu;
import org.client.menus.ScoutMenu;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Client client = new Client();
        start();
    }

    public static void start() {
        Scanner in = new Scanner(System.in);
        boolean exit = false;
        int command;
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
//            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
//            System.out.println("Client: Port# of Server :" + socket.getPort());
//
//            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please select menu: \n1.Player\n2.Scout\n3.Reports");


            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

//            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
            PlayerMenu menu1 = new PlayerMenu(socketReader, socketWriter);
            ScoutMenu menu2 = new ScoutMenu(socketReader, socketWriter);
            ReportMenu menu3 = new ReportMenu(socketReader, socketWriter);

            while (!exit) {
                command = in.nextInt();
                if (command == 1) {
                    menu1.setUpPlayerMenu();
                } else if (command == 2) {
                    menu2.setUpScoutMenu();
                } else if (command == 3) {
                    menu3.setUpReportMenu();
                } else if (command == 4) {
                    socketWriter.println("QUIT");
                    exit = true;
                }
            }


            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: " + e);
        }
    }
}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required