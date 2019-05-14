package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractMapStorage extends AbstractStorage {
    protected final Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAll() {
        return new ArrayList(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected abstract boolean hasSearchKey(Object searchKey);
}
