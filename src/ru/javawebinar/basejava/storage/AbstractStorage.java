package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.InvalidResumeException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.sort;

public abstract class AbstractStorage implements Storage {

    public Resume get(String uuid) {
        Object key = getKey(uuid);
        if (!hasKey(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            return doGet(key);
        }
    }

    public void update(Resume resume) {
        Object key = getKey(resume.getUuid());
        if (!hasKey(key)) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            doUpdate(resume, key);
        }
    }

    public void save(Resume resume) {
        if (resume == null || resume.getUuid() == null || resume.getUuid().isEmpty())
            throw new InvalidResumeException("Invalid resume:" + resume, resume);

        Object key = getKey(resume.getUuid());
        if (hasKey(key)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, key);
        }
    }

    public void delete(String uuid) {
        Object key = getKey(uuid);
        if (!hasKey(key)) {
            throw new NotExistStorageException(uuid);
        } else {
            doDelete(key);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes = asList(getAll());
        sort(resumes);
        return resumes;
    }

    protected abstract Resume[] getAll();

    protected abstract boolean hasKey(Object key);

    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doDelete(Object key);

    protected abstract Resume doGet(Object key);

    protected abstract Object getKey(String uuid);


}
