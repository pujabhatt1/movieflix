package egen.io.apimodule.exception;

public class NotAdminException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public NotAdminException(String message){
		super(message);
	}
	public NotAdminException(String message,Throwable cause){
		super(message,cause);
	}
}