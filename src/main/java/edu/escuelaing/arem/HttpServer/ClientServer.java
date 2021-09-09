package edu.escuelaing.arem.HttpServer;

import java.io.*;
import java.net.*;

/**
 * This class is where the client establishes connection to the server.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class ClientServer
{
    /**
     * This is the main method executed by the client that interacts with the servers.
     * @param args Client running the server.
     * @throws IOException Client error exception.
     */
    public static void main(String[] args) throws IOException 
    {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket("127.0.0.1", 35000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don’t know about host!.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn’t get I/O for " + "the connection to: localhost.");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        while ((userInput = stdIn.readLine()) != null) {
            out.println(userInput);
            System.out.println("echo: " + in.readLine());
        }
        out.close();
        in.close();
        stdIn.close();
        echoSocket.close();
    }
}