package server.serverthread.outputproviders.filetransformer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.bind.DatatypeConverter;

public class BinaryFileWriter 
{
	
	private File source;
	
	public BinaryFileWriter(File source)
	{
		this.source = source;
	}
	
	public void process(PrintWriter outPrinter, String type) throws IOException
	{
		outPrinter.write("Content-Type: "+ type + "\n\n");
		
		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(
				source));
		
		byte[] source = new byte[65536];
		int readBytes = reader.read(source);
		while (readBytes != 0) {
			
			outPrinter.write(DatatypeConverter.printBase64Binary(source));
			readBytes = reader.read(source);
		}
		outPrinter.write("\n\n");
		reader.close();		
	}
}
