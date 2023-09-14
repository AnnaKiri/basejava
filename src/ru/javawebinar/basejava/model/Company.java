package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Company implements Serializable {
    public static final Company EMPTY = new Company("", "", Period.EMPTY);
    private static final long serialVersionUID = 1L;

    private List<Period> periods;
    private String name;
    private String website;

    public Company() {
    }

    public Company(String name, String website, Period... periods) {
        this(Arrays.asList(periods), name, website);
    }

    public Company(List<Period> periods, String company, String link) {
        this.periods = periods;
        this.name = company;
        this.website = link;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public String getName() {
        return name;
    }

    public String getWebsite() {
        return website;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company that = (Company) o;
        return Objects.equals(periods, that.periods) && Objects.equals(name, that.name) && Objects.equals(website, that.website);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periods, name, website);
    }

    @Override
    public String toString() {
        return periods.get(0).getStartDate() + "-" + periods.get(0).getEndDate() +
                " " + name +
                " " + website + " " +
                periods.get(0).getPosition() + " " +
                periods.get(0).getDescription();
    }
}
