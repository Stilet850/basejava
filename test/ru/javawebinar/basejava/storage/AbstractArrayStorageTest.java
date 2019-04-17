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
    private static final Resume[] EXPECTED_ARRAY = new Resume[]{RESUME_UUID1, RESUME_UUID2, RESUME_UUID3};

    AbstractArrayStorageTest(Storage storage) {
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
        assertEquals(storage.getAll()[0], RESUME_UUID1);
        assertEquals(storage.getAll()[1], RESUME_UUID2);
        assertEquals(storage.getAll()[2], RESUME_UUID3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void update() {
        Resume resumeUUID4 = new Resume(UUID2);
        storage.update(resumeUUID4);

        assertEquals(storage.get("uuid2"), resumeUUID4);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(new Resume("uuidToUpdateStorage"));
    }

    @Test
    public void getAll() {
        Resume[] arrayAll = storage.getAll();
        assertNotNull(arrayAll);
        assertArrayEquals(EXPECTED_ARRAY, arrayAll);
    }

    @Test
    public void save() {
        storage.save(RESUME_NOT_EXIST);

        assertNotNull(storage);
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
    public void saveStorageOverFlow() {
        for (int i = 4; i <= STORAGE_LIMIT + 1; i++) {
            storage.save(new Resume(Integer.toString(i)));
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        //when
        storage.delete(UUID1);

        //then
        assertNotNull(storage.getAll());
        assertEquals(2, storage.getAll().length);
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