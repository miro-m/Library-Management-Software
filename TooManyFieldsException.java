
public class TooManyFieldsException extends Exception {

	
	private String x  = null;

	public TooManyFieldsException() {
		x = ""; 
	}
	
	public TooManyFieldsException(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
}
