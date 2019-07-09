package ru.javawebinar.basejava.model.content;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextSection extends Section{
    private static final long serialVersionUID = 1L;

    private String row;

    public TextSection() {
    }

    public TextSection(String row) {
        requireNonNull(row, "row must not be null");
        this.row = row;
    }

    public String getSection() {
        return row;
    }

    @Override
    public String toString() {
        return "TextSection{" +
                "row='" + row + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(row, that.row);
    }

    @Override
    public int hashCode() {
        return Objects.hash(row);
    }
}
