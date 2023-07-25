package ru.javawebinar.basejava.model;

import java.util.Objects;

public class Period {
    private final String startTime;
    private final String endTime;
    private final String position;
    private final String description;

    public Period(String startTime, String endTime, String position, String description) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.position = position;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Period period = (Period) o;
        return Objects.equals(startTime, period.startTime) && Objects.equals(endTime, period.endTime) && Objects.equals(position, period.position) && Objects.equals(description, period.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime, position, description);
    }

    @Override
    public String toString() {
        return startTime +
                "-" + endTime
                + position
                + description;
    }
}
