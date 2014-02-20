package server;

import java.net.ServerSocket;

import configuration.HttpdConfiguration;


public class HttpServer 
{
	public static void main(String[] args)
	{
				try {
						System.out.println("Listening");
						int portNumber = 4444;
						ServerSocket serverSocket = new ServerSocket(portNumber);
						while(true)
						{
					    	new HttpServerThread(serverSocket.accept(), new HttpdConfiguration()).start();
					    }
				} catch (Exception e)
				{
						System.out.println(e.getMessage());
				}
			}
	
}