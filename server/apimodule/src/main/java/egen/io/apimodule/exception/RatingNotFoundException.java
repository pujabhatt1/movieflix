package egen.io.apimodule.exception;

public class RatingNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public RatingNotFoundException(String message){
		super(message);
	}
	public RatingNotFoundException(String message,Throwable cause){
		super(message,cause);
	}
}
