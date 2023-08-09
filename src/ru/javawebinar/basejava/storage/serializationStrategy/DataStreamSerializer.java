package ru.javawebinar.basejava.storage.serializationStrategy;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements SerializationStrategy {
    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());

            Map<ContactType, String> contacts = resume.getContacts();

            writeWithException(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

            writeWithException(dos, resume.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section section = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE, PERSONAL:
                        dos.writeUTF((((TextSection) section).getDescription()));
                        break;
                    case ACHIEVEMENT, QUALIFICATIONS:
                        writeWithException(dos, ((ListTextSection) section).getStrings(), dos::writeUTF);
                        break;
                    case EXPERIENCE, EDUCATION:
                        writeWithException(dos, ((CompanySection) section).getCompanies(), company -> {
                            writeWithException(dos, company.getPeriods(), period -> {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getPosition());
                                dos.writeUTF(period.getDescription());
                            });
                            dos.writeUTF(company.getName());
                            dos.writeUTF(company.getWebsite());
                        });
                        break;
                    default:
                        throw new StorageException("Section " + sectionType + " was not written", null);
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            resume = new Resume(dis.readUTF(), dis.readUTF());
            int contactsSize = dis.readInt();
            for (int i = 0; i < contactsSize; i++) {
                resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sectionsSize = dis.readInt();
            for (int i = 0; i < sectionsSize; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());

                switch (sectionType) {
                    case OBJECTIVE, PERSONAL:
                        TextSection textSection = new TextSection(dis.readUTF());
                        resume.setSections(sectionType, textSection);
                        break;
                    case ACHIEVEMENT, QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int listSize = dis.readInt();
                        for (int l = 0; l < listSize; l++) {
                            String line = dis.readUTF();
                            list.add(line);
                        }
                        ListTextSection listTextSection = new ListTextSection(list);
                        resume.setSections(sectionType, listTextSection);
                        break;
                    case EXPERIENCE, EDUCATION:
                        List<Company> companyList = new ArrayList<>();
                        int companyListSize = dis.readInt();
                        for (int l = 0; l < companyListSize; l++) {

                            List<Period> periodList = new ArrayList<>();
                            int periodListSize = dis.readInt();
                            for (int k = 0; k < periodListSize; k++) {
                                LocalDate startDate = LocalDate.parse(dis.readUTF());
                                LocalDate endDate = LocalDate.parse(dis.readUTF());
                                String position = dis.readUTF();
                                String description = dis.readUTF();
                                Period period = new Period(startDate, endDate, position, description);

                                periodList.add(period);
                            }

                            String name = dis.readUTF();
                            String website = dis.readUTF();
                            Company company = new Company(periodList, name, website);

                            companyList.add(company);
                        }

                        CompanySection companySection = new CompanySection(companyList);
                        resume.setSections(sectionType, companySection);
                        break;
                    default:
                        throw new StorageException("Section " + sectionType + " can not be read", null);
                }
            }
        }
        return resume;
    }

    @FunctionalInterface
    private interface ConsumerWrite<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, ConsumerWrite<T> consumerWrite) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            consumerWrite.write(item);
        }
    }
}
