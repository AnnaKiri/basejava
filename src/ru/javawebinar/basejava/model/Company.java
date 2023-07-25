package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final List<Period> periods;
    private final String organization;
    private final String link;

    public Company(List<Period> periods, String organization, String link) {
        this.periods = periods;
        this.organization = organization;
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return Objects.equals(periods, that.periods) && Objects.equals(organization, that.organization) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, organization, link);
    }

    @Override
    public String toString() {
        return periods +
                " " + organization +
                " " + link;
    }
}
