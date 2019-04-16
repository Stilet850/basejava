package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import static java.lang.System.arraycopy;
import static java.util.Arrays.binarySearch;

public class SortedArrayStorage extends AbstractArrayStorage{

    @Override
    protected void remove(int index) {
        int num = size - index - 1;
        if(num > 0){
            arraycopy(storage, index+1,storage, index, num);
        }
    }

    @Override
    protected void insert(Resume r, int index) {
        int insertIdx = -index - 1;
        arraycopy(storage,insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = r;
    }

    @Override
    protected int getIndex(String uuid) {
        return binarySearch(storage, 0, size, new Resume(uuid));
    }
}
