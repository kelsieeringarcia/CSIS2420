package m01;

/** 
 * Represents a freight container that is defined by its length, 
 * width, and country of origin.
 * 
 * @author StarterCode + Kelsie Garcia
 * @param <T>
 *
 */
public class Container implements Comparable<Container>{
	private String country;  // country of origin
	private int id;          // identification number
	private int length;      // in ft
	private double weight;   // in tons
	
	/**
	 * This constructs a container.
	 * @param id
	 * @param length
	 * @param weight
	 * @param country
	 */
	public Container(int id, int length, double weight, String country ) {
		this.id = id;
		this.length = length;
		this.weight = weight;
		this.country = country;
	}
	
	/**
	 * 
	 * @return the ID of the country container
	 */
	public int getId() {
		return id;
	}	

	/**
	 * 
	 * @return the length of the country
	 */
	public int getLength() {
		return length;
	}

	/**
	 * 
	 * @return the weight of the country
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * 
	 * @return the country name
	 */
	public String getCountry() {
		return country;
	}

	@Override
	public String toString() {
		return String.format("%4d: %2dft %ft %s", id, length, weight, country);
	}

	@Override
	public int compareTo(Container o) {
		
		return this.getCountry().compareTo(o.getCountry());
	}
	
}
