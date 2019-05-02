package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new TreeMap<String, Resume>();

    @Override
    protected boolean hasKey(Object key) {
        return storage.containsKey((String) key);
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        storage.put((String) key, r);
    }

    @Override
    protected void doSave(Resume r, Object key) {
        storage.put((String) key, r);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((String) key);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((String) key);
    }

    @Override
    protected Object getKey(String uuid) {
        return uuid;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.values().toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
