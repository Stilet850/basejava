package ru.javawebinar.basejava.storage;

import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume(Integer.toString(i)));
            }
            int expectedStorageLength = 10000;
            assertEquals(expectedStorageLength, storage.size());
        } catch (StorageException e) {
            fail("Storage should not be filled. Please check storage capacity.");
        }

        storage.save(RESUME_NOT_EXIST);
    }
}