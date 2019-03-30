package CustomException;

public class NonExistingEntityException extends RuntimeException {

    public NonExistingEntityException(String message) {
        super(message);
    }
}