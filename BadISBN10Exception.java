
public class BadISBN10Exception extends Exception{

	private String x  = null;

	public BadISBN10Exception() {
		x = "";
	}
	
	public BadISBN10Exception(String x) {
		this.x = x;
	}
	
	public String getMessage() {
		return x;
	}
	
}
