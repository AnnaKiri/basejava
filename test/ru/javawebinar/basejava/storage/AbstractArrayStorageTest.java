package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        for (int i = 1; i <= AbstractArrayStorage.MAX_SIZE; i++) {
            try {
                storage.save(new Resume("uuid" + i, "Name" + i));
            } catch (StorageException e) {
                Assert.fail("Overflow happens too early");
            }
        }
        storage.save(new Resume("uuid" + AbstractArrayStorage.MAX_SIZE + 1, "Name" + AbstractArrayStorage.MAX_SIZE + 1));
    }
}