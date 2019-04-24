package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;
import java.util.TreeMap;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new TreeMap<String, Resume>();

    @Override
    protected void doUpdate(Resume r, int key) {

    }

    @Override
    protected void doSave(Resume r, int key) {

    }

    @Override
    protected void doDelete(int key) {

    }

    @Override
    protected Resume doGet(int key) {
        return null;
    }

    @Override
    protected int getKey(String uuid) {
        return 0;
    }

    @Override
    public void clear() {

    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }
}
