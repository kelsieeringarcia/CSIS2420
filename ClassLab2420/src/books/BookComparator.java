package books;

import java.util.Comparator;

public class BookComparator implements Comparator<Book>{

	@Override
	public int compare(Book o1, Book o2) {
		String b1 = o1.getTitle();
		String b2 = o2.getTitle();
		return b1.compareTo(b2);
	}

	
}
