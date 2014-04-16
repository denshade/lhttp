package concepts;

abstract public class GenericMessage 
{
	
	public GenericMessage(Header[] headers) {
		this.headers = headers;
	}

	private Header[] headers;

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	
}
