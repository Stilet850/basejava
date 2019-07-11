package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class FileStorage extends AbstractStorage<File>{
    private final File directory;
    private final StreamSerializer serializer;

    protected FileStorage(File directory, StreamSerializer serializer) {
        this.directory =  requireNonNull(directory, "directory must not be null");
        this.serializer = requireNonNull(serializer, "serializer must not be null");

        if(!directory.isDirectory()){
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not a directory");
        }

        if(!directory.canRead() || !directory.canWrite()){
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable and writable");
        }
    }

    @Override
    protected List<Resume> getAll() {
        List<Resume> resumeList = new ArrayList<>();
        File[] files = directory.listFiles();
        if(files==null){
            throw new StorageException("Cannot get all resume file");
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
            serializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
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
            return serializer.doRead(new BufferedInputStream(new FileInputStream(file)));
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
            throw new StorageException("Directory read error");
        }
        return directory.list().length;
    }
}
