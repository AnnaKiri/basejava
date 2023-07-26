package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class Company {
    private final List<Period> periods;
    private final String company;
    private final String link;

    public Company(List<Period> periods, String company, String link) {
        this.periods = periods;
        this.company = company;
        this.link = link;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getCompany() {
        return company;
    }

    public String getLink() {
        return link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return Objects.equals(periods, that.periods) && Objects.equals(company, that.company) && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, company, link);
    }

    @Override
    public String toString() {
        return periods.get(0).getStartTime() + "-" + periods.get(0).getEndTime() +
                " " + company +
                " " + link + " " +
                periods.get(0).getPosition() + " " +
                periods.get(0).getDescription();
    }
}
