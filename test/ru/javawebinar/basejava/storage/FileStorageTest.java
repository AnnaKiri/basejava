package ru.javawebinar.basejava.storage;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(new SerializationStrategyObjectStream(), STORAGE_DIR));
    }
}
