package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;

import java.util.*;

public class MainCollection {
    private static final String UUID1 = "uuid1";
    private static final String UUID2 = "uuid2";
    private static final String UUID3 = "uuid3";
    private static final String UUID_NOT_EXIST = "uuid_not_exist";

    private static Resume RESUME_UUID1;
    private static Resume RESUME_UUID2;
    private static Resume RESUME_UUID3;
    private static final Resume RESUME_NOT_EXIST;

    static{
        RESUME_UUID1 = new Resume(UUID1);
        RESUME_UUID2 = new Resume(UUID2);
        RESUME_UUID3 = new Resume(UUID3);
        RESUME_NOT_EXIST = new Resume(UUID_NOT_EXIST);
    }


    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(RESUME_UUID1);
        collection.add(RESUME_UUID2);
        collection.add(RESUME_UUID3);

/*
        for (Resume r : collection) {
            System.out.println(r.getUuid());
            if(r.getUuid().equals(UUID1))
            //             collection.remove(r);
        }
*/

//       System.out.println(collection.toString());

        Iterator<Resume> iterator = collection.iterator();

        while(iterator.hasNext()){
            Resume resume = iterator.next();
            if(resume.getUuid().equals(UUID1))
                         iterator.remove();
        }

               System.out.println(collection.toString());

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID1, RESUME_UUID1);
        map.put(UUID2, RESUME_UUID2);
        map.put(UUID3, RESUME_UUID3);

        for (String uuid: map.keySet()) {
            System.out.println(map.get(uuid ));
        }

        for (Map.Entry<String, Resume> entry:map.entrySet()){
            System.out.println(entry.getValue());
        }
    }

}
