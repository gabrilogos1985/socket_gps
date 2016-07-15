package gps.socket.standalone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Arrays;

import gps.socket.SocketListener;

public class RunSocketServer {

	public static void main(String[] args) throws IOException {
		System.out.println("Enter q to exit : ");
		System.out.println("Arrays : "+ Arrays.toString(args));

		String s = null;
		new SocketListener(args.length<1?null:Integer.parseInt(args[0]), InetAddress.getByName(args[1])).start();
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
