
public class MissingFieldException extends Exception {

	
	
	private String x  = null;

	public MissingFieldException() {
		x = "";
	}
	
	public MissingFieldException(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
	
	
}
