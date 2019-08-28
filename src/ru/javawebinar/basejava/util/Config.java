package ru.javawebinar.basejava.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    protected static final File PROPS = new File(".\\config\\resumes.properties");
    private Properties prop = new Properties();
    private File storageDir;

    private static final Config INSTANCE = new Config();

    public static Config get() {
        return INSTANCE;
    }

    private Config() {
        try (InputStream is = new FileInputStream(PROPS)){
            prop.load(is);
            storageDir = new File(prop.getProperty("storage.dir"));
        }catch(IOException e){
             throw new IllegalStateException("Cannot load property file: " + PROPS.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }
}
