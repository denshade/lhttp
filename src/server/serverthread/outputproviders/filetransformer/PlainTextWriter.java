package server.serverthread.outputproviders.filetransformer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class PlainTextWriter 
{

	private File source;
	
	public PlainTextWriter(File source)
	{
		this.source = source;
	}
	
	public void process(PrintWriter outPrinter) throws IOException
	{
		outPrinter.write("Content-Type: text/html\n\n");
		BufferedReader reader = new BufferedReader(new FileReader(
				source));
		String readLine = reader.readLine();
		while (readLine != null) {
			outPrinter.write(readLine);
			readLine = reader.readLine();
		}
		outPrinter.write("\n\n");
		reader.close();		
	}
	
}
