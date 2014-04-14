package server;

import java.io.File;
import java.net.ServerSocket;
import java.net.Socket;

import configuration.HttpdConfiguration;


public class HttpServer 
{
	public static void main(String[] args)
	{
				try {
						System.out.println("Listening");
						int portNumber = 4444;
						ServerSocket serverSocket = new ServerSocket(portNumber);
						HttpdConfiguration config = new HttpdConfiguration();
						config.setHttpDocuments(new File("/tmp"));
						while(true)
						{
							Socket socket = serverSocket.accept();
					    	new HttpServerThread(socket, config).start();
					    }
				} catch (Exception e)
				{
						System.out.println(e.getMessage());
				}
			}
	
}
