package parsers;

import java.io.InputStream;

import concepts.Header;
import concepts.Request;
import concepts.request.RequestLine;

public class RequestParser 
{
	public Request parseRequest(InputStream stream)
	{
		RequestLine requestLine = new RequestLine();
		Header[] headers = new Header[0];
		Request request = new Request(headers, requestLine, stream);
		
		return request;
		
	}
		
}
