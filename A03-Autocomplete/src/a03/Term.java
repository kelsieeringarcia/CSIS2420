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
        	len = len < r ? len : r;
        	
        	String sOne = t1.query.substring(0,len);
        	String sTwo = t2.query.substring(0,len);
       	
        	if(sOne.compareToIgnoreCase(sTwo) < 0) {
        		return -1;
        	} else if(sOne.compareToIgnoreCase(sTwo) == 0) {
        		return 0;
        	} else {
        		return 1;
        	}
        }
    }
    
//    public static void main(String[] args) {
//    	Term t1 = new Term("Apple", 1.0);
//    	Term t2 = new Term("Appmes", 2.0);
//    	Term t3 = new Term("Banana", 3.0);
//    	
//    	Term[] terms = {t2,t1,t3};
//    	for(Term el : terms) {
//    		System.out.print(el + " || ");
//    	}
//    	System.out.println();
//    	System.out.println();
//    	Collections.sort(Arrays.asList(terms), Term.byPrefixOrder(4));
//    	for(Term el : terms) {
//    		System.out.print(el + " || ");
//    	}
//    	
//    }

}
