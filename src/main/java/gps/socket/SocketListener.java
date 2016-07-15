package gps.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener extends Thread {

	private InetAddress address;
	private Integer port;

	public SocketListener(int port, InetAddress innetAdd) {
		this.port = port;
		this.address = innetAdd;
	}

	public SocketListener() {

	}

	@Override
	public void run() {
		if(port==null){
			port=8989;
		}
		ServerSocket serverSocket = null;
		try {
			if(address == null)
			 serverSocket = new ServerSocket(port);
			else
			serverSocket = new ServerSocket(port,50,address);
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
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
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
				try {
			while (true) {
					line = brinp.readLine();
					if ((line == null) || line.equalsIgnoreCase("QUIT")) {

						return;
					} else {
						//out.writeBytes(line + "\n\r");
						//out.flush();
						System.out.println(String.format("%s-> %s",getOrigin(socket),line));
					}
			}
				} catch (IOException e) {
					e.printStackTrace();
					return;
				} finally {
					try {
						socket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		}
	}

	private String getOrigin(Socket socket) {
		return String.format("%s",socket.getRemoteSocketAddress());
	}
}