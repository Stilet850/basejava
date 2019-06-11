package ru.javawebinar.basejava.model.content;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class JobContent extends AbstractOrganizationContent {
    private final String position;

    public JobContent(String name, String url, LocalDate startDate, LocalDate endDate, String content, String position) {
        super(name, url, startDate, endDate, content);
        requireNonNull(position, "Position must not be null");
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "JobContent{" +
                "position='" + position + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JobContent that = (JobContent) o;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), position);
    }
}
