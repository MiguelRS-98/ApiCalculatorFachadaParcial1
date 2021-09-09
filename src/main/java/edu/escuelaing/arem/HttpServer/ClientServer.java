package edu.escuelaing.arem.HttpServer;

import edu.escuelaing.arem.app.trigcalculator.TrigCalculator;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is where the client establishes connection to the server.
 * @author Miguel Angel Rodriguez Siachoque
 */
public class ClientServer
{
    /**
     * This method starts the HTTP server.
     * @param args Item to be displayed by the server.
     * @throws IOException Exception of a server malfunction.
     */
    public void startServer (String[] args) throws IOException 
    {
        int port = getPort();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port);
            System.exit(1);
        }
        Socket clientSocket = null;
        boolean running = true;
        while (running) {
            try {
                System.out.println("Listo para recibir en puerto: " + port);
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            proccessRequest(clientSocket);
        }
        serverSocket.close();
    }
    /**
     * This method perform the server request process and displays its header, received and request.
     * @param clientSocket Client that wants to run the HTTP server.
     * @throws IOException Exception in the malfunction of some request.
     */
    public void proccessRequest (Socket clientSocket) throws IOException
    {
        BufferedReader in;
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            String method = "";
            String path = "";
            String version = "";
            List<String> headers = new ArrayList<String>();
            while ((inputLine = in.readLine()) != null) {
                if (method.isEmpty()){
                    String[] requestStrings = inputLine.split(" ");
                    method = requestStrings[0];
                    path = requestStrings[1];
                    version = requestStrings[2];
                    System.out.println("Request: " + method + " " + path + " " + version);
                }
                else {
                    System.out.println("Header: " + inputLine);
                    headers.add(inputLine);
                }
                System.out.println("Received: " + inputLine);
                if (!in.ready()) {
                    break;
                }
            }
            String data = path.substring(1, path.length());
            String[] newPath = data.split("-");
            String operation = newPath[0];
            String number = newPath[1];
            double newNumber = Double.parseDouble(number);
            String responseMsg = processRequest(operation, newNumber);
            out.println(responseMsg);
        }
        in.close();
        clientSocket.close();
    }
    public static String processRequest (String op, double number) 
    {
        String answer = "";
        if (op.equals("sin") || op.equals("cos") || op.equals("tan")) {
            switch (op) {
                case "sin":
                    answer = TrigCalculator.getSin(number);
                    break;
                case "cos":
                    answer = TrigCalculator.getCos(number);
                    break;
                case "tan":
                    answer = TrigCalculator.getTan(number);
                    break;
                default:
                    answer = "No se puede realizar.";
                    break;
            }
        }
        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: application/json" + 
                "\r\n"+ answer;
    }
    /**
     * This method get the port.
     * @return the port.
     */
    public static int getPort () 
    {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}