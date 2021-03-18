package cePet;

public final class Pet {
	private String name;
	private int age;
	private String species;
	
	public Pet(String name, int age, String species) {
		super();
		this.name = name;
		this.age = age;
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getSpecies() {
		return species;
	}

	@Override
	public String toString() {
		return String.format("%s %s (%s)", species, name, age);
	}

}
