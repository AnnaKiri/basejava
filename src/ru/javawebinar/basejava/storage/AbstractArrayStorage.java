package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int MAX_SIZE = 10000;
    protected final Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;

    public int size() {
        return size;
    }

    public void save(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (size >= MAX_SIZE) {
            System.out.println("Database is full, you can't add more resume");
        } else if (index >= 0) {
            System.out.println("Database already contains this resume with uuid = " + resume.getUuid());
        } else {
            insertElement(resume, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            deleteElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            printWarningMessage(uuid, "is not found in database");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index >= 0) {
            return storage[index];
        } else {
            printWarningMessage(uuid, "is not found in database");
            return null;
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
            printWarningMessage(resume.getUuid(), "was updated");
        } else {
            printWarningMessage(resume.getUuid(), "is not found in database");
        }
    }

    protected void printWarningMessage(String uuid, String message) {
        System.out.println("Resume with uuid = " + uuid + " " + message);
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);
}
