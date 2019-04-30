package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<Resume>();

    @Override
    protected boolean has(Object key) {
        if ((int) key > -1)
            return true;

        return false;
    }

    @Override
    protected void doUpdate(Resume r, Object key) {
        storage.set((Integer) key, r);
    }

    @Override
    protected void doSave(Resume r, Object key) {
        storage.add(r);
    }

    @Override
    protected void doDelete(Object key) {
        storage.remove((int) key);
    }

    @Override
    protected Resume doGet(Object key) {
        return storage.get((int) key);
    }

    @Override
    protected Object getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++)
            if (uuid.equals(storage.get(i).getUuid()))
                return i;

        return -1;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.stream().toArray(Resume[]::new);
    }

    @Override
    public int size() {
        return storage.size();
    }
}
