package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.storage.SqlStorage;
import ru.javawebinar.basejava.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File(".\\config\\resumes.properties");
    private final File storageDir;
    private final Storage storage;

    private static final Config INSTANCE = new Config();

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)){
            Properties prop = new Properties();
            prop.load(is);
            storageDir = new File(prop.getProperty("storage.dir"));
            storage = new SqlStorage(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"));
        }catch(IOException e){
             throw new IllegalStateException("Cannot load property file: " + PROPS.getAbsolutePath());
        }
    }

    public Storage getStorage() {
        return storage;
    }

    public File getStorageDir() {
        return storageDir;
    }
}
