package ru.javawebinar.basejava.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume>{

    private final String fullName;
    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    private final String uuid;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(), fullName);
    }

    public Resume(String uuid, String fullName) {
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public void setContacts(ContactType contactType, String value) {
        contacts.put(contactType, value);
    }

    public void setSections(SectionType sectionType, Section section) {
        sections.put(sectionType, section);
    }

    public String getContacts(ContactType contactType) {
        return contacts.get(contactType);
    }

    public Section getSections(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(fullName, resume.fullName) && Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, uuid);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(fullName);

        for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(entry.getKey().getTitle());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue());
        }

        for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
            stringBuilder.append("\n");
            stringBuilder.append(entry.getKey().getTitle());
            stringBuilder.append(": ");
            stringBuilder.append(entry.getValue());
        }

        return stringBuilder.toString();
    }

    @Override
    public int compareTo(Resume o) {
        int result = uuid.compareTo(o.uuid);
        return result == 0 ? fullName.compareTo(o.fullName) : result;
    }
}
