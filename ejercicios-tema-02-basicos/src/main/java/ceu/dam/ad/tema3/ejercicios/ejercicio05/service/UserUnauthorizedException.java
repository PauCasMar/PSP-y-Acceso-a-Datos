package ceu.dam.ad.tema3.ejercicios.ejercicio05.service;

public class UserUnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1064214048922600104L;

	public UserUnauthorizedException() {
	}

	public UserUnauthorizedException(String message) {
		super(message);

	}

	public UserUnauthorizedException(Throwable cause) {
		super(cause);

	}

	public UserUnauthorizedException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserUnauthorizedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

}
