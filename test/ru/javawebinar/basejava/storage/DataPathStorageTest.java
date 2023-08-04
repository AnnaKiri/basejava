package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.DataStreamSerializer;

public class DataPathStorageTest extends AbstractStorageTest {
    public DataPathStorageTest() {
        super(new PathStorage(new DataStreamSerializer(), STORAGE_DIR.getAbsolutePath()));
    }
}
