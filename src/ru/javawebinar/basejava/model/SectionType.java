package ru.javawebinar.basejava.model;

public enum SectionType {
    OBJECTIVE("Job position"),
    PERSONAL("Personal qualities"),
    ACHIEVEMENT("Achievements"),
    QUALIFICATIONS("Qualifications"),
    EXPERIENCE("Experience"),
    EDUCATION("Education ");

    private String title;

    SectionType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
