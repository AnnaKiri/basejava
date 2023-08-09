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
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume = new Resume(dis.readUTF(), dis.readUTF());

            fillMap(dis, () -> resume.setContacts(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            fillMap(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());

                switch (sectionType) {
                    case OBJECTIVE, PERSONAL:
                        resume.setSections(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT, QUALIFICATIONS:
                        resume.setSections(sectionType, new ListTextSection(readWithException(dis, dis::readUTF)));
                        break;
                    case EXPERIENCE, EDUCATION:
                        resume.setSections(sectionType, new CompanySection(
                                readWithException(dis, () -> new Company(
                                        readWithException(dis, () -> new Period(
                                                LocalDate.parse(dis.readUTF()),
                                                LocalDate.parse(dis.readUTF()),
                                                dis.readUTF(),
                                                dis.readUTF()
                                        )),
                                        dis.readUTF(),
                                        dis.readUTF()
                                ))
                        ));
                        break;
                    default:
                        throw new StorageException("Section " + sectionType + " can not be read", null);
                }
            });
            return resume;
        }
    }

    private <T> List<T> readWithException(DataInputStream dis, ConsumerRead<T> consumerRead) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(consumerRead.read());
        }
        return list;
    }

    private void fillMap(DataInputStream dis, ConsumerAdd consumerAdd) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            consumerAdd.addItem();
        }
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, ConsumerWrite<T> consumerWrite) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            consumerWrite.write(item);
        }
    }

    @FunctionalInterface
    private interface ConsumerRead<T> {
        T read() throws IOException;
    }

    @FunctionalInterface
    private interface ConsumerAdd<T> {
        void addItem() throws IOException;
    }

    @FunctionalInterface
    private interface ConsumerWrite<T> {
        void write(T t) throws IOException;
    }
}
