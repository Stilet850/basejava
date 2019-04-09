package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import static java.util.Arrays.copyOfRange;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int DEFAULT_LENGTH = 10000;
    private final Resume[] storage = new Resume[DEFAULT_LENGTH];
    private int size;

    public void clear() {
        fill(storage, 0, size, null);

        size = 0;
    }

    public void update(Resume r) {
        if (getIndex(r.getUuid()) == -1) {
            System.err.println("Resume doesn't exist, uuid : " + r.getUuid());
        } else {
            storage[size] = r;
        }
    }

    public void save(Resume r) {
        if (r.getUuid() == null || r.getUuid().isEmpty())
            System.err.println("Invalid UUID " + r.getUuid());

        if (size == DEFAULT_LENGTH)
            System.err.println("Storage is full");

        if (getIndex(r.getUuid()) != -1) {
            System.err.println("Resume already exists, uuid : " + r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        if (getIndex(uuid) == -1) {
            System.err.println("There is no Resume in storage, uuid : " + uuid);
            return null;
        } else {
            return storage[getIndex(uuid)];
        }
    }

    public void delete(String uuid) {
        if (getIndex(uuid) == -1) {
            System.err.println("There is no Resume in storage, uuid : " + uuid);
        } else {
            storage[getIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return copyOfRange(storage, 0, size);
    }

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    public int size() {
        return size;
    }

    /**
     * Returns a position of Resume in Storage by Uuid.
     *
     * @param uuid
     * @return
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++)
            if (uuid.equals(storage[i].getUuid()))
                return i;

        return -1;
    }
}
