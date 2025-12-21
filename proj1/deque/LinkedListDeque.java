package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private ListNode<T> head, tail;

    private static class ListNode<T> {
        public T data;
        public ListNode<T> pre, next;

        public ListNode(T data, ListNode<T> pre, ListNode<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        this.size = 0;
        this.head = new ListNode<>(null, null, null);
        this.tail = new ListNode<>(null, null, null);
        this.head.next = this.tail;
        this.tail.pre = this.head;
    }

    public void addFirst(T item) {
        this.size++;
        ListNode<T> node = new ListNode<>(item, head, head.next);
        head.next.pre = node;
        head.next = node;
    }

    public void addLast(T item) {
        this.size++;
        ListNode<T> node = new ListNode<>(item, tail.pre, tail);
        tail.pre.next = node;
        tail.pre = node;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode<T> current = head;
        while (current != tail) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T ret = head.next.data;
        head.next = head.next.next;
        head.next.pre = head;
        return ret;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T ret = tail.pre.data;
        tail.pre = tail.pre.pre;
        tail.pre.next = tail;
        return ret;
    }

    public T get(int index) {
        ListNode<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.next;
            if (current == tail) {
                return null;
            }
        }
        return current.data;
    }

    private T findData(ListNode<T> node, int index) {
        if (node == tail && index > 0) {
            return null;
        }
        if (index == 0) {
            return node.data;
        }
        return findData(node.next, index - 1);
    }

    public T getRecursive(int index) {
        ListNode<T> current = head;
        return findData(current.next, index);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private ListNode<T> current = head.next;

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LinkedListDeque<?> that = (LinkedListDeque<?>) o;
        if (size() != that.size()) {
            return false;
        }

        ListNode<T> thisNode = this.head.next;
        ListNode<?> thatNode = that.head.next;
        while (thisNode != this.tail) {
            if (!java.util.Objects.equals(thisNode.data, thatNode.data)) {
                return false;
            }
            thisNode = thisNode.next;
            thatNode = thatNode.next;
        }
        return true;
    }
}
