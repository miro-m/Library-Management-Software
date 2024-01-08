
public class TooFewFieldsException extends Exception {

	private String x  = null;

	public TooFewFieldsException() {
		x = ""; 
	}
	
	public TooFewFieldsException(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
	
}
