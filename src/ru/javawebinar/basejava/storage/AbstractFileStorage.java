package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public abstract class AbstractFileStorage extends AbstractStorage<File>{
    private final File directory;

    protected AbstractFileStorage(File directory) {
        requireNonNull(directory, "directory must not be null");
        if(!directory.isDirectory()){
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }

        if(directory.canRead() || directory.canWrite()){
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable and writable");
        }

        this.directory = directory;
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumeList = new ArrayList<>();
        File[] files = directory.listFiles();
        if(files==null){
            throw new StorageException("Cannot get all resume file", null);
        }

        for (File file: files) {
            resumeList.add(doGet(file));
        }
        return resumeList;
    }

    @Override
    protected boolean hasSearchKey(File file) {
        return file.exists();
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            new StorageException("File write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            new StorageException("Could not save file: ", file.getName(), e);
        }
        doUpdate(resume, file);
  }

    @Override
    protected void doDelete(File file) {
        if(!file.delete()){
            throw new StorageException( "File delete error", file.getName());
        }
    }

    @Override
    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Cannot get resume from ", file.getName(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    public void clear() {
        File [] files = directory.listFiles();
        if(files != null) {
            for (File file:directory.listFiles()) {
                if (file.isFile()) {
                    doDelete(file);
                }
            }
        }
    }

    @Override
    public int size() {
        String [] list = directory.list();
        if (list==null){
            throw new StorageException("Directory read error", null);
        }
        return directory.list().length;
    }

    protected abstract Resume doRead(File file) throws IOException;

    protected abstract void doWrite (Resume resume, File file) throws IOException;
}
