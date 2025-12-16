package ceu.dam.ad.service;

public class QuestionNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8579442696104179459L;

	public QuestionNotFoundException() {
	}

	public QuestionNotFoundException(String message) {
		super(message);

	}

	public QuestionNotFoundException(Throwable cause) {
		super(cause);

	}

	public QuestionNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public QuestionNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
