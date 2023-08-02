package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.SerializationStrategyObjectStream;

public class FileStorageTest extends AbstractStorageTest {

    public FileStorageTest() {
        super(new FileStorage(new SerializationStrategyObjectStream(), STORAGE_DIR));
    }
}
