package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not directory");
        }
        if (!directory.canRead() || directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + "is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected void doUpdate(Resume resume, File file) {
// примерно то же самое что и в create, только не надо создавать файл, а написать doWrite
    }

    @Override
    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    @Override
    protected Resume doGet(File searchKey) {
//        нужен абстрактый метод который читает резюме из файла doRead
        return null;
    }

    @Override
    protected void doDelete(File file) {

    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    @Override
    protected List<Resume> getList() {
// читает резюме из каталога, метод тот же doRead, пока не реализовывать doRead

        return null;
    }

    @Override
    public void clear() {
// получить и удалить из каталога все резюме
    }

    @Override
    public int size() {
//        посчитать количество сколько файлов в каталоге
        return 0;
    }

    protected abstract void doWrite(Resume resume, File file) throws IOException;

}
