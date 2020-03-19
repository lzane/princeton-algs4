import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int n = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.arr = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return n == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] newArr = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
        arr[n++] = item;
        if (n == arr.length) {
            resize(n * 2);
        }
    }

    private void exchange(int i, int j) {
        Item temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // remove and return a random item
    public Item dequeue() {
        if (n == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        int pick = StdRandom.uniform(n);
        Item pickItem = arr[pick];
        arr[pick] = null;
        n--;
        exchange(pick, n);

        if (n > 0 && n <= arr.length / 4) {
            resize(n * 2);
        }
        return pickItem;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (n == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        int pick = StdRandom.uniform(n);
        Item pickItem = arr[pick];
        return pickItem;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private final Item[] iteratorArr;
        private int current = 0;

        public RandomizedQueueIterator() {
            RandomizedQueue<Item> randomizedQueue = new RandomizedQueue<Item>();
            iteratorArr = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                randomizedQueue.enqueue(arr[i]);
            }

            for (int i = 0; i < n; i++) {
                Item temp = randomizedQueue.dequeue();
                iteratorArr[i] = temp;
            }
        }

        public boolean hasNext() {
            return current < n;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item pick = iteratorArr[current++];
            return pick;
        }
    }


    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        queue.enqueue(5);
        queue.enqueue(6);

        for (int i : queue) {
            StdOut.print(i);
        }
    }

}
