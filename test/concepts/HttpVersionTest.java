package concepts;

import static org.junit.Assert.*;

import org.junit.Test;

public class HttpVersionTest {

	@Test
	public void test() {
		HttpVersion version = new HttpVersion();
		version.setMajorVersion(2);
		version.setMinorVersion(4);

		HttpVersion version2 = new HttpVersion();
		version2.setMajorVersion(3);
		version2.setMinorVersion(2);

		assertTrue(version.compareTo(version2) < 0);
		assertTrue(version2.compareTo(version) > 0);
		assertTrue(version.compareTo(version) == 0);
	}

}
