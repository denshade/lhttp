package server;

import java.io.IOException;
import java.net.Socket;

import outputproviders.FileOutputProvider;
import outputproviders.OutputProvider;

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
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
