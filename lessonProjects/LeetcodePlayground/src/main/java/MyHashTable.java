import java.util.LinkedList;

public class MyHashTable {
    LinkedList<Entry>[] table = new LinkedList[5];

    public String get(int key) {
        int hashIndex = hashFunction(key);

        LinkedList<Entry> list = table[hashIndex];
        if (list != null){
            for (Entry entry : list) {
                if (entry.key == key){
                    return entry.value;
                }
            }
        }
        return null;
    }

    public void remove(int key) {
        int hashIndex = hashFunction(key);

        LinkedList<Entry> list = table[hashIndex];
        if (list != null){
            for (Entry entry : list) {
                if (entry.key == key){
                    list.remove(entry);
                    break;
                }
            }
        }
    }

    private class Entry{
        int key;
        String value;

        public Entry(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(int key, String value) {
        int hashIndex = hashFunction(key);
        if (table[hashIndex] == null) {
            table[hashIndex] = new LinkedList<>();
        }
        LinkedList<Entry> list = table[hashIndex];

        for (Entry entry : list) {
            if (entry.key == key){
                entry.value = value;
                return;
            }
        }

        list.addLast(new Entry(key, value));
    }

    private int hashFunction(int key){
        return key % table.length;
    }

    public String toString(){
        StringBuilder builder = new StringBuilder("[");
        for (LinkedList<Entry> entries : table) {
            if (entries != null){
                for (Entry entry : entries) {
                    builder.append(entry.key).append("=").append(entry.value).append(", ");
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }

}
