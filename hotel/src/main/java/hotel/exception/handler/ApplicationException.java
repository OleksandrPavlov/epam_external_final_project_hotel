package hotel.exception.handler;

public class ApplicationException extends Exception {
    private static final long serialVersionUID = -2504043176983262064L;
    private final String messege = "Oh! Something is not good for me... Please reload page";

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException() {

    }

    @Override
    public String getMessage() {
        return messege;
    }

}
