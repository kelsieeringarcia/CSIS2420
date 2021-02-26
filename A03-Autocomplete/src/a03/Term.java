package a03;

import java.util.Comparator;

/**
 * This class represents the auto complete term that is being searched.
 * 
 * @author Kelsie Garcia And Chad Zuniga
 *
 */
public class Term implements Comparable<Term>{
	
	private String query;
	private double weight;
	
	/**
	 * Initialize a term with the given query string and weight.
	 * 
	 * @param query
	 * @param weight
	 */
	public Term(String query, double weight) {
        if (query == null)
            throw new IllegalArgumentException("Query cannot be null.");
        if (weight < 0)
            throw new IllegalArgumentException("Weight cannot be negative.");
        this.query = query;
        this.weight = weight;
	}
	
	/**
	 * Compare the terms in descending order by weight.
	 * 
	 * @return Comparator
	 */ 
	public static Comparator<Term> byReverseWeightOrder(){
		return null;//TODO	
	}
	
	/**
	 * Compare the terms in lexicographic order but using only the first r characters of each query.
	 * 
	 * @param r
	 * @return Comparator
	 */
	public static Comparator<Term> byPrefixOrder(int r){
		if (r < 0)
            throw new IllegalArgumentException("r cannot be negative.");
		return null;//TODO
	}
	
	
	// Compare the terms in lexicographic order by query.
	@Override
	public int compareTo(Term that) {
		return query.compareTo(that.query);
	}
	
	// Return a string representation of the term in the following format:
    // the weight, followed by a tab, followed by the query.
	@Override
	public String toString() {
		return Double.toString(weight) + "\t" + query;
	}

}
