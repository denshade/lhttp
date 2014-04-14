package concepts;

import static org.junit.Assert.*;

import org.junit.Test;

public class LanguageTagTest {

	// In summary, a language tag is composed of 1 or more parts
	@Test
	public void testOkLanguageTag() 
	{
		LanguageTag tag = new LanguageTag("en-GB");
		assertEquals("en", tag.getLanguageAbreviation());
		assertEquals("GB", tag.getCountryCode());
	}

	// In summary, a language tag is composed of 1 or more parts
	@Test(expected=IllegalArgumentException.class)
	public void testBadLanguageTag() 
	{
		new LanguageTag("xx-GB");
	}
	
	// In summary, a language tag is composed of 1 or more parts
	@Test(expected=IllegalArgumentException.class)
	public void testBadCountryTag() 
	{
		new LanguageTag("en-XX");
	}
	
	// In summary, a language tag is composed of 1 or more parts
	@Test()
	public void testLanguageTagOnePart() 
	{
		new LanguageTag("en");
	}
}
