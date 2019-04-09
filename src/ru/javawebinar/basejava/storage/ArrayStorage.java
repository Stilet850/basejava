package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected void remove(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected void insert(Resume r, int index) {
        storage[size] = r;
    }

    /**
     * Returns a position of Resume in Storage by Uuid.
     *
     * @param uuid
     * @return
     */
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++)
            if (uuid.equals(storage[i].getUuid()))
                return i;

        return -1;
    }
}
