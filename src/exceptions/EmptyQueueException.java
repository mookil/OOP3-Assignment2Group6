package exceptions;

/**
 * Exception class for test cases
 */
public class EmptyQueueException extends Exception {
	

	/**
	 * Exception Constructor Method
	 */
	public EmptyQueueException(String errorMessage) {
		super(errorMessage);
	}

}
