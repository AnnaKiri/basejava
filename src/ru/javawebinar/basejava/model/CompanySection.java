package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private static final long serialVersionUID = 1L;

    private final List<Company> companies;

    public CompanySection(List<Company> organizationList) {
        this.companies = organizationList;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companies, that.companies);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companies);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Company list : companies) {
            stringBuilder.append("\n");
            stringBuilder.append("• ");
            stringBuilder.append(list);
        }
        stringBuilder.append("\n");
        return  stringBuilder.toString();
    }
}
