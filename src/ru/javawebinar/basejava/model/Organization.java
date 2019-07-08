package ru.javawebinar.basejava.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static ru.javawebinar.basejava.util.DateUtil.NOW;
import static ru.javawebinar.basejava.util.DateUtil.of;

public class Organization  implements Serializable {

    private static  final long serialVersionUID = 1L;
    private final Link link;
    private List<Position> positions = new ArrayList<>();

    public Organization(String name, String url, Position... positions) {
        this(new Link(name, url), asList(positions));
    }

    public Organization(Link homePage, List <Position> positions) {
        this.link = homePage;
        this.positions = positions;
    }

    @Override
    public String toString() {
        return "Organization(" +
                 link +
                ", " + positions +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(link, that.link) &&
                Objects.equals(positions, that.positions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(link, positions);
    }

    public static class Position {
        private final String title;
        private final String description;
        private final LocalDate startDate;
        private final LocalDate endDate;

        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }

        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }

        public Position(LocalDate startDate, LocalDate endDate,  String title, String description ) {
            requireNonNull(title, "title must not be null");
            requireNonNull(startDate, "startDate must not be null");
            requireNonNull(endDate, "endDate must not be null");
            this.title = title;
            this.startDate = startDate;
            this.endDate = endDate;
            this.description = description;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", startDate=" + startDate +
                    ", endDate=" + endDate +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return title.equals(position.title) &&
                    Objects.equals(description, position.description) &&
                    startDate.equals(position.startDate) &&
                    endDate.equals(position.endDate);
        }

        @Override
        public int hashCode() {
            return Objects.hash(title, description, startDate, endDate);
        }
    }
}
