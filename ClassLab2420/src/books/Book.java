package books;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class Book implements Comparable<Book> {

	private String title;
	private String author;
	private int year;

	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return title + "by" + author + "(" + year + ")";
	}

	public static List<Book> getList(String file) {
		List<Book> list = new ArrayList<>();
		String line;
		String[] attributes;

		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			Scanner s = new Scanner(br);
			while (s.hasNext()) {
				line = s.nextLine();
				attributes = line.split(",");
				if (attributes.length == 3) {
					String nameOfBook = attributes[0];
					String authorOfBook = attributes[1];
					String yearOfBook = attributes[2];
					if (isNumber(yearOfBook)) {
						Book b = new Book(nameOfBook, authorOfBook, Integer.valueOf(yearOfBook));
						list.add(b);
					}
				}
				else {
					System.err.println("Problem reading in \"" + line + "\"");
				}
			}
		} catch (IOException e) {
			System.err.print(e);
		}
		return list;

	}

	private static boolean isNumber(String s) {
		for (int i = 0; i < s.length(); i++)
			if (Character.isDigit(s.charAt(i)) == false)
				return false;

		return true;
	}

	@Override
	public int compareTo(Book o) {
		return this.getTitle().compareTo(o.getTitle());
	}

}
