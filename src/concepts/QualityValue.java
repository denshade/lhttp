package concepts;

/**
 * HTTP content negotiation (section 12) uses short "floating point" numbers to 
 * indicate the relative importance ("weight") of various negotiable parameters. 
 * A weight is normalized to a real number in the range 0 through 1, where 0 is 
 * the minimum and 1 the maximum value. If a parameter has a quality value of 0, 
 * then content with this parameter is `not acceptable' for the client. 
 * HTTP/1.1 applications MUST NOT generate more than three digits after the 
 * decimal point.
 * 
 * @author lieven
 *
 */
public class QualityValue 
{
	private double value;

	public double getValue() {
		return value;
	}
	/**
	 *@throws NumberFormatException if the string does not contain
     *         a parsable {@code double}.
     */         
	public QualityValue(String value)
	{
		this(Double.parseDouble(value));
	}

	public QualityValue(double value)
	{
		if (value < 0 || value > 1){
			throw new IllegalArgumentException("Invalid quality value. A quality value should be 0 <= x <= 1");
		}
		this.value = value;
	}
	
	public String toString()
	{
		return String.format("%1$,.3f", value);
	}

}
