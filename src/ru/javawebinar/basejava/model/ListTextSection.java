package ru.javawebinar.basejava.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListTextSection extends Section {
    private static final long serialVersionUID = 1L;

    private List<String> strings;

    public ListTextSection() {
    }

    public ListTextSection(String... strings) {
        this(Arrays.asList(strings));
    }

    public ListTextSection(List<String> stringList) {
        Objects.requireNonNull(stringList, "descriptions must not be null");
        this.strings = stringList;
    }

    public List<String> getStrings() {
        return strings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListTextSection that = (ListTextSection) o;
        return Objects.equals(strings, that.strings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strings);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String list : strings) {
            stringBuilder.append("\n");
            stringBuilder.append("• ");
            stringBuilder.append(list);
        }
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
