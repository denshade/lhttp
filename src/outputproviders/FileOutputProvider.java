package outputproviders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

import outputproviders.statuscodes.Code200;
import outputproviders.statuscodes.Code404;

import configuration.HttpdConfiguration;

import parsers.GetParser;
import parsers.GetRequest;

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
		
		GetRequest request = GetParser.parseGetLine(firstLine);
		if (request.requestedFile.equals("/"))
		{
			request.requestedFile = "index.html";
		}
		File file = new File(httpdConfiguration.getHtDocsDirectory()
				.getAbsolutePath()
				+ File.separator
				+ request.requestedFile);
		if (file.exists() && file.isFile()) {
			
			out.write(Code200.getMessage() + "\n");
			out.write("Accept-Ranges: bytes\n");
			out.write("Content-Length: " + file.length() + "\n");
			out.write("Connection: close\n");
			out.write("Content-Type: text/html\n\n");
			BufferedReader reader = new BufferedReader(new FileReader(
					file));
			String readLine = reader.readLine();
			while (readLine != null) {
				out.write(readLine);
				readLine = reader.readLine();
			}
			out.write("\n");
			//reader.close();
			//input.close();
			
		} else {
			out.write(Code404.getMessage() + "\n\n");
		}
		out.flush();
		//out.close();
	}
	
}
