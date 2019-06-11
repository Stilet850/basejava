package ru.javawebinar.basejava.model.content;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class MultiLineSection implements Section {
    private final List<Content> rows;

    public MultiLineSection(List<Content> rows) {
        requireNonNull(rows, "Rows must not be null");
        this.rows = rows;
    }

    public List<Content> getRows() {
        return rows;
    }

    @Override
    public String toString() {
        return "MultiLineSection{" +
                "rows=" + rows +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiLineSection multiLine = (MultiLineSection) o;
        return rows.equals(multiLine.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }
}
