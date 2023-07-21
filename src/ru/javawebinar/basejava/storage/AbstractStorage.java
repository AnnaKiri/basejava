package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object searchKey = getIndex(resume.getUuid());
        if ((Integer)searchKey >= 0) {
            doUpdate(resume, (Integer)searchKey);
        } else {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getIndex(resume.getUuid());
        if ((Integer)searchKey >= 0) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            doSave(resume, (Integer)searchKey);
        }
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getIndex(uuid);
        if ((Integer)searchKey >= 0) {
            return doGet((Integer)searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getIndex(uuid);
        if ((Integer)searchKey >= 0) {
            doDelete((Integer)searchKey);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    protected abstract void doUpdate(Resume resume, int index);

    protected abstract void doSave(Resume resume, int index);

    protected abstract Resume doGet(int index);

    protected abstract void doDelete(int index);

    protected abstract int getIndex(String uuid);
}
