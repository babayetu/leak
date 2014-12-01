package algorithm;

public class CycleFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String message = "Cycle found in graph";

	protected CycleFoundException() {
		super(message);
	}	
}