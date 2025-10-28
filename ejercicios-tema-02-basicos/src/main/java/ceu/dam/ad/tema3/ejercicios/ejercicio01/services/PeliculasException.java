package ceu.dam.ad.tema3.ejercicios.ejercicio01.services;

public class PeliculasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 678554199482638749L;

	public PeliculasException() {
	}

	public PeliculasException(String message) {
		super(message);
	}

	public PeliculasException(Throwable cause) {
		super(cause);
	}

	public PeliculasException(String message, Throwable cause) {
		super(message, cause);
	}

	public PeliculasException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
