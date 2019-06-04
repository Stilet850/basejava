package ru.javawebinar.basejava.model.content;

public class SingleLine implements Section {
    private Content row;

    public SingleLine(Content row) {
        this.row = row;
    }

    @Override
    public void addRow(Content row) {
        this.row = row;
    }
}
