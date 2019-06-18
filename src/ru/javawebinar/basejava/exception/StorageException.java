package ru.javawebinar.basejava.exception;

public class StorageException extends RuntimeException {
    private final String uuid;

    public StorageException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public StorageException(String io_error, String name, Exception e) {
        super(io_error, e);
        this.uuid = name;
    }

    public String getUuid() {
        return uuid;
    }
}
