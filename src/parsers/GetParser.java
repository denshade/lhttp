package parsers;

public class GetParser 
{

	public static GetRequest parseGetLine(String parseLine)
	{
		
		String[] get = parseLine.split(" ");
		if (get.length != 3)
		{
			throw new IllegalArgumentException("Invalid get request");
		}
		GetRequest request = new GetRequest();
		request.method = get[0]; 
		request.requestedFile = get[1]; 
		request.httpVersion = get[2]; 
		if (!request.method.equalsIgnoreCase("get"))
        {
        	throw new IllegalArgumentException("Only GET supported");
        }
        return request;       
	}
}
