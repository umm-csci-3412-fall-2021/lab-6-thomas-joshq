package echoserver;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
  
public class EchoServer {

	public static final int PORT_NUMBER = 6013; 
    public static void main(String[] args)
    {
        ServerSocket serverSocket = null;
  
        try {
  
            serverSocket = new ServerSocket(PORT_NUMBER);
            serverSocket.setReuseAddress(true);
  
            while (true) {
                Socket client = serverSocket.accept();
                ClientHandler clientSock = new ClientHandler(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class ClientHandler implements Runnable {
	private final Socket clientSocket;
	public ClientHandler(Socket socket)
	{
		this.clientSocket = socket;
	}

	public void run()
	{
		try {
			OutputStream out = clientSocket.getOutputStream();
			InputStream in = clientSocket.getInputStream();

			int bytesRead;
			while ((bytesRead = in.read()) != -1) {
			  out.write(bytesRead);
			  out.flush();
			}
			clientSocket.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
