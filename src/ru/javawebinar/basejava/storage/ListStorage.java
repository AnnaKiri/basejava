package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    protected final List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    protected void doUpdate(Resume resume, int index) {
        listStorage.set(index, resume);
    }

    @Override
    protected void doSave(Resume resume, int index) {
        listStorage.add(resume);
    }

    @Override
    protected Resume doGet(int index) {
        return listStorage.get(index);
    }

    @Override
    protected void doDelete(int index) {
        listStorage.remove(index);
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < listStorage.size(); i++) {
            if (listStorage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
