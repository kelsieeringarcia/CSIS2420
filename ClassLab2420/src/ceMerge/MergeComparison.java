package ceMerge;

import java.text.DecimalFormat;

import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdRandom;

public class MergeComparison {

	
	public static void main(String[] args) {
		Integer[] intArray;
		int count = 0;
		int size = 1024;
		DecimalFormat df = new DecimalFormat("#.###"); 
		

		
		System.out.println("       n     Merge");
		System.out.println("---------- ------------");
		for(int i = 0; i < 9; i++) {
			intArray = getNumbers(size);
			long sTime = System.nanoTime();
			Merge.sort(intArray);
			long estTime = System.nanoTime() - sTime;
			String formatted = df.format(estTime/1000000000.0);
			System.out.println(size + "\t\t" + formatted + "s");
			size = size * 2;
		}
		System.out.println();
		System.out.println();
		
		Integer[] intArray2;
		size = 1024;
		System.out.println("       n     Merge");
		System.out.println("---------- ------------");
		for(int i = 0; i < 9; i++) {
			intArray2 = getNumbers(size);
			long sTime = System.nanoTime();
			MergeSlow.sort(intArray2);
			long estTime = System.nanoTime() - sTime;
			String formatted = df.format(estTime/1000000000.0);
			System.out.println(size + "\t\t" + formatted + "s");
			size = size * 2;
		}
	}
	
	public static Integer[] getNumbers(int size) {
		Integer[] result = new Integer[size];
		
		for(int i = 0; i <size; i++) {
			result[i] = StdRandom.uniform(100000, 999999);
		}
		return result;
	}
}
