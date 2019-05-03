package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

//TODO use another new class as search key.
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
    public List<Resume> getAllSorted() {
        return storage.values().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public int size() {
        return storage.size();
    }
}
