package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int MAX_SIZE = 10000;
    private Resume[] storage;
    private int size;

    public ArrayStorage() {
        storage = new Resume[MAX_SIZE];
    }

    public void clear() {
        if (size == 0) {
            return;
        }
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndexByUuid(resume.getUuid());

        if (index != -1) {
            storage[index] = resume;
            printWarningMessage(resume.getUuid(), "was updated");
        } else {
            printWarningMessage(resume.getUuid(), "is not found in database");
        }
    }

    public void save(Resume r) {
        if (size >= MAX_SIZE) {
            System.out.println("Database is full, you can't add more resume");
            return;
        }

        int index = getIndexByUuid(r.getUuid());

        if (index == -1) {
            storage[size++] = r;
        } else {
            System.out.println("Database already consist this resume with uuid = " + r.getUuid());
        }
    }

    public Resume get(String uuid) {
        int index = getIndexByUuid(uuid);

        if (index != -1) {
            return storage[index];
        } else {
            printWarningMessage(uuid, "is not found in database");
            return null;
        }
    }

    public void delete(String uuid) {
        int index = getIndexByUuid(uuid);

        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            printWarningMessage(uuid, "is not found in database");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int getIndexByUuid(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }

        return -1;
    }

    private void printWarningMessage(String uuid, String message) {
        System.out.println("Resume with uuid = " + uuid + " " + message);
    }

}
