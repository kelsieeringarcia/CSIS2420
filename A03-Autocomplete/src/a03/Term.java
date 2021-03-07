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
            throw new NullPointerException("Query cannot be null.");
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
        return new ReverseWeightOrder();    
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
		return new ByPrefixOrder(r);//TODO
	}
	
	
	/**
	 * @return the query
	 */
	public String getQuery() {
		return query;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
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
	
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term t1, Term t2) {
            if (t1.weight < t2.weight) 
                return 1;
            else if (t1.weight > t2.weight) 
                return -1;
            else 
                return 0;            
        }
    }
    
    private static class ByPrefixOrder implements Comparator<Term> {
    	
    	private int r;
    	
    	public ByPrefixOrder(int r) {
    		this.r = r;
    	}
    	
        public int compare(Term t1, Term t2) {
        	int tOne = t1.query.length();
        	int tTwo = t2.query.length();

        	int len = tOne < tTwo ? tOne : tTwo;

        	if(len < r) return -1;

        	String sOne = t1.query.substring(0,r);
        	String sTwo = t2.query.substring(0,r);
       	
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
