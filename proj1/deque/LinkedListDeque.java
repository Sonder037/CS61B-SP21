package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {
    private int size;
    private ListNode<T> head, tail;

    private static class ListNode<T> {
        private T data;
        private ListNode<T> pre, next;

        public ListNode(T data, ListNode<T> pre, ListNode<T> next) {
            this.data = data;
            this.pre = pre;
            this.next = next;
        }
        
        public T getData() {
            return data;
        }
        
        public ListNode<T> getPre() {
            return pre;
        }
        
        public ListNode<T> getNext() {
            return next;
        }
        
        public void setData(T data) {
            this.data = data;
        }
        
        public void setPre(ListNode<T> pre) {
            this.pre = pre;
        }
        
        public void setNext(ListNode<T> next) {
            this.next = next;
        }
    }

    public LinkedListDeque() {
        this.size = 0;
        this.head = new ListNode<>(null, null, null);
        this.tail = new ListNode<>(null, null, null);
        this.head.setNext(this.tail);
        this.tail.setPre(this.head);
    }

    public void addFirst(T item) {
        this.size++;
        ListNode<T> node = new ListNode<>(item, head, head.getNext());
        head.getNext().setPre(node);
        head.setNext(node);
    }

    public void addLast(T item) {
        this.size++;
        ListNode<T> node = new ListNode<>(item, tail.getPre(), tail);
        tail.getPre().setNext(node);
        tail.setPre(node);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ListNode<T> current = head;
        while (current != tail) {
            System.out.print(current.getData() + " ");
            current = current.getNext();
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T ret = head.getNext().getData();
        head.setNext(head.getNext().getNext());
        head.getNext().setPre(head);
        return ret;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size--;
        T ret = tail.getPre().getData();
        tail.setPre(tail.getPre().getPre());
        tail.getPre().setNext(tail);
        return ret;
    }

    public T get(int index) {
        ListNode<T> current = head;
        for (int i = 0; i <= index; i++) {
            current = current.getNext();
            if (current == tail) {
                return null;
            }
        }
        return current.getData();
    }

    private T findData(ListNode<T> node, int index) {
        if (node == tail && index > 0) {
            return null;
        }
        if (index == 0) {
            return node.getData();
        }
        return findData(node.getNext(), index - 1);
    }

    public T getRecursive(int index) {
        ListNode<T> current = head;
        return findData(current.getNext(), index);
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private ListNode<T> current = head.getNext();

        @Override
        public boolean hasNext() {
            return current != tail;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<?> other = (Deque<?>) o;
        if (this.size() != other.size()) {
            return false;
        }
        
        Iterator<?> thisIterator = this.iterator();
        Iterator<?> otherIterator = other.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (!java.util.Objects.equals(thisIterator.next(), otherIterator.next())) {
                return false;
            }
        }
        return true;
    }
}