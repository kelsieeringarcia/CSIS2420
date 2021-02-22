package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * A double-ended queue or deque. Items can be added or removed on either end.
 * 
 * @author Kelsie Garcia and Chad Zuniga
 *
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

	private Node<Item> head;
	private Node<Item> tail;
	private int n;

	private class Node<Item> {
		private Item item;
		private Node<Item> next;
		private Node<Item> prev;

	}

	/**
	 * This constructs and empty deque.
	 */
	public Deque() {
		this.head = null;
		this.tail = null;
		n = 0;
	}

	/**
	 * This method checks if the current deque is empty.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * This method checks the size of the deque.
	 * 
	 * @return size
	 */
	public int size() {
		return n;
	}

	/**
	 * This method adds a item at the beginning of the deque and reassign the head
	 * to the added item.
	 * 
	 * @param item
	 */
	public void addFirst(Item item) {
		if (item == null)
			throw new NullPointerException();

		Node<Item> newHead = new Node<Item>();
		Node<Item> oldHead = head;
		newHead.item = item;

		head = newHead;
		head.prev = null;

		if (this.isEmpty()) {
			head.next = null;
			tail = head;
		} else {
			head.next = oldHead;
			oldHead.prev = head;
		}

		n++;
	}

	/**
	 * This method adds an item at the end of the deque and reassign the tail to the
	 * added item.
	 * 
	 * @param item
	 */
	public void addLast(Item item) {
		if (item == null)
			throw new NullPointerException();

		Node<Item> newTail = new Node<Item>();
		Node<Item> oldTail = tail;
		newTail.item = item;

		tail = newTail;
		tail.next = null;

		if (this.isEmpty()) {
			tail.prev = null;
			head = tail;
		} else {
			tail.prev = oldTail;
			oldTail.next = tail;
		}

		n++;
	}

	/**
	 * This method removes the first item of the deque and reassign the head to the
	 * next item
	 * 
	 * @return the removed item
	 */
	public Item removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot remove anything from an empty deque.");
		}
		Node<Item> newNode = new Node<Item>();
		if(n == 1) {
			newNode = head;
			head = head.next;
			tail = head;
			n--;
			return newNode.item;
		}
		
		newNode = head;
		head = head.next;
		head.prev = null;
		n--;
		return newNode.item;
	}

	/**
	 * This method removes the last item of the deque and reassign the tail to the
	 * previous item
	 * 
	 * @return the removed item
	 */
	public Item removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException("Cannot remove anything from an empty deque.");
		}
		Node<Item> newNode = new Node<Item>();
		if(n == 1) {
			newNode = tail;
			tail = tail.prev;
			head = tail;
			n--;
			return newNode.item;
		}
		newNode = tail;
		tail = tail.prev;
		tail.next = null;
		n--;
		return newNode.item;
	}

	/**
	 * TESTING CLIENT 
	 */
	public static void main(String[] args) {
		Deque<Integer> q = new Deque<>();
		q.addLast(0);
		
		q.removeLast();
		System.out.println(q.isEmpty());
//		q.addFirst(1);
//		q.addFirst(2);
//		q.addLast(1);
//		q.removeFirst();
//		q.addLast(2);
//		q.addFirst(3);
//		q.addLast(4);
//		q.addLast(5);
//		q.removeLast();
		System.out.println("Size: " + q.size());
		for (Integer el : q) {
			System.out.println(el);
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	/**
	 * This class helps iterate through the deque.
	 * 
	 * @author Kelsie Garcia and Chad Zuniga
	 *
	 */
	public class ListIterator implements Iterator<Item> {
		Node<Item> current = head;

		@Override
		public boolean hasNext() {
			return current != null;
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
