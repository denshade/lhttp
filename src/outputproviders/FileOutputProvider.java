package outputproviders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

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
			out.write("HTTP-1.1 200 OK\n\n");
			BufferedReader reader = new BufferedReader(new FileReader(
					file));
			String readLine = reader.readLine();
			while (readLine != null) {
				out.write(readLine);
				readLine = reader.readLine();
			}
			reader.close();

		} else {
			out.write("HTTP-1.1 404 OK\n\n");
		}
		out.flush();
	}
	
}
