package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public class MapResumeStorage extends AbstractMapStorage {
    @Override
    protected boolean hasSearchKey(Object searchKey) {
        return searchKey != null;
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage.replace(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Object resume) {
        storage.remove(((Resume) resume).getUuid());
    }

    @Override
    protected Resume doGet(Object resume) {
        return (Resume) resume;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return storage.get(uuid);
    }

}
