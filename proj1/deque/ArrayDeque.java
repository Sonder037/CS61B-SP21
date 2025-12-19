package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>{
    private T[] items;
    private int size;
    private int head, tail;
    private int capacity;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        head = 4;
        tail = 3;
        capacity = 8;
    }

    private void resize(int newCapacity) {
        int newHead = newCapacity / 2;
        int newTail = newCapacity / 2 - 1;
        T[] newItems = (T[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newItems[(newTail + 1) % newCapacity] = items[(head + i) % capacity];
            newTail = (newTail + 1) % newCapacity;
        }
        items = newItems;
        head = newHead;
        tail = newTail;
        capacity = newCapacity;
    }

    public void addFirst(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        items[(head - 1 + capacity) % capacity] = item;
        head = (head - 1 + capacity) % capacity;
        size++;
    }

    public void addLast(T item) {
        if (size == capacity) {
            resize(size * 2);
        }
        items[(tail + 1) % capacity] = item;
        tail = (tail + 1) % capacity;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(head + i) % capacity] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[head % capacity];
        items[head % capacity] = null;
        head = (head + 1) % capacity;
        size--;
        if (size > 16 && size < capacity / 4 && capacity / 2 >= 8) {
            resize(capacity / 4);
        }
        return item;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[tail % capacity];
        items[tail % capacity] = null;
        tail = (tail - 1 + capacity) % capacity;
        size--;
        if (size > 16 && size < capacity / 4 && capacity / 2 >= 8) {
            resize(capacity / 4);
        }
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(head + index) % capacity];
    }

    public Iterator<T> iterator() {
        return new ArratDequeueIterator();
    }

    private class ArratDequeueIterator implements Iterator<T> {
        private int p = 0;
        @Override
        public boolean hasNext() {
            return p < size;
        }
        @Override
        public T next() {
            T item = items[(head + p) % capacity];
            p++;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (size != other.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!items[(head + i) % capacity].equals(other.items[(other.head + i) % other.capacity])) {
                return false;
            }
        }
        return true;
    }
}
