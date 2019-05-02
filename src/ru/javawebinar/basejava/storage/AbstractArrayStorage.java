package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    static final int STORAGE_LIMIT = 10000;
    final Resume[] storage = new Resume[STORAGE_LIMIT];
    int size;

    @Override
    public boolean hasKey(Object key) {
        return ((int) key > -1);
    }

    @Override
    public Resume doGet(Object key) {
        return storage[(int) key];
    }

    @Override
    public void doDelete(Object key) {
        remove((int) key);
        size--;
    }

    @Override
    public void doUpdate(Resume r, Object key) {
        storage[(int) key] = r;
    }

    @Override
    public void doSave(Resume r, Object key) {
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", r.getUuid());
        else {
            insert(r, (Integer) key);
            size++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    public int size() {
        return size;
    }

    protected abstract void insert(Resume r, int key);

    protected abstract void remove(int index);
}
