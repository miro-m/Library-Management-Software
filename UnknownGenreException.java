
public class UnknownGenreException extends Exception {

	private String x  = null;

	public UnknownGenreException() {
		x = ""; 
	}
	
	public UnknownGenreException(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
	
}
