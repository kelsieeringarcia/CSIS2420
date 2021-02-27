package a03;

import java.util.Arrays;
import java.util.Comparator;
/**
 * This class uses binary search in order to find the first or last occurrence of a key within 
 * an array
 * 
 * @author Kelsie Garcia AND Chad Zuniga
 *
 */
public class BinarySearchDeluxe {

	/**
	 * Return the index of the first key in a[] that equals the search key, or -1 if no such key.
	 * 
	 * @param <Key>
	 * @param a
	 * @param key
	 * @param comparator
	 * @return index of the first key
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a.length == 0 || a == null) throw new NullPointerException("Array can not be null");
		if(key == null) throw new NullPointerException("Key can not be null");
		if(comparator == null) throw new NullPointerException("Key can not be null");

		int index = Arrays.binarySearch(a, key, comparator);
		
		while(index >= 0 && a[index] == key) {
			if(index <= 0) return 0;
			index -= 1;
			if(a[index] != key) {
				return ++index;
			}
		}
		
		return index >= 0 ? index : -1;			
	}
	
	/**
	 *  Return the index of the last key in a[] that equals the search key, or -1 if no such key.
	 * @param <Key>
	 * @param a
	 * @param key
	 * @param comparator
	 * @return index of the last key
	 */
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a.length == 0 || a == null) throw new NullPointerException("Array can not be null");
		if(key == null) throw new NullPointerException("Key can not be null");
		if(comparator == null) throw new NullPointerException("Key can not be null");
		
		int index = Arrays.binarySearch(a, key, comparator);
		
		while(index >= 0 && a[index] == key) {
			if(index >= a.length-1) return a.length-1;
			index += 1;
			if(a[index] != key) {
				return --index;
			}
		}
		
		return index >= 0 ? index : -1;	
	}
	
	
//	public static void main(String[] args) {
//		Integer[] numbers = {1,1,42,100,42,100,1,100,1,100,100,100,1,1,100,1,100,42,42,100,42,42,1,1,1,42};
//		
//		Arrays.sort(numbers);
//		
//		for(Integer el : numbers) {
//			System.out.print(el + " ");
//		}
//		System.out.println();
//		System.out.println();
//		
//		System.out.println(BinarySearchDeluxe.firstIndexOf(numbers, 1, Comparator.naturalOrder()));
//		
//		for(Integer el : numbers) {
//			System.out.print(el + " ");
//		}
//		
//	}
}