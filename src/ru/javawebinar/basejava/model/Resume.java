package ru.javawebinar.basejava.model;

import ru.javawebinar.basejava.model.content.Section;

import java.util.*;

import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {
    // Unique identifier
    private final String uuid;
    //TODO: Better to wrap this field into separate Class: FullName
    private final String fullName;

    public void setContacts(Map<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        //after watching HW#06
        requireNonNull(uuid, "UUID must not be null");
        requireNonNull(fullName, "FullName must not be NULL");

        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void addContact(ContactType contactType, String contact) {
        contacts.put(contactType, contact);
    }

    public void addSection(SectionType contactType, Section section) {
        sections.put(contactType, section);
    }

    public Section getSection(SectionType sectionType){
        return sections.get(sectionType);
    }

    public String getContact(ContactType contactType){
        return contacts.get(contactType);
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
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public int compareTo(Resume r) {
        int comparisonResultByFullName = fullName.compareTo(r.fullName);

        return comparisonResultByFullName != 0 ? comparisonResultByFullName : uuid.compareTo(r.uuid);
    }
}
