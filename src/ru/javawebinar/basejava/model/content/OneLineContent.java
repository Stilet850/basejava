package ru.javawebinar.basejava.model.content;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class OneLineContent implements Content {
    private final String row;

    public OneLineContent(String row) {
        requireNonNull(row, "Row must not be null");
        this.row = row;
    }

    public String getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "OneLineContent{" +
                "row='" + row + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneLineContent oneLine = (OneLineContent) o;
        return row.equals(oneLine.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}