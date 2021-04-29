package autocomplete;

import java.util.Comparator;

/**
 * Word class gives more functionality to strings by allowing the string value word
 * to be accompanied by a rank value.
 * 
 * @author chad.zuniga
 */
public class Word implements Comparable<Word>{

	private String word;
	private long rank;
	private int length;
	
	/**
	 * Inits the Word class. Takes in a string word, along with the "rank" of the word/
	 * how often the word is used in common literature.
	 * 
	 * @param word	string
	 * @param rank	long
	 */
	public Word(String word, long rank) {
		this.word = word;
		this.rank = rank;
		this.length = this.getLength(word);
	}
	
	/**
	 * Compare the terms in lexicographic order but using only the first r characters of each query.
	 * 
	 * @param r
	 * @return Comparator
	 */
	public static Comparator<Word> byPrefixOrder(int r){
		if (r < 0)
            throw new IllegalArgumentException("r cannot be negative.");
		return new ByPrefixOrder(r);
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @return the rank
	 */
	public long getRank() {
		return rank;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}
	
	/**
	 * @return the length
	 */
	public int getLength(String word) {
		return word.length();
	}
	
	@Override
	public String toString() {
		return word + " " + rank;
	}

	@Override
	public int compareTo(Word arg0) {
		return this.word.compareToIgnoreCase(arg0.word);
	}
	
	private static class ByPrefixOrder implements Comparator<Word> {
		private int r;
		
		public ByPrefixOrder(int r) {
			this.r = r;
		}
		
	    public int compare(Word t1, Word t2) {
	    	int tOne = t1.getWord().length();
	    	int tTwo = t2.getWord().length();

	    	int len = tOne < tTwo ? tOne : tTwo;

	    	if(len < r) return -1;

	    	String sOne = t1.getWord().substring(0,r);
	    	String sTwo = t2.getWord().substring(0,r);
	   	
	    	if(sOne.compareToIgnoreCase(sTwo) < 0) {
	    		return -1;
	    	} else if(sOne.compareToIgnoreCase(sTwo) == 0) {
	    		return 0;
	    	} else {
	    		return 1;
	    	}
	    }
	}
}