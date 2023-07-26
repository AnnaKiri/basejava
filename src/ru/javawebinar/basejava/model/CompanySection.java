package ru.javawebinar.basejava.model;

import java.util.List;
import java.util.Objects;

public class CompanySection extends Section {
    private final List<Company> companyList;

    public CompanySection(List<Company> organizationList) {
        this.companyList = organizationList;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompanySection that = (CompanySection) o;
        return Objects.equals(companyList, that.companyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyList);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Company list : companyList) {
            stringBuilder.append("\n");
            stringBuilder.append("• ");
            stringBuilder.append(list);
        }
        stringBuilder.append("\n");
        return  stringBuilder.toString();
    }
}
