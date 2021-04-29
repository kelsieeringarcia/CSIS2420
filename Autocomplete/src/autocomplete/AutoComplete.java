package autocomplete;

import java.util.Arrays;
import java.util.Comparator;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.MaxPQ;
import edu.princeton.cs.algs4.Out;


/**
 * AutoComplete takes in a list of words from a file, searches for a particular word given
 * as input by the user, and generates a Max Priority Queue based on the "rank" of the word/
 * how often the word is used in books. The user can also add words to the list if it doesn't
 * exist, and will resave the file with the updated word.
 * 
 * @author chad.zuniga
 */
public class AutoComplete {	
	private Word[] words;
	private String userWord;
	private int fileSize;
	
	private class Node implements Comparable<Node> {
		Word w;
		long priority;

		public Node(Word w) {
			this.w = w;
			this.priority = w.getRank();
		}
		
		@Override
		public int compareTo(Node o) {
			if(this.w.getWord().contains(userWord)) {
				return 1;
			}else if(this.priority > o.priority) {
				return 1;
			}else if(this.priority < o.priority){
				return -1;
			}else {
				return 0;
			}
		}
	}
	
	/**
	 * Inits AutoComplete ctor and gets the words from a file and creates a 
	 * global array of Words
	 */
	public AutoComplete() {
		this.words = getWordsFromFile();
	}
	
	/**
	 * This method takes a given word, performs a binary search to find the index
	 * of the first word that starts with the given word, then returns a MaxPQ
	 * of the top results.
	 * 
	 * @param userWord		string
	 * @param numOfResults	int
	 * @return
	 */
	public String[] autoCompleteFromWord(String userWord, int numOfResults) {
		if(userWord.isEmpty() || userWord == null) throw new IllegalArgumentException("Word can not be null or empty");
		if(numOfResults < 1) throw new IllegalArgumentException("Number of Results can not be less than 1");
		this.userWord = userWord;
		MaxPQ<Node> pq = new MaxPQ<>();
		String[] result = new String[0];
		
		Word uw = new Word(userWord, 0);
		int index = firstIndexOf(words, uw, Word.byPrefixOrder(userWord.length()));
		if(index < 0) {
			return result;
		}
		
		try {
		
			for(int i = index; i < index+numOfResults; i++) {
				
				if(i < fileSize) {
					pq.insert(new Node(words[i]));
				} else {
					break;
				}
			}
			
			//pq.forEach(x -> System.out.println(x.w.getWord()));
		} catch(Exception e) {
			System.out.println("does not exist in word list");
		}

		result = new String[numOfResults];
		for(int i = 0; i < numOfResults; i++) {
			result[i] = pq.delMax().w.getWord();
		}
		return result;
	}
	
	/**
	 * This method takes a users word and adds it to the global array of Words
	 * 
	 * @param userWord	string
	 */
	public void insertIntoWordList(String userWord) {
		if(userWord.isEmpty() || userWord == null) throw new IllegalArgumentException("Word can not be null or empty");
		Word[] temp = new Word[fileSize + 1];
		
		for(int i = 0; i < fileSize; i++) {
			temp[i] = words[i];
		}
		
		temp[fileSize] = new Word(userWord, 0);
		words = temp;
		
		Insertion.sort(words);
		
		fileSize++;
		System.out.println("added userword:" + userWord);
	}
	
	public void refreshWordFileOnDestroy() {
		try {
			String fileName = "src/autocomplete/WordList2.txt";
			Out out = new Out(fileName);
			
			out.println(fileSize);
			for(Word el : words) {
				out.printf("%s	%s%n",el.getWord(),el.getRank());
			}
			
			out.close();
		} catch(Exception e) {
			System.out.println(e);
			System.out.println("could not write to file");
		}
	}
	
	private Word[] getWordsFromFile() {
		Word[] wordsFromFile = new Word[0];
		try {
			String fileName = "src/autocomplete/WordList2.txt";
		    In in = new In(fileName);
		    int count = 0;
		    
		    fileSize = Integer.parseInt(in.readLine());
		    wordsFromFile = new Word[fileSize];
		    while(in.hasNextLine()) {
		    	String temp = in.readLine();
		    	if(temp == null) break;
		    	String[] arr = temp.split("	");
		    	Word w = new Word(arr[0], Long.parseLong(arr[1]));
		    	
		    	wordsFromFile[count] = w;
		    	count++;
		    }
		    
		    Arrays.sort(wordsFromFile);
		} catch(Exception e) {
			System.out.println(e);
		}
		
	    return wordsFromFile;
	}
	

	
	private static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
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

	public static void main(String[] args) {
//		AutoComplete ac = new AutoComplete();
//		ac.insertIntoWordList("AAAAAKELSIE");
//		ac.refreshWordFileOnDestroy();
//		@SuppressWarnings("resource")
//		Scanner input = new Scanner(System.in);
//		System.out.print("Input word: ");
//		System.out.println();
//		
//		String userWord = input.nextLine();
//	
//		ac.autoCompleteFromWord(userWord, 10);
//		
//		ac.insertIntoWordList(userWord);
//		
//		System.out.println("\n");
//		
//		System.out.print("Input word: ");
//		System.out.println();
//		
//		String userWord1 = input.nextLine();
//	
//		ac.autoCompleteFromWord(userWord1, 10);	
	}
}