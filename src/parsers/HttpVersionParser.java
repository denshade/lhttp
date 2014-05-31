package parsers;

import concepts.HttpVersion;

public class HttpVersionParser 
{
	public HttpVersion parse(String versionString)
	{
		String[] versionParts = versionString.split("\\.");
		if (versionParts.length != 2)
		{
			throw new IllegalArgumentException("Unknown version format: " + versionString);
		}
		long majorVersion = Long.parseLong(versionParts[0]);
		long minorVersion = Long.parseLong(versionParts[1]);
		HttpVersion version = new HttpVersion();
		version.setMajorVersion(majorVersion);
		version.setMinorVersion(minorVersion);		
		return version;
	}
}
