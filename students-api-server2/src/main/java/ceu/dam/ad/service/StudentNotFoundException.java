package ceu.dam.ad.service;

public class StudentNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2962667756499110604L;

	public StudentNotFoundException() {
	}

	public StudentNotFoundException(String message) {
		super(message);

	}

	public StudentNotFoundException(Throwable cause) {
		super(cause);

	}

	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public StudentNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
