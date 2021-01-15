package hotel.exception.handler;

public class ValidationException extends Exception {
    public ValidationException(String messege, Throwable th) {
        super(messege, th);
    }

    public ValidationException() {
    }
}
