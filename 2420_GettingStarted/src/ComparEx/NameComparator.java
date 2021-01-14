package ComparEx;

import java.util.Comparator;

public class NameComparator implements Comparator<Student>{

	@Override
	public int compare(Student s1, Student s2) {
		String name1 = s1.getName();
		String name2 = s2.getName();
		//ascending order(descending order would be: name2.compareTo(name1))
		return name1.compareTo(name2);
	}

}
