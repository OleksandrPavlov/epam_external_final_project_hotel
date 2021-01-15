package hotel.exception.handler;

public class AuthenticationException extends CustomException {
    private String errorMessage;

    public AuthenticationException() {
        super();
    }

    public AuthenticationException(String message) {
        errorMessage = message;
    }


    @Override
    public String getMessage() {
        return errorMessage;
    }
}
