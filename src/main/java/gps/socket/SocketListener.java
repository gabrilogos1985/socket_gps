package gps.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener extends Thread {

	public SocketListener() {
		// final PrintStream os = new
		// PrintStream(serviceSocket.getOutputStream());
	}

	@Override
	public void run() {
		ServerSocket serverSocket = null;
		try {
			 serverSocket = new ServerSocket(8989);
			while (true) {
				try {
					Socket serviceSocket = serverSocket.accept();

					// new threa for a client
					new EchoThread(serviceSocket).start();
				} catch (IOException e) {
					System.out.println("I/O error: " + e);
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	class EchoThread extends Thread {
		protected Socket socket;

		public EchoThread(Socket clientSocket) {
			this.socket = clientSocket;
		}

		public void run() {
			InputStream inp = null;
			BufferedReader brinp = null;
			DataOutputStream out = null;
			try {
				inp = socket.getInputStream();
				brinp = new BufferedReader(new InputStreamReader(inp));
				//out = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				return;
			}
			String line;
			while (true) {
				try {
					line = brinp.readLine();
					if ((line == null) || line.equalsIgnoreCase("QUIT")) {
						socket.close();
						return;
					} else {
						//out.writeBytes(line + "\n\r");
						//out.flush();
						System.out.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		}
	}
}