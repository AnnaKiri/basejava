package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object searchKey = getNotExistingSearchKey(resume.getUuid());
        doUpdate(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getExistingSearchKey(resume.getUuid());
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getNotExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract int getIndex(String uuid);

    private Object getExistingSearchKey(String uuid) throws ExistStorageException{
        Object searchKey = getIndex(uuid);
        if (isExist(searchKey)) {
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    private Object getNotExistingSearchKey(String uuid) throws NotExistStorageException{
        Object searchKey = getIndex(uuid);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;

    }
}
