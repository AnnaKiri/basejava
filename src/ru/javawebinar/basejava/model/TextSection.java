package ru.javawebinar.basejava.model;

import java.util.Objects;

public class TextSection extends Section {
    private static final long serialVersionUID = 1L;

    public static final TextSection EMPTY = new TextSection("");

    private String description;

    public TextSection() {
    }

    public TextSection(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextSection that = (TextSection) o;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "\n" + description + "\n";
    }
}
