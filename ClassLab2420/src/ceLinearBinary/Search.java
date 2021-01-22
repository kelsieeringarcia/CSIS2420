package ceLinearBinary;

import edu.princeton.cs.algs4.Selection;

public class Search {
	/**
	* Searches the array <code>numbers</code> for the
	* specified <code>key</code> based on the linear
	* search algorithm.
	*
	* @param numbers
	* @param key the number that we are looking for
	* @return the index of the first occurrence of the key
	* in the array or -1 if the key was not found.
	*/
	public static int linear(int[] numbers, int key) {
	    for (int i = 0; i < numbers.length; i++) 
	        if (numbers[i] == key) 
	            return i; 
	    return -1;
		
	}
	
	/**
	* Searches the array <code>numbers</code> for the
	* specified <code>key</code> based on the binary
	* search algorithm.
	*
	* @param numbers
	* @param key the number that we are looking for
	* @return the index of the first occurrence of the key
	* in the array or -1 if the key was not found.
	*/
	public static int binary(int[] numbers, int key) {
		if(isSorted(numbers) == true){
			int min = 0, max =  numbers.length - 1;
			
			while(min <= max) {
				int middle = (min + max) / 2;
				
				if(numbers[middle] == key){
					return middle;
				}else if(numbers[middle] < key){
					min = middle + 1;
				}else {
					max = middle - 1;
				}
			}
			return -1;
		}
		else {
			return -2;
		}
	}
	
	public static boolean isSorted(int[] data) {
		for(int i = 1; i < data.length; i++) {
			if(data[i - 1] > data[i]) {
				return false;
			}
		}
		return true;
	}
}
