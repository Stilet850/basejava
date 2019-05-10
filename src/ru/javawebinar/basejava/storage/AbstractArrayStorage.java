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
        return ((Integer) key > -1);
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
    public void doUpdate(Resume resume, Object key) {
        storage[(int) key] = resume;
    }

    @Override
    public void doSave(Resume resume, Object key) {
        if (size == STORAGE_LIMIT)
            throw new StorageException("Storage overflow", resume.getUuid());
        else {
            insert(resume, (Integer) key);
            size++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null) in sorted way.
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

    protected abstract void insert(Resume resume, int key);

    protected abstract void remove(int index);
}
