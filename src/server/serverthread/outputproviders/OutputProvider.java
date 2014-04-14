package server.serverthread.outputproviders;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface OutputProvider 
{
	public void process(InputStream input, OutputStream out) throws IOException;
}
