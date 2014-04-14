package server;

import java.io.IOException;
import java.net.Socket;

import server.serverthread.outputproviders.FileOutputProvider;
import server.serverthread.outputproviders.OutputProvider;


import configuration.HttpdConfiguration;


public class HttpServerThread extends Thread {
	private Socket socket = null;

	private HttpdConfiguration httpdConfiguration;

	public HttpServerThread(Socket socket, HttpdConfiguration httpdConfiguration) {
		super("KKMultiServerThread");
		this.socket = socket;
		this.httpdConfiguration = httpdConfiguration;
	}

	public void run() {
		try {
			OutputProvider provider = new FileOutputProvider(httpdConfiguration);
			provider.process(socket.getInputStream(), socket.getOutputStream());
			Thread.sleep(100); //Why do I have to do this?
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
