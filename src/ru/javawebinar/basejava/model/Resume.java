package ru.javawebinar.basejava.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Initial resume class
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Comparable<Resume>, Serializable {
    private static final long serialVersionUID = 1L;
    public static final Comparator<Resume> RESUME_COMPARATOR =
            Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public static final Resume EMPTY = new Resume();

    static {
        EMPTY.setSections(SectionType.OBJECTIVE, TextSection.EMPTY);
        EMPTY.setSections(SectionType.PERSONAL, TextSection.EMPTY);
        EMPTY.setSections(SectionType.ACHIEVEMENT, ListTextSection.EMPTY);
        EMPTY.setSections(SectionType.QUALIFICATIONS, ListTextSection.EMPTY);
        EMPTY.setSections(SectionType.EXPERIENCE, new CompanySection(Company.EMPTY));
        EMPTY.setSections(SectionType.EDUCATION, new CompanySection(Company.EMPTY));
    }

    private String fullName;

    private final Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private final Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

    private String uuid;

    public Resume() {
    }

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

    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    public Section getSection(SectionType sectionType) {
        return sections.get(sectionType);
    }

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSectionByType(SectionType type) {
        switch (type) {
            case OBJECTIVE:
            case PERSONAL:
                return ((TextSection) sections.get(type)).getDescription();
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return String.join("\n", ((ListTextSection) sections.get(type)).getStrings());
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Resume resume = (Resume) o;
        return Objects.equals(fullName, resume.fullName) && Objects.equals(contacts, resume.contacts) && Objects.equals(sections, resume.sections) && Objects.equals(uuid, resume.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, contacts, sections, uuid);
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
        stringBuilder.append("\n");

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
