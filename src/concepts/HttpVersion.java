package concepts;

public class HttpVersion implements Comparable<HttpVersion>
{
	private long majorVersion;
	private long minorVersion;
	
	public long getMajorVersion() {
		return majorVersion;
	}
	public void setMajorVersion(long majorVersion) {
		this.majorVersion = majorVersion;
	}
	public long getMinorVersion() {
		return minorVersion;
	}
	public void setMinorVersion(long minorVersion) {
		this.minorVersion = minorVersion;
	}
	
	@Override
	public int compareTo(HttpVersion o) {
		if (getMajorVersion() > o.getMajorVersion()) return 1;
		if (getMajorVersion() < o.getMajorVersion()) return -1;
		if (getMinorVersion() > o.getMinorVersion()) return 1;
		if (getMinorVersion() < o.getMinorVersion()) return -1;		
		return 0;
	}
	
	

	
}
