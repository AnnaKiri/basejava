package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListTextSection extends Section {
    private final List<String> stringList;

    public ListTextSection(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getStringList() {
        return stringList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListTextSection that = (ListTextSection) o;
        return Objects.equals(stringList, that.stringList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String list : stringList) {
            stringBuilder.append("\n");
            stringBuilder.append("• ");
            stringBuilder.append(list);
        }
        stringBuilder.append("\n");
        return  stringBuilder.toString();
    }
}
