package ru.javawebinar.basejava.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected Storage storage;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID_NEW = "uuid_new";

    private static Resume RESUME_UUID1;
    private static Resume RESUME_UUID2;
    private static Resume RESUME_UUID3;
    static Resume RESUME_NEW;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        RESUME_UUID1 = new Resume(UUID1, UUID1);
        RESUME_UUID2 = new Resume(UUID2, UUID2);
        RESUME_UUID3 = new Resume(UUID3, UUID3);
        RESUME_NEW = new Resume(UUID_NEW, UUID_NEW);
        storage.clear();
        storage.save(RESUME_UUID3);
        storage.save(RESUME_UUID2);
        storage.save(RESUME_UUID1);
    }

    @After
    public void doClean(){
        storage.clear();
    }

    @Test
    public void get() {
        assertEquals(RESUME_UUID1, storage.get(RESUME_UUID1.getUuid()));
        assertEquals(RESUME_UUID2, storage.get(RESUME_UUID2.getUuid()));
        assertEquals(RESUME_UUID3, storage.get(RESUME_UUID3.getUuid()));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NEW);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID2, UUID_NEW);

        storage.update(resume);
        assertSame(resume, storage.get(UUID2));
        assertEquals(UUID_NEW, storage.get(UUID2).getFullName());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_NEW);
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedArray = asList(RESUME_UUID1, RESUME_UUID2, RESUME_UUID3);
        List<Resume> actualArray = storage.getAllSorted();
        assertNotNull(actualArray);
        assertThat(actualArray, is(expectedArray));
    }

    @Test
    public void save() {
        storage.save(RESUME_NEW);
        assertEquals(4, storage.size());
        assertEquals(RESUME_NEW, storage.get(UUID_NEW));
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