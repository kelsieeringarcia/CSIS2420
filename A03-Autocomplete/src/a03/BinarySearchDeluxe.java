package a03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class is used for finding first/last index in an array of a given key using a given comparator
 * 
 * @author Kelsie Garcia and Chad Zuniga
 *
 */
public class BinarySearchDeluxe {

	/**
	 *  Return the index of the first key in a[] that equals the search key, or -1 if no such key.
	 * @param <Key>
	 * @param a
	 * @param key
	 * @param comparator
	 * @return first index of the key
	 */
	public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a.length == 0 || a == null) throw new NullPointerException("Array can not be null");
		if(key == null) throw new NullPointerException("Key can not be null");
		if(comparator == null) throw new NullPointerException("Key can not be null");

		int index = Arrays.binarySearch(a, key, comparator);
		
		while(index >= 0 && comparator.compare(a[index],key) == 0) {
			if(index == 0) return 0;
			index -= 1;
		}
		
		return index >= 0 ? index += 1 : -1;
	}
	
	/**
	 *  Return the index of the last key in a[] that equals the search key, or -1 if no such key.
	 * @param <Key>
	 * @param a
	 * @param key
	 * @param comparator
	 * @return last index of key
	 */
	public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
		if(a.length == 0 || a == null) throw new NullPointerException("Array can not be null");
		if(key == null) throw new NullPointerException("Key can not be null");
		if(comparator == null) throw new NullPointerException("Key can not be null");
		
		int index = Arrays.binarySearch(a, key, comparator);
		
		while(index >= 0 && comparator.compare(a[index], key) == 0) {
			if(index >= a.length-1) return a.length-1;
			index += 1;
		}
		
		return index >= 0 ? index -= 1 : -1;	
	}
	
}