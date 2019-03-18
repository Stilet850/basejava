import java.util.*;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int DEFAULT_LENGTH = 10000;
    private Resume[] storage = new Resume[DEFAULT_LENGTH];
    private int lastId = 0;

    void clear() {
		Arrays.fill(this.storage, null);
    }

    void save(Resume r) {
    this.storage[lastId]= r;
    lastId++;
    }

    Resume get(String uuid) {
          return Arrays.stream(this.storage).filter(Objects::nonNull).filter(resume -> resume.uuid.equals(uuid)).findFirst().orElse(new Resume());
    }

    void delete(String uuid) {
        this.storage = Arrays.stream(this.storage).filter(Objects::nonNull).filter(resume -> !(resume.uuid.equals(uuid))).toArray(Resume[]::new);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.stream(this.storage).filter(Objects::nonNull).toArray(Resume[]::new);
    }

    int size() {
       return this.storage.length;
    }
}
