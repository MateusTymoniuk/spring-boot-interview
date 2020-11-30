package br.com.mateus.springbootinterview.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -4405203529162634924L;

	public NegocioException(String s) {
		super(s);
	}

}
