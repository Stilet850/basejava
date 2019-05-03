package ru.javawebinar.basejava.storage;

import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String FULL_NAME_1 = "FULL_NAME_1";
    private static final String FULL_NAME_2 = "FULL_NAME_2";
    private static final String FULL_NAME_3 = "FULL_NAME_3";
    private static final String UUID_NEW = "uuid_new";

    private static final Resume RESUME_UUID1 = new Resume("uuid1", FULL_NAME_1);
    private static final Resume RESUME_UUID2 = new Resume("uuid2", FULL_NAME_2);
    private static final Resume RESUME_UUID3 = new Resume("uuid3", FULL_NAME_3);
    protected static final Resume RESUME_NOT_EXIST = new Resume(UUID_NEW);

    protected AbstractStorageTest(Storage storage) {
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
        Resume resume = RESUME_UUID2;
        storage.update(resume);
        assertSame(resume, storage.get(RESUME_UUID2.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NOT_EXIST);
    }

    @Test
    public void getAll() {
        List<Resume> expectedArray = Arrays.asList(RESUME_UUID1, RESUME_UUID2, RESUME_UUID3);
        List<Resume> actualArray = storage.getAllSorted();
        assertNotNull(actualArray);
        assertEquals(expectedArray, actualArray);
    }

    @Test
    public void save() {
        storage.save(RESUME_NOT_EXIST);
        assertEquals(4, storage.size());
        assertEquals(RESUME_NOT_EXIST, storage.get(RESUME_NOT_EXIST.getUuid()));
    }

    @Test(expected = InvalidResumeException.class)
    public void saveNull() {
        storage.save(null);
    }

    @Test(expected = InvalidResumeException.class)
    public void saveNullInUuid() {
        Resume resume = new Resume(null,UUID1);
        storage.save(resume);
    }

    @Test(expected = InvalidResumeException.class)
    public void saveEmptyUuid() {
        Resume resume = new Resume("",UUID1);
        storage.save(resume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExisting() {
        storage.save(RESUME_UUID2);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID1);
        assertNotNull(storage.getAllSorted());
        assertEquals(2, storage.size());
        storage.get(UUID1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_NEW);
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