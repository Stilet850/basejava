import java.util.Arrays;
import java.util.Objects;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int DEFAULT_LENGTH = 10000;
    private Resume[] storage = new Resume[DEFAULT_LENGTH];

    void clear() {
        Arrays.fill(this.storage, null);
    }

    void save(Resume r) {
        if (r == null || r.uuid == null || r.uuid.isEmpty()) {
            return;
        }

        for (int i = 0; i < this.storage.length; i++) {
            if (this.storage[i] == null) {
                this.storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        return Arrays.stream(this.storage).filter(Objects::nonNull).filter(resume -> resume.uuid.equals(uuid)).findFirst().orElse(new Resume());
    }

    void delete(String uuid) {
        for (int i = 0; i < this.storage.length; i++) {
            if (this.storage[i] != null && uuid.equals(this.storage[i].uuid)) {
                this.storage[i] = this.storage[size() - 1];
                this.storage[size() - 1] = null;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(this.storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    /**
     * @return size of array (nonNull) containing Resumes.
     */
    int size() {
        return (int) Arrays.stream(this.storage).filter(Objects::nonNull).count();
    }
}
