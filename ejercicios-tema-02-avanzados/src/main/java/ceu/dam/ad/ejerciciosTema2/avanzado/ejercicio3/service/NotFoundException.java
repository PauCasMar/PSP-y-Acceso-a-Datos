package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.service;

public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 857859178053320028L;

	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super(cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
