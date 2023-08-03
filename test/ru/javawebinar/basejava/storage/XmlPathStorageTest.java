package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.storage.serializationStrategy.XmlStreamSerializer;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(new XmlStreamSerializer(), STORAGE_DIR.getAbsolutePath()));
    }
}
