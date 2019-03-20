import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int DEFAULT_LENGTH = 10000;
    private Resume[] storage = new Resume[DEFAULT_LENGTH];
    private int size;

    void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    void save(Resume r) {
        if (r == null || r.uuid == null || r.uuid.isEmpty()) {
            return;
        }

        storage[size] = r;
        size++;
    }

    Resume get(String uuid) {
        return Arrays.stream(storage).filter(Objects::nonNull).filter(resume -> resume.uuid.equals(uuid)).findFirst().orElse(new Resume());
    }

    void delete(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].uuid)) {
                storage[i] = storage[size() - 1];
                storage[size() - 1] = null;
                size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    int size() {
        return size;
    }
}
