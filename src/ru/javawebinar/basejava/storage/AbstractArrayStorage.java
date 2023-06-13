package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorage implements Storage{
    protected static final int MAX_SIZE = 10000;
    protected Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index != -1) {
            return storage[index];
        } else {
            printWarningMessage(uuid, "is not found in database");
            return null;
        }
    }

    protected void printWarningMessage(String uuid, String message) {
        System.out.println("Resume with uuid = " + uuid + " " + message);
    }

    protected abstract int getIndex(String uuid);
}
