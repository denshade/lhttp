package server.serverthread.outputproviders;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;


import configuration.HttpdConfiguration;

import parsers.GetParser;
import parsers.GetRequest;
import server.serverthread.outputproviders.filetransformer.BinaryFileWriter;
import server.serverthread.outputproviders.filetransformer.PlainTextWriter;
import server.serverthread.outputproviders.statuscodes.Code200;
import server.serverthread.outputproviders.statuscodes.Code400;
import server.serverthread.outputproviders.statuscodes.Code404;

public class FileOutputProvider implements OutputProvider
{
	private HttpdConfiguration httpdConfiguration;
	
	public FileOutputProvider(HttpdConfiguration configuration)
	{
		httpdConfiguration = configuration;
	}
	
	@Override
	public void process(InputStream input, OutputStream outputStream) throws IOException 
	{
		PrintWriter out = new PrintWriter(outputStream, true);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				input));
		
		String firstLine = in.readLine();
		if (firstLine == null) {
			return;
		}
		try{
			GetRequest request = GetParser.parseGetLine(firstLine);
			
		if (request.requestedFile.equals("/"))
		{
			request.requestedFile = "index.html";
		}
		File file = new File(httpdConfiguration.getHttpDocuments()
				.getAbsolutePath()
				+ File.separator
				+ request.requestedFile);
		if (file.exists() && file.isFile()) {
			
			out.write(Code200.getMessage() + "\n");
			out.write("Accept-Ranges: bytes\n");
			out.write("Content-Length: " + file.length() + "\n");
			out.write("Connection: close\n");
			if (file.getName().endsWith(".html"))
			{
				PlainTextWriter writer = new PlainTextWriter(file);
				writer.process(out);
			} else if (file.getName().endsWith(".png"))
			{
				BinaryFileWriter writer = new BinaryFileWriter(file);
				writer.process(out, "image/png");
			}
		} else {
			out.write(Code404.getMessage() + "\n\n");
		}
		} catch(IllegalArgumentException ex)
		{
			out.write(Code400.getMessage() + "\n\n");
		}
		out.flush();
	}
	
}
