package test;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Gabe on 05/07/2016.
 */
public class TestSocket {

    public static void main(String... args) {
        ServerSocket MyService;
        try {
            ServerSocket socket = new ServerSocket(8989);
            Socket clientSocket = null;
            try {
                Socket serviceSocket = socket.accept();
                DataInputStream is = new DataInputStream(clientSocket.getInputStream());
                PrintStream os = new PrintStream(clientSocket.getOutputStream());
// As long as we receive data, echo that data back to the client.
                System.out.println("Waiting...");
                while (true) {
                    String line = is.readLine();
                    os.println(line);
                }
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }

    @org.junit.Test
    public void name() throws Exception {
        System.out.println(new StringBuilder("\u0020").append("-"));

    }
}
