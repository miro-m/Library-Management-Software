
public class BadYearException extends Exception {

	private String x  = null;

	public BadYearException() {
		x = "";
	}
	
	public BadYearException(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}	
	
	
}
