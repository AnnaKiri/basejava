package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.Config;
import ru.javawebinar.basejava.ResumeTestData;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AbstractStorageTest {

    protected static final String PATH = "C:\\IntelliJ IDEA workspace\\basejava\\storage";
    protected static final File STORAGE_DIR = Config.get().getStorageDir();
    protected final Storage storage;

    private static final String UUID_1 = UUID.randomUUID().toString();
    private static final String UUID_2 = UUID.randomUUID().toString();
    private static final String UUID_3 = UUID.randomUUID().toString();
    private static final String UUID_4 = UUID.randomUUID().toString();
    private static final String DUMMY_UUID = UUID.randomUUID().toString();

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = ResumeTestData.createFilledResume(UUID_1, "Name1");
        RESUME_2 = ResumeTestData.createFilledResume(UUID_2, "Name2");
        RESUME_3 = ResumeTestData.createFilledResume(UUID_3, "Name3");
        RESUME_4 = ResumeTestData.createFilledResume(UUID_4, "Name4");
    }

    public AbstractStorageTest(Storage storage) {
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
        assertSize(3);
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
        Assert.assertArrayEquals(new Resume[0], storage.getAllSorted().toArray());
    }

    @Test
    public void update() throws Exception {
        Resume resume = ResumeTestData.createFilledResume(UUID_2, "Name2");
        storage.update(resume);
        Assert.assertTrue(resume.equals(storage.get(UUID_2)));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_4);
    }

    @Test
    public void getAll() throws Exception {
        List<Resume> expected = new ArrayList<>(List.of(RESUME_1, RESUME_2, RESUME_3));
        expected.sort(Resume.RESUME_COMPARATOR);
        List<Resume> actual = storage.getAllSorted();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() throws Exception {
        storage.save(RESUME_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        assertSize(2);
        storage.get(UUID_2);
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get(UUID_1);
        assertGet(resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(DUMMY_UUID);
    }

    public void assertSize(int size) {
        Assert.assertEquals(size, storage.size());
    }

    public void assertGet(Resume resume) {
        Assert.assertTrue(resume.equals(storage.get(resume.getUuid())));
    }

}