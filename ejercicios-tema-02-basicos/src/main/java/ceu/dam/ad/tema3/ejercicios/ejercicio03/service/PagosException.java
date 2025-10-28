package ceu.dam.ad.tema3.ejercicios.ejercicio03.service;

public class PagosException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4716107446910203795L;

	public PagosException() {
	}

	public PagosException(String message) {
		super(message);
	}

	public PagosException(Throwable cause) {
		super(cause);
	}

	public PagosException(String message, Throwable cause) {
		super(message, cause);
	}

	public PagosException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
