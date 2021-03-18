package cePet;

import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.StdRandom;

public class PetApp {
	static BinarySearchST<Integer, Pet> st = new BinarySearchST<>();
	
	public static void main(String[] args) {
		st.put(randomKey(), new Pet("Buddy", 8, "dog"));
		st.put(randomKey(), new Pet("Tom", 4, "cat"));
		st.put(randomKey(), new Pet("Jack", 50, "tortoise"));
		st.put(randomKey(), new Pet("Olivar", 11, "cat"));
		st.put(randomKey(), new Pet("Freyja", 4, "dog"));
		st.put(randomKey(), new Pet("Athena", 1, "fish"));
		st.put(randomKey(), new Pet("Fang", 10, "dog"));
		st.put(randomKey(), new Pet("Kiwi", 2, "bird"));
		st.put(randomKey(), new Pet("Buddah", 4, "lizard"));
		st.put(randomKey(), new Pet("Jake", 25, "tortoise"));
		//print the ten keys in a single line.
		System.out.print("All Keys: ");
		for(Integer key : st.keys()) {
			System.out.print(key + " ");
		}
		System.out.println();
		//Print all the names of the pets in the symbol table in a single line.
		System.out.print("All pet names: ");
		for(Integer key : st.keys()) {
			System.out.print(st.get(key).getName() + " ");
		}
		System.out.println();
		
		System.out.println();
		//Check whether 10 is a key in the symbol table.
		keyContains(st, 10);
		//Check whether 17 is a key in the symbol table.
		keyContains(st, 17);
		//Check whether 23 is a key in the symbol table.
		keyContains(st, 23);
		
		System.out.println();
		//Find the smallest pet ID 
		System.out.println("Smallest pet ID: " + st.min());
		//Find the smallest pet ID that is greater than 17
		System.out.println("Smallest pet ID greater or equal to 17: " + st.ceiling(17));
		//Find the number of pets that are in the symbol table.
		System.out.println("Number of pets: " + st.size());
		//Add a 3-year-old dog named Waldi with pet ID 30 to the symbol table.
		st.put(30, new Pet("Waldi", 3, "dog"));
		//Find the number of pet IDs that are smaller than 25.
		System.out.println("Pet IDs smaller 25: " + st.size(0, 24));
		//Print the pet with the pet ID 30.
		System.out.println("Pet with ID 30: " + st.get(30));
		//Find the second smallest pet ID and print the corresponding pet.
		System.out.println("Second smallest pet ID: " + st.select(1));
		System.out.println("IDs  Pets");
		System.out.println("---  -------------");
		for(Integer key : st.keys()) {
			System.out.println(String.format("%s   %s", key, st.get(key)));
		}
		
	}


	/**
	 * This checks if the key exist with in the symbol table
	 * @param st
	 * @param key
	 */
	private static void keyContains(BinarySearchST<Integer, Pet> st, int key) {
		if(st.contains(key)) {
			System.out.println(st.get(key));
		}else {
			System.out.println("The key ("+ key +") was not found in the symbol table.");
		}
	}
	
	/**
	 * Generates random key
	 * @return random key number
	 */
	private static int randomKey() {
		int random = StdRandom.uniform(10,30);
		if(st.contains(random)) {
			return randomKey();
		}
		return random;
	}

}
