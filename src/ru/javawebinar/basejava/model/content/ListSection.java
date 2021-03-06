package ru.javawebinar.basejava.model.content;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends Section {
    private static final long serialVersionUID = 2L;
    public static final Section EMPTY = new ListSection("");

    private List<String> items;

    public ListSection() {
    }

    public ListSection(String ... items) {
        this(asList(items));
    }

    public ListSection(List<String> items) {
        requireNonNull(items, "items must not be null");
        this.items = items;
    }

    public String getSection(){
      return items.stream().collect(Collectors.joining("\\n"));
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "ListSection{" +
                "items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSection that = (ListSection) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }
}
