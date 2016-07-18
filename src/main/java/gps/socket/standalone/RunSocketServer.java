package gps.socket.standalone;

import gps.socket.SocketListener;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;

public class RunSocketServer {

    public static void main(String[] args) throws IOException {
        System.out.println("Enter q to exit : ");
        System.out.println("Arrays : " + Arrays.toString(args));

        String s = null;
        Integer port = args.length < 1 ? null : Integer.parseInt(args[0]);
        InetAddress innetAdd = args.length < 2 ? null :
                InetAddress.getByName(args[1]);
        SocketListener socketListener = new SocketListener(port, innetAdd);
        socketListener.start();
        while (!"q".equalsIgnoreCase(s)) {
            try {
                //BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                //s = bufferRead.readLine();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
