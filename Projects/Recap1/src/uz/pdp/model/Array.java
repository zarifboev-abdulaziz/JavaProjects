package uz.pdp.model;


import java.util.HashSet;
import java.util.Set;

public class Array<T> {
    public int initialCapacity = 5;
    T[] elements; // null
    public int size;

    public Array() {
        this.initialCapacity = initialCapacity;
        elements = (T[]) new Object[initialCapacity];
    }

    // CONSTRUCTOR 1 for initial capacity
    public Array(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        elements = (T[]) new Object[initialCapacity]; // = new String [4]
    }

    // CONSTRUCTOR 2 for initial values
    public Array(T... initialElements) {

        elements = (T[]) new Object[initialElements.length]; // = new String [4]
        for (T element : initialElements) {
            add(element);
        }
    }

    public boolean add(T element) {
        // ["Abror", "Eldor", "Sardor"]
        //      0,      1,      2
        if (size >= initialCapacity) {
            initialCapacity *= 2;
            T[] yangiElementlarRuyxati = (T[]) new Object[initialCapacity];
            for (int i = 0; i < elements.length; i++) {
                yangiElementlarRuyxati[i] = elements[i];
            }
            yangiElementlarRuyxati[size++] = element;
            elements = yangiElementlarRuyxati;
            return true;
        }

        elements[size++] = element;
        return true;
    }

    public boolean add(T element, int index) {
        if (index < 0 || index > size) return false;

        if (index == size) return add(element);


        if (size + 1 > initialCapacity) initialCapacity *= 2;

        T[] yangiElementlarRuyxati = (T[]) new Object[initialCapacity];
        int k = 0;
        for (int i = 0; i <= size; i++) {
            if (index == i) {
                yangiElementlarRuyxati[index] = element;
                k++;
            }
            yangiElementlarRuyxati[k++] = elements[i];
        }
        size++;
        elements = yangiElementlarRuyxati;
        return true;
    }


    public boolean remove(int index) {
        if (index < 0 || index >= size) return false;
        size--;
        if (size == initialCapacity / 2) initialCapacity = initialCapacity / 2;
        T[] newList = (T[]) new Object[initialCapacity];
        int j = 0;
        for (int i = 0; i <= size; i++) {
            if (index == i)
                i++;
            newList[j++] = elements[i];
        }
        elements = newList;
        return true;
    }


    @Override
    public String toString() {
        String elementlarRuyxatiT = "";
        for (int i = 0; i < size; i++) {
            if (i == size - 1)
                elementlarRuyxatiT += elements[i];

            else elementlarRuyxatiT += elements[i] + ", ";
        }

        return "[ " + elementlarRuyxatiT + " ]";
    }

    public T get(int index) {
        if (index < 0 || index > size)
            return null;
        return elements[index];
    }

    public Array intersect(Array<T> list) {
        Array<T> result = new Array<>();
        Set<T> firstList = new HashSet<>();
        Set<T> secondList = new HashSet<>();
        for (int i = 0; i < this.size; i++) {
            firstList.add(this.elements[i]);

        }

        for (int i = 0; i < list.size; i++) {
            secondList.add(list.elements[i]);
        }

        for (T t : firstList) {
            if (secondList.contains(t)) {
                result.add(t);
            }
        }

        return result;
    }

    public void reverse() {
        T[] temp = (T[]) new Object[initialCapacity];
        int k = 0;
        for (int i = size - 1; i >= 0; i--) {
            temp[k++] = elements[i];
        }
        elements = temp;
    }

    public void addAll(Array result) {
        for (int i = 0; i < this.size; i++) {
            result.add(this.elements[i]);
        }
    }


}
