package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage{

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            printWarningMessage(resume.getUuid(), "was updated");
        } else {
            printWarningMessage(resume.getUuid(), "is not found in database");
        }
    }

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (size >= MAX_SIZE) {
            System.out.println("Database is full, you can't add more resume");
        } else if (index != -1) {
            System.out.println("Database already contains this resume with uuid = " + r.getUuid());
        } else {
            storage[size++] = r;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
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

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}
