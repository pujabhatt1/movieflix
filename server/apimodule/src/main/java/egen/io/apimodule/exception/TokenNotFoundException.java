package egen.io.apimodule.exception;

public class TokenNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public TokenNotFoundException(String message){
		super(message);
	}
	public TokenNotFoundException(String message,Throwable cause){
		super(message,cause);
	}
}