package books;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.princeton.cs.algs4.Selection;

public class BookApp {

	public static void main(String[] args) {
		List<Book> books = Book.getList("/Users/kelsiegarcia/Documents/gitHub/CSIS-2420/ClassLab2420/src/books/books.csv");
		System.out.println("Number of books read in: " + books.size());
		System.out.println();
		System.out.println();
		
		System.out.println("Sorted  Books:");
		Book naturalSortedBooks[] = new Book[books.size()];
		for(int i = 0; i < books.size(); i++) {
			naturalSortedBooks[i] = books.get(i);
		}
		Selection.sort(naturalSortedBooks, new BookComparator());
		for(Book el : naturalSortedBooks) {
			System.out.println(el);
		}
		System.out.println();
		System.out.println();
		
		System.out.println("Books Sorted in Reverse Order:");
		List<Book> reverseSortedBooks = new ArrayList<>(); 
		for(int i = 0; i < books.size(); i++) {
			reverseSortedBooks.add(i, naturalSortedBooks[i]);
		}
		Collections.reverse(reverseSortedBooks);
		for(Book el : reverseSortedBooks) {
			System.out.println(el);
		}

	}

}
