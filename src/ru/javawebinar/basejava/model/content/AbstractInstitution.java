package ru.javawebinar.basejava.model.content;

import ru.javawebinar.basejava.model.Period;

abstract class AbstractInstitution implements Content {
    private String name;
    private Period period;
    private String content;

    public AbstractInstitution(String name, Period period, String content) {
        this.name = name;
        this.period = period;
        this.content = content;
    }
}
