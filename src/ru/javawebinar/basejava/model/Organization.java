package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Organization {
    private final String startTime;
    private final String endTime;
    private final String organization;
    private final String link;
    private final String position;
    private final String description;

    public Organization(String startTime, String endTime, String organization, String link, String position, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.organization = organization;
        this.link = link;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization that = (Organization) o;
        return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(organization, that.organization) && Objects.equals(link, that.link) && Objects.equals(position, that.position) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, organization, link, position, description);
    }

    @Override
    public String toString() {
        return startTime +
                "-" + endTime +
                " " + organization +
                " " + link +
                " " + position +
                " " + description;
    }
}
