import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    public static final int MAX_SIZE = 10000;
    private Resume[] storage;
    private int size;

    public ArrayStorage() {
        this.storage = new Resume[MAX_SIZE];
        this.size = 0;
    }

    void clear() {
        if (size == 0) {
            return;
        }
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    void save(Resume r) {
        if (size == MAX_SIZE) {
            return;
        }
        storage[size++] = r;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    int size() {
        return size;
    }
}
