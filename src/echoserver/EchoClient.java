package echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.io.*;
import java.net.*;

import javax.sound.sampled.SourceDataLine;

public class EchoClient{
	public static final int PORT_NUMBER = 6013;


	public static void main(String[] args) throws IOException {
		String server;
		// Use "127.0.0.1", i.e., localhost, if no server is specified.
		if (args.length == 0) {
		  server = "127.0.0.1";
		} else {
		  server = args[0];
		}
	
		try {
		  // Connect to the server
			Socket socket = new Socket(server, PORT_NUMBER);		
			InputStream socketInputStream = socket.getInputStream();
			OutputStream socketOutputStream = socket.getOutputStream();
		
			Thread1 obj = new Thread1(socketOutputStream, socket);
			Thread2 obj2 = new Thread2(socketInputStream, socket);

			obj.start();
			obj2.start();

		} catch (ConnectException ce) {
		System.out.println("We were unable to connect to " + server);
		System.out.println("You should make sure the server is running.");
	  	} catch (IOException ioe) {
		System.out.println("We caught an unexpected exception");
		System.err.println(ioe);
	  	}
	}
		


	static class Thread1 extends Thread{
		final OutputStream streamOutput;
		Socket socket;
		public Thread1(OutputStream output, Socket socket){
			streamOutput = output;
			this.socket = socket;
		}
		
		public void run(){
			int bytesRead;
			try {
				while ((bytesRead = System.in.read()) != -1) {
					streamOutput.write(bytesRead);
					streamOutput.flush();
				}
				socket.shutdownOutput();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static class Thread2 extends Thread{
		final InputStream streamIn;
		Socket socket;
		public Thread2(InputStream input, Socket socket){
			streamIn = input;
			this.socket = socket;
		}
		public void run(){
			try {
				int output;
				while ((output = streamIn.read()) != -1){			
					System.out.write(output);
					System.out.flush();
				}
				socket.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}