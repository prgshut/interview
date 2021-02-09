package gb.lesson2.arrayList;

import gb.lesson2.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 8;
    protected E[] data;
    protected int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int initCapacity) {
        this.data = (E[]) new Comparable[initCapacity];
    }

    @Override
    public boolean add(E value) {
        checkAndGrow();
        data[size++] = value;
        return true;
    }

    public void insert(E value, int index) {
        checkAndGrow();
        if (index == size) {
            add(value);
        } else {
            checkIndex(index);
            if (size - index >= 0) {
                System.arraycopy(data, index, data, index + 1, size - index);
            }
            data[index] = value;
            size++;
        }
    }

    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        return index != -1 && (remove(index) != null);
    }

    @Override
    public boolean contains(E val) {
        return false;
    }


    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        if (size - 1 - index >= 0) {
            System.arraycopy(data, index + 1, data, index, size - 1 - index);
        }
        data[size - 1] = null;
        size--;
        return removedValue;
    }


    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpti() {
        return false;
    }


    public void trimToSize() {
        data = Arrays.copyOf(data, size);
    }


    private void swap(int indexA, int indexB) {
        E temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    private int calculateNewLength() {
        return size > 0 ? size * 2 : 1;
    }


    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Object next() {
        return null;
    }
}
