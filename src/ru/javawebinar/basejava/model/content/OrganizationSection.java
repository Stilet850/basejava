package ru.javawebinar.basejava.model.content;

import ru.javawebinar.basejava.model.Organization;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganizationSection extends Section {
    private static final long serialVersionUID = 1L;
    private List<Organization> organizations;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization ... organizations) {
        this(asList(organizations));
    }

    public OrganizationSection(List<Organization> organizations) {
        requireNonNull(organizations, "Organizations must not ne null");
        this.organizations = organizations;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    @Override
    public String toString() {
        return "OrganizationSection{" +
                "organizations=" + organizations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrganizationSection that = (OrganizationSection) o;
        return organizations.equals(that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizations);
    }
}
