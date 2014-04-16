package concepts;

import java.io.OutputStream;

import concepts.response.StatusLine;

public class Response extends GenericMessage
{
	
	public Response(Header[] headers, StatusLine line, OutputStream stream) {
		super(headers);
		this.statusLine = line;
		this.stream = stream;
		
	}
	private OutputStream stream;
	
	private StatusLine statusLine;

	public StatusLine getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(StatusLine statusLine) {
		this.statusLine = statusLine;
	}

	public OutputStream getBody()
	{
		return stream;
	}
}
