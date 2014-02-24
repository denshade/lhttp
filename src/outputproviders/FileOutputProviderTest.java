package outputproviders;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;

import org.junit.Test;

import configuration.HttpdConfiguration;

public class FileOutputProviderTest {

	@Test
	public void verify404IsGiven() throws IOException {
		HttpdConfiguration configuration = new HttpdConfiguration();
		configuration.setHttpDocuments(new File("/tmp"));
		FileOutputProvider provider = new FileOutputProvider(configuration);
		String inputString = "GET /noExisto HTTP/1.1";
		InputStream input = new ByteArrayInputStream(inputString.getBytes());
		OutputStream outputStream = new ByteArrayOutputStream();
		provider.process(input, outputStream);
		String response = outputStream.toString();
		assertEquals("HTTP-1.1 404 Not Found\n\n", response);
	}
	
	/**
	 * If the file exists we must return the file.
	 * @throws IOException
	 */
	@Test
	public void verify200IsGiven() throws IOException {
		HttpdConfiguration config = new HttpdConfiguration();
		File f = File.createTempFile("hello", ".html");
		Writer writer = new FileWriter(f);
		writer.write("<html>ow hai</html>");
		writer.close();
		File directory = f.getParentFile();
		config.setHttpDocuments(directory);
		FileOutputProvider provider = new FileOutputProvider(config);
		String inputString = "GET /" + f.getName() + " HTTP/1.1";
		InputStream input = new ByteArrayInputStream(inputString.getBytes());
		OutputStream outputStream = new ByteArrayOutputStream();
		provider.process(input, outputStream);
		String response = outputStream.toString();
		assertEquals("HTTP-1.1 200 OK\nAccept-Ranges: bytes\nContent-Length: 19\nConnection: close\nContent-Type: text/html\n\n<html>ow hai</html>\n\n", response);
	}
	

	/**
	 * If the file exists we must return the file.
	 * @throws IOException
	 */
	@Test
	public void verify400IsGiven() throws IOException {
		HttpdConfiguration config = new HttpdConfiguration();
		File f = File.createTempFile("hello", ".html");
		Writer writer = new FileWriter(f);
		writer.write("<html>ow hai</html>");
		writer.close();
		File directory = f.getParentFile();
		config.setHttpDocuments(directory);
		FileOutputProvider provider = new FileOutputProvider(config);
		String inputString = "MAF /" + f.getName() + " HTTP/1.1";
		InputStream input = new ByteArrayInputStream(inputString.getBytes());
		OutputStream outputStream = new ByteArrayOutputStream();
		provider.process(input, outputStream);
		String response = outputStream.toString();
		assertEquals("HTTP-1.1 400 Bad Request\n\n", response);
	}
}
