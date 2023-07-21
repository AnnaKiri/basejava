package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public void update(Resume resume) {
        Object searchKey = getIndex(resume.getUuid());
        if (!isExist(searchKey)) {
            getNotExistingSearchKey(resume.getUuid());
        }
        doUpdate(resume, searchKey);
    }

    @Override
    public void save(Resume resume) {
        Object searchKey = getIndex(resume.getUuid());
        if (isExist(searchKey)) {
            getExistingSearchKey(resume.getUuid());
        }
        doSave(resume, searchKey);
    }

    @Override
    public Resume get(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExist(searchKey)) {
            getNotExistingSearchKey(uuid);
        }
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getIndex(uuid);
        if (!isExist(searchKey)) {
            getNotExistingSearchKey(uuid);
        }
        doDelete(searchKey);
    }

    protected abstract boolean isExist(Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doDelete(Object searchKey);

    protected abstract int getIndex(String uuid);

    private void getExistingSearchKey(String uuid) throws ExistStorageException{
        throw new ExistStorageException(uuid);
    }

    private void getNotExistingSearchKey(String uuid) throws NotExistStorageException{
        throw new NotExistStorageException(uuid);
    }
}
