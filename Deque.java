/* *****************************************************************************
 *  Name: Arjoban Singh
 *  Date: jan 6, 2020
 *  Description: Deque implemented
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    private class Node
    {
        Item item;
        Node next;
        Node previous;
    }

    // construct an empty deque
    public Deque()
    {
        // setting first and last intially to null and size to 0
        first = null;
        last = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return size == 0;
    }

    // return the number of items on the deque
    public int size()
    {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null) throw new IllegalArgumentException();

        // temprary node called oldFirst for storing First
        Node oldFirst = first;
        size++;

        // making new node and setting it to first and than setting its item and its previous to null
        first = new Node();
        first.item = item;
        first.previous = null;

        // if size is 1 than, last is also the first
        if (size() == 1)
        {
            first.next = null;
            last = first;
        }
        // else setting previous and next to required
        else if (size() > 1)
        {
            oldFirst.previous = first;
            first.next = oldFirst;
        }

    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null) throw new IllegalArgumentException();

        // temprary node called oldLast for storing last
        Node oldLast = last;
        size++;

        // setting new node to last and its properties or fields
        last = new Node();
        last.item = item;
        last.next = null;

        // if size is 1 meaning if first is null or size was 0 before adding this new node
        if (size() == 1)
        {
            // setiing previous of new last to null and first to last, as there are no more elements
            last.previous = null;
            first = last;
        }
        // else if size is greater than 1(it can't be zero we have invremented size in this method) than set required fields
        else
            {
                last.previous = oldLast;
                oldLast.next = last;
            }
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        Item item = first.item;
        size--;

        // if no elements or empty than setting first and last to null
        if (isEmpty())
        {
            first = null;
            last = null;
        }
        // if size is 1, than setting first to be the element at first.next and its previous to be null and last is same as first
        else if (size() == 1)
        {
             first = first.next;
             first.previous = null;
             last = first;
        }
        else
        {
            first = first.next;
            first.previous = null;
        }

        return item;
    }


    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }
        Item item = last.item;
        size--;

        // if no elements or empty than setting first and last to null
        if (isEmpty())
        {
            first = null;
            last = null;
        }
        // if size is 1, than setting last to be the element at last.previous and its next to be null and first is same as last
        else if (size() == 1)
        {
            last = last.previous;
            last.next = null;
            first = last;
        }
        else
        {
            last = last.previous;
            last.next = null;
        }

        return item;

    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()
    {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>
    {
        private Node current = first;
        public boolean hasNext() { return current != null;  }
        public Item next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
        public void remove()
        {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<String> myStack = new Deque<>();
        System.out.println(myStack.isEmpty());
        System.out.println(myStack.size());
        myStack.addFirst("world");
        myStack.addFirst("hello");
        myStack.addLast("from");
        myStack.addLast("Arjoban");
        for(String s: myStack)
        {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println(myStack.isEmpty());
        System.out.println(myStack.size());
        System.out.println(myStack.removeFirst());
        System.out.println(myStack.removeLast());
    }
}
