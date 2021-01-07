package bandsurf;

import java.io.*;
import java.net.*;
import java.sql.Timestamp;
 
public class TimeServer {
    public static void main(String args[]) {
        int port = 81;
        try {
        	port = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Argument" + args[0] + " must be an integer.");
            System.exit(1);
        }
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
 
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");
 
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                
                String ip = socket.getRemoteSocketAddress().toString();
                System.out.println(ip);
                
                writer.println(timestamp.getTime());
 
                socket.close();
            }
 
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}