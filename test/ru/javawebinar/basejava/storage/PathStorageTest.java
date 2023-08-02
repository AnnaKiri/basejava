package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.SerializationStrategyObjectStream;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(new SerializationStrategyObjectStream(), PATH));
    }
}