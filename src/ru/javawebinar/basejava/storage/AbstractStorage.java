package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (!has(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(key);
        }
    }

    public void update(Resume r) {
        Object key = getKey(r.getUuid());
        if (!has(key)) {
            throw new NotExistStorageException(r.getUuid());
        } else {
            doUpdate(r, key);
        }
    }

    public void save(Resume r) {
        if (r == null || r.getUuid() == null || r.getUuid().isEmpty())
            throw new InvalidResumeException("Invalid resume:" + r, r);

        Object key = getKey(r.getUuid());
        if (has(key)) {
            throw new ExistStorageException(r.getUuid());
        } else {
            doSave(r, key);
        }
    }

    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (!has(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

    protected abstract boolean has(Object key);

    protected abstract void doUpdate(Resume r, Object key);

    protected abstract void doSave(Resume r, Object key);

    protected abstract void doDelete(Object key);

    protected abstract Resume doGet(Object key);

    protected abstract Object getKey(String uuid);
}
