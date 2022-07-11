package project.framework.core.accountdetails.storage.service;

public class StorageServiceException extends RuntimeException {

    public StorageServiceException() {
    }

    public StorageServiceException(String message) {
        super(message);
    }

    public StorageServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageServiceException(Throwable cause) {
        super(cause);
    }
}
