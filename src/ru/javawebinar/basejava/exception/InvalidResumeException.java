package ru.javawebinar.basejava.exception;

import ru.javawebinar.basejava.model.Resume;

public class InvalidResumeException extends RuntimeException {
    private final Resume resume;

    public InvalidResumeException(String message, Resume r) {
        super(message);
        this.resume = r;
    }

    public Resume getResume() {
        return resume;
    }
}
