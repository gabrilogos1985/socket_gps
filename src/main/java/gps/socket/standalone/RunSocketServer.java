package gps.socket.standalone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import gps.socket.SocketListener;

public class RunSocketServer {

	public static void main(String[] args) throws IOException {
		System.out.println("Enter q to exit : ");
		String s = null;
		while (!"q".equalsIgnoreCase(s)) {
			 new SocketListener().start();
			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				s = bufferRead.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
