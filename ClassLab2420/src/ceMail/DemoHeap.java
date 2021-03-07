package ceMail;

import edu.princeton.cs.algs4.MaxPQ;

public class DemoHeap {

	public static void main(String[] args) {
		Mail[] arr = new Mail[25];
		for(int i = 0;i < 25;i++) {
			arr[i] = new Mail();
		}
		
		MaxPQ<Mail> pq = new MaxPQ<>();
		
		System.out.println("25 random mail objects:");
		System.out.println("-----------------------");
		for(Mail el : arr) {
			System.out.println(el);
			pq.insert(el);
		}
		System.out.println();
		
		
		System.out.println("Mail delivery:");
		System.out.println("--------------");
		for(Mail el : pq) {
			System.out.println(pq.delMax());
		}
		
		
	}

}
