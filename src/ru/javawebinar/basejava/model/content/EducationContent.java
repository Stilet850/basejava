package ru.javawebinar.basejava.model.content;

import java.time.LocalDate;

public class EducationContent extends AbstractOrganizationContent {
    public EducationContent(String name, String url, LocalDate startDate, LocalDate endDate, String content) {
        super(name, url, startDate, endDate, content);
    }
}
