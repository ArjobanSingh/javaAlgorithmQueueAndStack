/* *****************************************************************************
 *  Name: Arjoban Singh
 *  Date: jan 6, 2020
 *  Description: RandomizedQueue implemented
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {


    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        items = (Item[]) new Object[1];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {

        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return size;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(2 * items.length);
        items[size] = item;
        size++;
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i =0; i < size; i++)
        {
            copy[i] = items[i];
        }
        items = copy;
    }
    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty()) throw new NoSuchElementException();
        if (size > 0 && size == items.length/4) resize(items.length/2);
        int randomIndex = StdRandom.uniform(size);
        Item thisItem = items[randomIndex];

        // this line will than set the value at the last index of array by decrementing size by 1 to this randomIndex of the array
        // than, there will be no empty or null element in middle of array as we have put the last element to this randomIndex, and the original value is poped or overriden by last value
        items[randomIndex] = items[--size];

        // thn making the last element to be null.
        items[size] = null;
        return thisItem;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        int randomIndex = StdRandom.uniform(size);
        return items[randomIndex];
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.isEmpty());
        randomizedQueue.enqueue("hello");
        randomizedQueue.enqueue("world");
        randomizedQueue.enqueue("again");

        System.out.println(randomizedQueue.size());
        System.out.println(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());

        for (String s: randomizedQueue)
        {
            System.out.println(s);
        }
        System.out.println("New iteration");

        for (String s: randomizedQueue)
        {
            System.out.println(s);
        }

        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.isEmpty());
        System.out.println(randomizedQueue.size());
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private int tempSize;
        private int[] indexArray;
        public ListIterator()
        {
            tempSize = size;
            indexArray = new int[size];
            for (int i = 0; i < size; i++)
            {
                indexArray[i] = i;
            }
            // this will shuffle the indexArray to random order, whenever new ListIterator is called in iterator method
            StdRandom.shuffle(indexArray);
        }
        @Override
        public boolean hasNext() {
            return tempSize > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            // decrementing tempSize, than getting element from indexArray at tempSize and than using that element as index to get item in items array
            Item item = items[indexArray[--tempSize]];
            return  item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
