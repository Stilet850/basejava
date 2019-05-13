package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage {

//    private static class ResumeComparator implements Comparator<Resume> {
//        @Override
//        public int compare(Resume o1, Resume o2) {
//            return o1.getUuid().compareTo(o2.getUuid());
//        }
//    }

    private static final Comparator<Resume> RESUME_COMPARATOR = (o1, o2) -> compare(o1, o2);

    private static int compare(Resume o1, Resume o2) {
        return o1.getUuid().compareTo(o2.getUuid());
    }

    @Override
    protected void remove(int index) {
        int num = size - index - 1;
        if (num > 0) arraycopy(storage, index + 1, storage, index, num);
    }

    @Override
    protected void insert(Resume resume, int index) {
        int insertIdx = -index - 1;
        arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return binarySearch(storage, 0, size, new Resume(uuid, ""), RESUME_COMPARATOR);
    }
}
