package ceu.dam.ad.tema3.ejercicios.ejercicio06.service;

public class PedidoNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7798790340924636027L;

	public PedidoNotFoundException() {
	}

	public PedidoNotFoundException(String message) {
		super(message);
	}

	public PedidoNotFoundException(Throwable cause) {
		super(cause);
	}

	public PedidoNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public PedidoNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
