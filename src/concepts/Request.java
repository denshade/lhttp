package concepts;

import java.io.InputStream;

import concepts.request.RequestLine;

public class Request extends GenericMessage
{
	public Request(Header[] headers, RequestLine requestLine, InputStream stream) {
		super(headers);
		this.requestLine = requestLine;
		this.stream = stream;
	}

	private RequestLine requestLine;
	
	private InputStream stream;

	public RequestLine getRequestLine() {
		return requestLine;
	}

	public void setRequestLine(RequestLine requestLine) {
		this.requestLine = requestLine;
	}
	
	public InputStream getBody()
	{
		return stream;
	}
}
