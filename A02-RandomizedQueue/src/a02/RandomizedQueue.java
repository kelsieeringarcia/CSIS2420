package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * This class is for a randomized queue that is a queue that can be altered at random.
 *  
 * @author Kelsie Garcia and Chad Zuniga
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
	
	private static final int INIT_CAPACITY = 8;
	
	private Item[] a;
	private int n;
	
	/**
	 * Constructs a randomized queue.
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		a = (Item[]) new Object[INIT_CAPACITY];
		n = 0;
	}
	
	/**
	 * This method checks to see if the queue is empty.
	 * @return
	 */
	public boolean isEmpty() {
		return n == 0;
	}
	
	/**
	 * 
	 * @return the size of the queue
	 */
	public int size() {
		return n;
	}
	
	/**
	 * This method will insert a new item into the queue randomly.
	 * @param item
	 */
	public void enqueue(Item item) {
		if(item == null) throw new NullPointerException();
		
		if(n == a.length) resize(2*a.length);
		a[n++] = item;
	}
	
	/**
	 * This method will remove a item from the queue at random.
	 * @return
	 */
	public Item dequeue() {
		if(this.isEmpty()) 
			throw new NoSuchElementException("Cannot remove anything from an empty RandomizedQueue.");
		
		int removedIndex = StdRandom.uniform(n);
		Item removedItem = a[removedIndex];
		
		a[removedIndex] = a[n-1];
		a[n-1] = null;
		n--;
		
		if (n > 0 && n == a.length/4) resize(a.length/2);
		
		return removedItem;
	}
	
	/**
	 * This method will peek at an item within the queue at random with out removing it.
	 * @return
	 */
	public Item sample() {
		if(this.isEmpty()) 
			throw new NoSuchElementException("Cannot sample anything from an empty RandomizedQueue.");
		return a[StdRandom.uniform(n)];
		
	}
	

	public static void main(String[] args) {
		RandomizedQueue<String> rq = new RandomizedQueue<>();
		rq.enqueue("test1");
		rq.enqueue("test2");
		rq.enqueue("test3");
		rq.enqueue("test4");
		rq.enqueue("test5");
		rq.enqueue("test6");
		rq.enqueue("test7");
		System.out.println("n:"+ rq.n);
		System.out.println("Removed item: " + rq.dequeue());
		System.out.println("Removed item: " + rq.dequeue());
		System.out.println("Removed item: " + rq.dequeue());
		System.out.println("Removed item: " + rq.dequeue());
		System.out.println("Removed item: " + rq.dequeue());
		System.out.println("Removed item: " + rq.dequeue());
		System.out.println("Removed item: " + rq.dequeue());
		//System.out.println("Removed item: " + rq.dequeue());
		
		for(String el : rq) {
			System.out.println(el);
		}

	}

	@Override
	public Iterator<Item> iterator() {
		return new ArrayIterator();
	}

	/**
	 * This class helps iterate through the random queue.
	 * 
	 * @author Kelsie Garcia and Chad Zuniga
	 *
	 */
	public class ArrayIterator implements Iterator<Item> {
        Item[] items;
        int current;
        
        public ArrayIterator() {
            current = 0;
            items = (Item[]) new Object[n];
            
            for(int i = 0; i < n; i++) {
                items[i] = a[i];
            }
            
            StdRandom.shuffle(items);
        }
        
        @Override
        public boolean hasNext() {
            return current < n;
        }

        @Override
        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();
            
            return items[current++];
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
	
    // resize the underlying array holding the elements
    private void resize(int capacity) {
        assert capacity >= n;

        // textbook implementation
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = a[i];
        }
        a = copy;
    }

}
