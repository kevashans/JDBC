package org.server;

/**
 * SERVER  - MULTITHREADED                                         March 2021
 * <p>
 * Server accepts client connections, creates a ClientHandler to handle the
 * Client communication, creates a socket and passes the socket to the handler,
 * runs the handler in a separate Thread.
 * <p>
 * <p>
 * The handler reads requests from clients, and sends replies to clients, all in
 * accordance with the rules of the protocol. as specified in
 * "ClientServerBasic" sample program
 * <p>
 * The following PROTOCOL is implemented:
 * <p>
 * If ( the Server receives the request "Time", from a Client ) then : the
 * server will send back the current time
 * <p>
 * If ( the Server receives the request "Echo message", from a Client ) then :
 * the server will send back the message
 * <p>
 * If ( the Server receives the request it does not recognize ) then : the
 * server will send back the message "Sorry, I don't understand"
 * <p>
 * This is an example of a simple protocol, where the server's response is based
 * on the client's request.
 * <p>
 * Each client is handled by a ClientHandler running in a separate worker Thread
 * which allows the Server to continually listen for and handle multiple clients
 */


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.core.Packet;
import org.json.JSONObject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLOutput;
import java.time.LocalTime;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        try {
            ServerSocket ss = new ServerSocket(8080);  // set up ServerSocket to listen for connections on port 8080

            System.out.println("Server: Server started. Listening for connections on port 8080...");

            int clientNumber = 0;  // a number for clients that the server allocates as clients connect

            while (true)    // loop continuously to accept new client connections
            {
                Socket socket = ss.accept();    // listen (and wait) for a connection, accept the connection,
                // and open a new socket to communicate with the client
                clientNumber++;

                System.out.println("Server: Client " + clientNumber + " has connected.");

                System.out.println("Server: Port# of remote client: " + socket.getPort());
                System.out.println("Server: Port# of this server: " + socket.getLocalPort());

                Thread t = new Thread(new ClientHandler(socket, clientNumber)); // create a new ClientHandler for the client,
                t.start();                                                  // and run it in its own thread

                System.out.println("Server: ClientHandler started in thread for client " + clientNumber + ". ");
                System.out.println("Server: Listening for further connections...");
            }
        } catch (IOException e) {
            System.out.println("Server: IOException: " + e);
        }
        System.out.println("Server: Server exiting, Goodbye!");
    }

    public class ClientHandler implements Runnable   // each ClientHandler communicates with one Client
    {
        BufferedReader socketReader;
        PrintWriter socketWriter;
        Socket socket;
        int clientNumber;

        public ClientHandler(Socket clientSocket, int clientNumber) {
            try {
                InputStreamReader isReader = new InputStreamReader(clientSocket.getInputStream());
                this.socketReader = new BufferedReader(isReader);

                OutputStream os = clientSocket.getOutputStream();

                this.socketWriter = new PrintWriter(os); // true => auto flush socket buffer

                this.clientNumber = clientNumber;  // ID number that we are assigning to this client

                this.socket = clientSocket;  // store socket ref for closing

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;
            Packet incomingPacket = new Packet("", null);
            Packet response = null;
            boolean run = true;

            try {
            while (!incomingPacket.getCommand().equals("QUIT")) {
                    ////packet data from the client in the form of json is read into the "incoming packet"
                    incomingPacket.readJson(new JSONObject(socketReader.readLine()));
                    System.out.println("Received message " + incomingPacket);

                    CommandFactory factory = new CommandFactory();
                    ////show what command the server will exceute
//                    socketWriter.println(incomingPacket.getCommand());
                    ////create command object
                    Command command = factory.createCommand(incomingPacket.getCommand());
                    if (command != null) {
                        ///execute command
                        response = command.createResponse(incomingPacket);
                    }
                    ////transfer data to the client in the form of jsonstring
                    if (response != null){
                    socketWriter.println(response.writeJSON());
                    }
                    socketWriter.flush();

                }
                socket.close();
            }
            catch (SocketException ex) {
                System.out.println("Server: (ClientHandler): SocketException for Client " + clientNumber + ": " + ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.out.println("Server: (ClientHandler): Handler for Client " + clientNumber + " is terminating .....");
        }

    }
}

