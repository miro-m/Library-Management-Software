
public class BadISBN13Exception extends Exception{

	private String x  = null;

	public BadISBN13Exception() {
		x = "";
	}
	
	public BadISBN13Exception(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
	
}
