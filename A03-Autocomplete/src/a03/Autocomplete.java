package a03;

import java.util.Arrays;

/**
 * This class is used for taking the terms given and providing all the matches found using the prefix
 * as well returning how many matches come up in the array given the prefix.
 * 
 * @author Kelsie Garcia and Chad Zuniga
 *
 */
public class Autocomplete {
	
	private Term[] terms;

	/**
	 *  Initialize the data structure from the given array of terms.
	 * @param terms
	 */
    public Autocomplete(Term[] terms) {
    	if (terms == null) 
    		throw new NullPointerException("Terms array cannot be null");
    	this.terms = terms;
    	Arrays.sort(terms);
    }
    
    /**
     *  Return all terms that start with the given prefix, in descending order of weight.
     * @param prefix
     * @return an array of terms that match the prefix
     */
    public Term[] allMatches(String prefix) {
    	Term[] arr;
    	Term t = new Term(prefix, 0.0);
    	int first = BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
    	int last = BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
    	if(first == -1 || last == -1) return new Term[0];
    	arr = Arrays.copyOfRange(terms, first, last + 1);
    	Arrays.sort(arr, Term.byReverseWeightOrder());
    	return arr;
    }
    
    /**
     * Return the number of terms that start with the given prefix.
     * @param prefix
     * @return number of matches found with the prefix
     */
    public int numberOfMatches(String prefix) {
    	Term t = new Term(prefix, 0.0);
    	int first = BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
    	int last = BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
    	if(first == -1 || last == -1) return 0;
    	return (last - first) + 1;
    }
    
}
