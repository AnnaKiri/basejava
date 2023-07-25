package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class ListOrganizationSection extends Section {
    private final List<Organization> organizationList;

    public ListOrganizationSection(List<Organization> organizationList) {
        this.organizationList = organizationList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListOrganizationSection that = (ListOrganizationSection) o;
        return Objects.equals(organizationList, that.organizationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationList);
    }

    @Override
    public String toString() {
        return organizationList.toString();
    }
}
