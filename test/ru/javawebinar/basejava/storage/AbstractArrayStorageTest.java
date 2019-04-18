package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import static org.junit.Assert.*;
import static ru.javawebinar.basejava.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest {

    private Storage storage;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID_NOT_EXIST = "uuid_not_exist";

    private static final Resume RESUME_UUID1 = new Resume(UUID1);
    private static final Resume RESUME_UUID2 = new Resume(UUID2);
    private static final Resume RESUME_UUID3 = new Resume(UUID3);
    private static final Resume RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);
    private Resume[] expectedArray;

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_UUID1);
        storage.save(RESUME_UUID2);
        storage.save(RESUME_UUID3);
    }

    @Test
    public void get() {
        assertEquals(RESUME_UUID1, storage.get(RESUME_UUID1.getUuid()));
        assertEquals(RESUME_UUID2, storage.get(RESUME_UUID2.getUuid()));
        assertEquals(RESUME_UUID3, storage.get(RESUME_UUID3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID2);
        storage.update(resume);

        assertSame(resume, storage.get(UUID2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void getAll() {
        Resume[] expectedArray = new Resume[]{RESUME_UUID1, RESUME_UUID2, RESUME_UUID3};
        Resume[] actualArray = storage.getAll();
        assertNotNull(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void save() {
        storage.save(RESUME_NOT_EXIST);

        assertEquals(4, storage.size());
        assertEquals(RESUME_NOT_EXIST, storage.getAll()[3]);
    }

    @Test(expected = NullPointerException.class)
    public void saveNull() {
        storage.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void saveNullInUuid() {
        Resume resume = new Resume(null);
        storage.save(resume);
    }

    @Test(expected = NullPointerException.class)
    public void saveEmptyUuid() {
        Resume resume = new Resume("");
        storage.save(resume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExisting() {
        storage.save(RESUME_UUID2);
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
            fail();
        }
        storage.save(RESUME_NOT_EXIST);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {

        storage.delete(UUID1);

        assertNotNull(storage.getAll());
        assertEquals(2, storage.size());
        storage.get(UUID1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NOT_EXIST);
    }

    @Test
    public void clear() {
        storage.clear();

        assertNotNull(storage);
        assertEquals(0, storage.size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}