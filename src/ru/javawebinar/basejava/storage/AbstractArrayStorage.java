package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

import static java.util.Arrays.*;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size;

    @Override
    public boolean hasSearchKey(Object index) {
        return ((int) index > -1);
    }

    @Override
    public Resume doGet(Object index) {
        return storage[(int) index];
    }

    @Override
    public void doDelete(Object index) {
        remove((int) index);
        size--;
    }

    @Override
    public void doUpdate(Resume resume, Object index) {
        storage[(int) index] = resume;
    }

    @Override
    public void doSave(Resume resume, Object index) {
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        else {
            insert(resume, (Integer) index);
            size++;
        }
    }

    public List<Resume> getAll() {
        return asList(copyOfRange(storage, 0, size));
    }

    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    protected abstract void insert(Resume resume, int index);

    protected abstract void remove(int index);
}
