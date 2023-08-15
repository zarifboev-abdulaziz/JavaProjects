package lesson;

import java.util.LinkedList;

public class LessonHashTable {


    private LinkedList<Entry>[] elements = new LinkedList[5];


    private class Entry{
        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(int key, String value){
        int hashIndex = hash(key);

        LinkedList<Entry> list = elements[hashIndex];
        if (list == null){
            list = new LinkedList<>();
        }

        for (Entry entry : list) {
            if (entry.key == key){
                entry.value = value;
                return;
            }
        }

        list.addLast(new Entry(key, value));
    }

    public String get(int key){
        int hashIndex = hash(key);

        LinkedList<Entry> list = elements[hashIndex];
        if (list != null){
            for (Entry entry : list) {
                if (entry.key == key){
                    return entry.value;
                }
            }
        }
        return null;
    }

    private int hash(int key){
        return key % elements.length;
    }



}
