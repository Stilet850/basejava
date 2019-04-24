package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        int key = getKey(uuid);
        if (key <= -1) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(key);
        }
    }

    public void update(Resume r) {
        int key = getKey(r.getUuid());
        if (key <= -1) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, key);
        }
    }

    public void save(Resume r) {
        if (r == null || r.getUuid() == null || r.getUuid().isEmpty())
            throw new NullPointerException("Resume is null");

        int key = getKey(r.getUuid());
        if (key >=0 ) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, key);
        }
    }

    public void delete(String uuid) {
        int key = getKey(uuid);
        if (key <= -1) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

    protected abstract void doUpdate(Resume r, int key);
    protected abstract void doSave(Resume r, int key);
    protected abstract void doDelete(int key);
    protected abstract Resume doGet(int key);
    protected abstract int getKey(String uuid);
}
