package ru.javawebinar.basejava.model.content;

import ru.javawebinar.basejava.model.Period;

public class Job extends AbstractInstitution {
    private String position;

    public Job(String name, Period period, String content, String position) {
        super(name, period, content);
        this.position = position;
    }
}
