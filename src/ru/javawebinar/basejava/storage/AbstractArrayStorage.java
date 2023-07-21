package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected static final int MAX_SIZE = 10000;
    protected final Resume[] storage = new Resume[MAX_SIZE];
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        if (size >= MAX_SIZE) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, (Integer) searchKey);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        deleteElement((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer)searchKey >= 0;
    }

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);
}
