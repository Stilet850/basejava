package ru.javawebinar.basejava.model;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        requireNonNull(startDate, "startDate must not be null");
        requireNonNull(endDate, "endDate must not be null");

        this.startDate = startDate;
        this.endDate = endDate;
    }
}
