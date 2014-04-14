package concepts;

import static org.junit.Assert.*;

import org.junit.Test;

public class QualityValueTest {
	
	//HTTP/1.1 applications MUST NOT generate more than three digits after the decimal point.
	@Test
	public void noMoreThan3Digitis() 
	{
		QualityValue val = new QualityValue(0.11111);
		assertEquals("0.111", val.toString());
	}
	//A weight is normalized to a real number in the range 0 through 1, where 0 is the minimum and 1 the maximum value.
	@Test(expected=IllegalArgumentException.class)	
	public void illegalArgumentBiggerThan1() 
	{
		QualityValue val = new QualityValue(1.11111);
	}
	//A weight is normalized to a real number in the range 0 through 1, where 0 is the minimum and 1 the maximum value.
	@Test(expected=IllegalArgumentException.class)	
	public void illegalArgumentSmallerThan0() 
	{
		QualityValue val = new QualityValue(-1.11111);
	}
}
