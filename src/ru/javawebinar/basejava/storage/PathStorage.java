package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.serializer.StreamSerializer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class PathStorage extends AbstractStorage<Path>{
    private final Path directory;
    private final StreamSerializer serializer;

    protected PathStorage(String dir, StreamSerializer serializer) {
        requireNonNull(dir, "directory must not be null");
        this.directory = Paths.get(dir);
        this.serializer = requireNonNull(serializer, "serializer must not be null");
;
        if(!Files.isDirectory(directory)){
            throw new IllegalArgumentException(dir + " is not a directory");
        }

        if(!Files.isWritable(directory)){
            throw new IllegalArgumentException(dir + " is not readable and writable");
        }
    }

    @Override
    protected List<Resume> getAll() {
        return getFilesList().map(this::doGet).collect(toList());
    }

    @Override
    protected boolean hasSearchKey(Path path) {
        return Files.isRegularFile(path);
    }

    @Override
    protected void doUpdate(Resume resume, Path path) {
        try {
            serializer.doWrite(resume, new BufferedOutputStream(Files.newOutputStream(path)));
        } catch (IOException e) {
            new StorageException("Path write error", resume.getUuid(), e);
        }
    }

    @Override
    protected void doSave(Resume resume, Path path) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            new StorageException("Could not save path: " + path, getFileName(path), e);
        }
        doUpdate(resume, path);
  }

    @Override
    protected void doDelete(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException( "Path delete error", getFileName(path), e);
        }
    }

    @Override
    protected Resume doGet(Path path) {
        try {
            return serializer.doRead(new BufferedInputStream(Files.newInputStream(path)));
        } catch (IOException e) {
            throw new StorageException("Cannot get resume from ", getFileName(path), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return directory.resolve(uuid);
    }

    @Override
    public void clear() {
            getFilesList().forEach(this::doDelete);
    }

    @Override
    public int size() {
        return (int) getFilesList().count();
    }

    private Stream<Path> getFilesList() {
        try {
            return Files.list(directory);
        } catch (IOException e) {
            throw  new StorageException("Path delete error.", e);
        }
    }

    private String getFileName(Path path) {
        return path.getFileName().toString();
    }
}
