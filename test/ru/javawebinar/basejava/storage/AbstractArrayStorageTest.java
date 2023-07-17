package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_4 = new Resume(UUID_4);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void updateNormal() throws Exception {
        Resume resume = new Resume(UUID_2);
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resume = new Resume(UUID_4);
        storage.update(resume);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resume = storage.getAll();
        Assert.assertEquals(3, resume.length);
        Assert.assertEquals(resume[0], RESUME_1);
        Assert.assertEquals(resume[1], RESUME_2);
        Assert.assertEquals(resume[2], RESUME_3);
    }

    @Test
    public void saveNormal() throws Exception {
        storage.save(new Resume(UUID_4));
        Assert.assertEquals(4, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(RESUME_2);
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        for (int i = 4; i <= AbstractArrayStorage.MAX_SIZE; i++) {
            try {
                storage.save(new Resume("uuid" + i));
            } catch (StorageException e){
                Assert.fail("Overflow happens too early");
            }
        }
        storage.save(new Resume("uuid" + AbstractArrayStorage.MAX_SIZE + 1));
    }

    @Test
    public void deleteNormal() throws Exception {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get(UUID_1);
        Assert.assertEquals(RESUME_1, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }
}