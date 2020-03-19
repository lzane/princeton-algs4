import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item item;
        private Node prev;
        private Node next;
    }

    private Node first;
    private Node last;
    private int n;

    // construct an empty deque
    public Deque() {
        first = null;
        last = null;
        n = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the deque
    public int size() {
        return n;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }

        n++;
        // empty
        if (first == null) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.prev = null;
            newNode.next = null;
            first = newNode;
            last = newNode;
            return;
        }

        Node newNode = new Node();
        Node oldFirst = first;
        newNode.item = item;
        newNode.prev = null;
        newNode.next = oldFirst;
        oldFirst.prev = newNode;
        first = newNode;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }

        n++;
        // empty
        if (first == null) {
            Node newNode = new Node();
            newNode.item = item;
            newNode.prev = null;
            newNode.next = null;
            first = newNode;
            last = newNode;
            return;
        }

        Node newNode = new Node();
        Node oldLast = last;
        newNode.item = item;
        newNode.prev = oldLast;
        newNode.next = null;
        oldLast.next = newNode;
        last = newNode;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (first == null) {
            throw new NoSuchElementException("first is null");
        }

        n--;
        Node removeNode = first;
        if (first.next == null) {
            // last one to remove
            last = null;
            first = null;
        }
        else {
            first = removeNode.next;
            first.prev = null;
        }

        return removeNode.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (last == null) {
            throw new NoSuchElementException("last is null");
        }

        n--;
        Node removeNode = last;
        if (last.prev == null) {
            // last one to remove
            last = null;
            first = null;
        }
        else {
            last = removeNode.prev;
            last.next = null;
        }

        return removeNode.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> q = new Deque<Integer>();

        q.addFirst(1);
        q.addFirst(2);
        q.addFirst(3);
        q.addLast(4);
        q.addLast(5);
        q.addLast(6);

        // 3 2 1 4 5 6

        q.removeFirst();
        q.removeLast();
        q.removeLast();

        // 2 1 4

        // q.removeLast();
        // q.removeLast();
        // q.removeLast();


        for (int i : q) {
            StdOut.print(i);
        }
    }
}
