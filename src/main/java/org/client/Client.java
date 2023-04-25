package org.client;

/** CLIENT                                                  March 2021
 *
 * This Client program asks the user to input commands to be sent to the server.
 *
 * There are only two valid commands in the protocol: "Time" and "Echo"
 *
 * If user types "Time" the server should reply with the current server time.
 *
 * If the user types "Echo" followed by a message, the server will echo back the message.
 * e.g. "Echo Nice to meet you"
 *
 * If the user enters any other input, the server will not understand, and
 * will send back a message to the effect.
 *
 * NOte: You must run the server before running this the client.
 * (Both the server and the client will be running together on this computer)
 */


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.DTOs.Player;
import org.DTOs.Scout;
import org.client.menus.playerMenu;
import org.core.Packet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class Client
{
    public static void main(String[] args)
    {
        Client client = new Client();
        client.start();
    }

    public void start()
    {
        Scanner in = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 8080);  // connect to server socket
            System.out.println("Client: Port# of this client : " + socket.getLocalPort());
            System.out.println("Client: Port# of Server :" + socket.getPort() );

            System.out.println("Client message: The Client is running and has connected to the server");

            System.out.println("Please enter a command:  (\"Time\" to get time, or \"Echo message\" to get echo) \n>");
//            String command = in.nextLine();

            OutputStream os = socket.getOutputStream();
            PrintWriter socketWriter = new PrintWriter(os, true);   // true => auto flush buffers

//            socketWriter.println(command);

            Scanner socketReader = new Scanner(socket.getInputStream());  // wait for, and retrieve the reply
             playerMenu menu1 = new playerMenu(socketReader,socketWriter);
             menu1.setUpPlayerMenu();

            ////SERVER REPLY
//            if (command.startsWith("FIND_PLAYER_BY_ID"))
//            {
//                String input = socketReader.nextLine();
//                Player p3 = new Gson().fromJson(input, Player.class);
//                System.out.println(p3);
//            }else if(command.startsWith("FIND_ALL_PLAYERS"))
//            {
//                Type list = new TypeToken<List<Player>>(){}.getType();
//                String input = socketReader.nextLine();
//                List<Player> playerArray = new Gson().fromJson(input, list);
//                System.out.println(playerArray);
//            }else if(command.startsWith("FIND_SCOUT_BY_ID"))
//            {
//                String input = socketReader.nextLine();
//                Scout s = new Gson().fromJson(input, Scout.class);
//                System.out.println(s);
//            }else if(command.startsWith("FIND_ALL_SCOUTS"))
//            {
//                Type list = new TypeToken<List<Scout>>(){}.getType();
//                String input = socketReader.nextLine();
//                List<Scout> scoutArray = new Gson().fromJson(input, list);
//                System.out.println(scoutArray);
//            }else if(command.startsWith("FIND_ALL_SCOUTS"))
//            {
//                Type list = new TypeToken<List<Scout>>(){}.getType();
//                String input = socketReader.nextLine();
//                List<Scout> scoutArray = new Gson().fromJson(input, list);
//                System.out.println(scoutArray);
//            }else{
//                System.out.println("command not found");
//            }


            socketWriter.close();
            socketReader.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("Client message: IOException: "+e);
        }
    }
}


//  LocalTime time = LocalTime.parse(timeString); // Parse timeString -> convert to LocalTime object if required