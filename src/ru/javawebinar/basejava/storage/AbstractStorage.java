package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

import static java.util.Collections.sort;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object key = getExistingKey(uuid);
        return doGet(key);
    }

    public void update(Resume resume) {
        Object key = getExistingKey(resume.getUuid());
        doUpdate(resume, key);
    }

    public void save(Resume resume) {
        validate(resume);

        Object key = getNotExistingKey(resume.getUuid());
        doSave(resume, key);
   }

    public void delete(String uuid) {
        Object key = getExistingKey(uuid);
        doDelete(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = getAll();
        sort(resumes);
        return resumes;
    }

    private static void validate(Resume resume) {
        if (resume == null || resume.getUuid() == null || resume.getUuid().isEmpty())
            throw new InvalidResumeException("Invalid resume:" + resume, resume);
    }

    private Object getExistingKey(String uuid) {
        Object key = getKey(uuid);
        if (!hasKey(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return key;
        }
    }

    private Object getNotExistingKey(String uuid) {
        Object key = getKey(uuid);
        if (hasKey(key)) {
            throw new ExistStorageException(uuid);
        } else {
            return key;
        }
    }

    protected abstract List getAll();

    protected abstract boolean hasKey(Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doDelete(Object key);

    protected abstract Resume doGet(Object key);

    protected abstract Object getKey(String uuid);


}
