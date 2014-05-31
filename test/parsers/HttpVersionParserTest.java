package parsers;

import static org.junit.Assert.*;

import org.junit.Test;

import concepts.HttpVersion;

public class HttpVersionParserTest {

	@Test
	public void test() {
		HttpVersionParser parser = new HttpVersionParser();
		HttpVersion version = parser.parse("2.3");
		assertEquals(2, version.getMajorVersion());
		assertEquals(3, version.getMinorVersion());
	}

}
