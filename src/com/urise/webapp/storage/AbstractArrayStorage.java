package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

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

    protected abstract int getIndex(String uuid);

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    public int size() {
        return size;
    }
}
