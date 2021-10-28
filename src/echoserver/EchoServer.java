package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	
	// REPLACE WITH PORT PROVIDED BY THE INSTRUCTOR
	public static final int PORT_NUMBER = 6013; 
	public static void main(String[] args) throws IOException, InterruptedException {
		EchoServer server = new EchoServer();
		server.start();
	}

	private void start() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(PORT_NUMBER);
		while (true) {
			Socket client = serverSocket.accept();
			InputStream input = client.getInputStream();
			OutputStream output = client.getOutputStream();
	
			int bytesRead;
			while ((bytesRead = input.read()) != -1) {
			  output.write(bytesRead);
			  output.flush();
			}
			input.close();
			output.close();
	
			client.close();
		  }
	}
}