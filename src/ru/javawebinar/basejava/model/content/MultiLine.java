package ru.javawebinar.basejava.model.content;

import java.util.ArrayList;
import java.util.List;

public class MultiLine implements Section {
    private List<Content> rows;

    public MultiLine() {
        rows = new ArrayList<>();
    }

    public MultiLine(List<Content> rows) {
        this.rows = rows;
    }

    @Override
    public void addRow(Content row) {
        rows.add(row);
    }
}
