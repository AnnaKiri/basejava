package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(new JsonStreamSerializer(), STORAGE_DIR.getAbsolutePath()));
    }
}
