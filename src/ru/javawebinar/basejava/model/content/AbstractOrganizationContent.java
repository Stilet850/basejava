package ru.javawebinar.basejava.model.content;

import ru.javawebinar.basejava.model.Period;

import java.time.LocalDate;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

abstract class AbstractOrganizationContent implements Content {
    private final String name;
    private final String url;
    private final Period period;
    private final String content;

    AbstractOrganizationContent(String name, String url, LocalDate startDate, LocalDate endDate, String content) {
        requireNonNull(name, "Name must not be null");
        requireNonNull(startDate, "Startdate must not be null");
        requireNonNull(endDate, "EndDate must not be null");
        this.name = name;
        this.url = url;
        this.period = new Period(startDate, endDate);
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractOrganizationContent that = (AbstractOrganizationContent) o;
        return name.equals(that.name) &&
                Objects.equals(url, that.url) &&
                period.equals(that.period) &&
                content.equals(that.content);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, url, period, content);
    }

    @Override
    public String toString() {
        return "AbstractOrganizationContent{" +
                "name='" + name + '\'' +
                ", url=" + url +
                ", period=" + period +
                ", content='" + content + '\'' +
                '}';
    }
}
