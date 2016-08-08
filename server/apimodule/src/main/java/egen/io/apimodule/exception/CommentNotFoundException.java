package egen.io.apimodule.exception;

public class CommentNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public CommentNotFoundException(String message){
		super(message);
	}
	public CommentNotFoundException(String message,Throwable cause){
		super(message,cause);
	}
}
