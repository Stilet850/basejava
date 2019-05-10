package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;

//TODO refactoring
public interface Storage {
    void clear();

    void update(Resume resume);

    void save(Resume resume);

    Resume get(String uuid);

    void delete(String uuid);

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    List<Resume> getAllSorted();
    /**
     * @return size of array (nonNull) containing Resumes.
     */
    int size();
}
