package ru.javawebinar.basejava.model.content;

import java.util.Objects;

public class OneLineSection implements Section {
    private final Content row;

    public OneLineSection(Content row) {
        this.row = row;
    }

    @Override
    public String toString() {
        return "OneLineSection{" +
                "row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OneLineSection that = (OneLineSection) o;
        return Objects.equals(row, that.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
