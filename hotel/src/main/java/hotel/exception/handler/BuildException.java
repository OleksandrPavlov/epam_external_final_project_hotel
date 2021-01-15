package hotel.exception.handler;

public class BuildException extends Exception {
    public BuildException(String messege, Throwable th) {
        super(messege, th);
    }
}
