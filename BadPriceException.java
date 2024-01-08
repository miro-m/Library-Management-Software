
public class BadPriceException extends Exception{
	
	private String x  = null;

	public BadPriceException() {
		x = "";
	}
	
	public BadPriceException(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
}
