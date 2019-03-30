package CustomException;


public class DuplicateCNPException extends RuntimeException {

    public DuplicateCNPException(String message) {
        super(message);
    }
}
