package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

abstract public class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.err.println("There is no Resume in storage, uuid : " + uuid);
            return null;
        } else {
            return storage[index];
        }
    }

    public void update(Resume r) {
        if (getIndex(r.getUuid()) == -1) {
            System.err.println("Resume doesn't exist, uuid : " + r.getUuid());
        } else {
            storage[size] = r;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public void save(Resume r) {
        if (r.getUuid() == null || r.getUuid().isEmpty())
            System.err.println("Invalid UUID " + r.getUuid());

        if (size == STORAGE_LIMIT)
            System.err.println("Storage is full");

        int index = getIndex(r.getUuid());
        if (index > 0) {
            System.err.println("Resume already exists, uuid : " + r.getUuid());
        } else {
            insert(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.err.println("There is no Resume in storage, uuid : " + uuid);
        } else {
            Resume r = new Resume();
            r.setUuid(uuid);
            remove(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);

        size = 0;
    }

    protected abstract void remove(int index);

    protected abstract void insert(Resume r, int index);

    protected abstract int getIndex(String uuid);

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    public int size() {
        return size;
    }
}
