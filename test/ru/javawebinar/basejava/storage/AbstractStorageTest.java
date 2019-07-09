package ru.javawebinar.basejava.storage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static ru.javawebinar.basejava.model.ResumeTestData.create;

public abstract class AbstractStorageTest {
    protected final static File STORAGE_DIR = new File("D:\\storage");

    protected final Storage storage;
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID_NEW = "uuid_new";

//    private static Resume R1 = new Resume(UUID1, "abcd");
//    private static Resume R2 = new Resume(UUID2, "bbd");
//    private static Resume R3 = new Resume(UUID3, "bcd");
    private static Resume R1 = create(UUID1, "abcd");
    private static Resume R2 = create(UUID2, "bbd");
    private static Resume R3 = create(UUID3, "bcd");
    static Resume R_NEW = create(UUID_NEW, UUID_NEW);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(R3);
        storage.save(R2);
        storage.save(R1);
    }

    @After
    public void doClean() {
        storage.clear();
    }

    @Test
    public void get() {
        assertEquals(R1, storage.get(UUID1));
        assertEquals(R2, storage.get(UUID2));
        assertEquals(R3, storage.get(UUID3));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get(UUID_NEW);
    }

    @Test
    public void update() {
        Resume resume = new Resume(UUID2, UUID_NEW);

        storage.update(resume);
        assertEquals(resume, storage.get(UUID2));
        assertEquals(UUID_NEW, storage.get(UUID2).getFullName());
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(R_NEW);
    }

    @Test
    public void getAllSorted() {
        List<Resume> expectedArray = asList(R1, R2, R3);
        List<Resume> actualArray = storage.getAllSorted();
        assertNotNull(actualArray);
        assertThat(actualArray, is(expectedArray));
    }

    @Test
    public void save() {
        storage.save(R_NEW);
        assertEquals(4, storage.size());
        assertEquals(R_NEW, storage.get(UUID_NEW));
    }

    @Test(expected = InvalidResumeException.class)
    public void saveNull() {
        storage.save(null);
    }

    @Test(expected = NullPointerException.class)
    public void saveNullInUuid() {
        Resume resume = new Resume(null, UUID1);
        storage.save(resume);
    }

    @Test(expected = InvalidResumeException.class)
    public void saveEmptyUuid() {
        Resume resume = new Resume("", UUID1);
        storage.save(resume);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExisting() {
        storage.save(R2);
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