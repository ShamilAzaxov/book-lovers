package sh.tech.bookloverslibrary.exception;

public class RentNotFoundException extends RuntimeException{
    public RentNotFoundException(String message) {
        super(message);
    }
}
