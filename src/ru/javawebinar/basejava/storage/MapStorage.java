package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapStorage extends AbstractMapStorage {

    @Override
    protected boolean hasSearchKey(Object searchKey) {
        return storage.containsKey(searchKey);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put((String) searchKey, resume);
    }

    @Override
    protected void doDelete(Object searchKey) {
        storage.remove(searchKey);
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage.get(searchKey);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

}
