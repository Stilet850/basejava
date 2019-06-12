package ru.javawebinar.basejava.model;

import java.time.LocalDate;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        //TODO: validation
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
