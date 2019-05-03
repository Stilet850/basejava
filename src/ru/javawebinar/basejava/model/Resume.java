package ru.javawebinar.basejava.model;

import java.util.Objects;
import java.util.UUID;

import static java.util.Objects.hash;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
//public class Resume{

    // Unique identifier
    private final String uuid;

    private final String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(uuid, resume.uuid) &&
                Objects.equals(fullName, resume.fullName);
    }

    @Override
    public int hashCode() {
        return hash(uuid, fullName);
    }

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resume r) {
        int byFullName = this.fullName.compareTo(r.fullName);

        return byFullName != 0 ? byFullName : this.uuid.compareTo(r.uuid);
    }
}
