package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int DEFAULT_LENGTH = 10000;
    private final Resume[] storage = new Resume[DEFAULT_LENGTH];
    private int size;

    public void clear() {
        for (int i = 0; i < size; i++)
            storage[i] = null;

        size = 0;
    }

    public void update(Resume r){
        if (checkExistence(r.getUuid())){
            //TODO: not clear what should be implemented
        }
    }

    public void save(Resume r) {
        if (r.getUuid() == null || r.getUuid().isEmpty())
           System.err.println("Invalid UUID " + r.getUuid());

        if(!checkExistence(r.getUuid())) {
            storage[size] = r;
            size++;
        }

        if (size == DEFAULT_LENGTH)
            System.err.println("Storage is full");
    }

    public Resume get(String uuid) {
        if(checkExistence(uuid))
            return Arrays.stream(storage).filter(Objects::nonNull).filter(resume -> resume.getUuid().equals(uuid)).findFirst().orElse(null);
        else
            return null;
    }

    public void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
              }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    public int size() {
        return size;
    }

    /**
     * Check if input UUID is unique.
     *
     * @param uuid  String
     * @return      boolean
     */
    private boolean checkExistence(String uuid) {
        if(Arrays.stream(getAll()).anyMatch(resume -> resume.getUuid().equals(uuid))){
            System.out.println("Resume exists, uuid: " + uuid);
            return true;
        }

        System.out.println("Resume doesn't exist, uuid: " + uuid);

        return false;
    }
}
