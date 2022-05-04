package nl.meritude.businessmodule.exception;

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }
}
