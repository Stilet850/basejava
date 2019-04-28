package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<Resume>();

    @Override
    protected void doUpdate(Resume r, int key) {
        storage.set(key, r);
    }

    @Override
    protected void doSave(Resume r, int key) {
        storage.add(r);
    }

    @Override
    protected void doDelete(int key) {
        storage.remove(key);
    }

    @Override
    protected Resume doGet(int key) {
        return storage.get(key);
    }

    @Override
    protected int getKey(String uuid) {
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
