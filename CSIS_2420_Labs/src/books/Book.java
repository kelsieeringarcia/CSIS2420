package books;

import java.io.File;
import java.util.List;

public class Book {

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
	
	public static List<Book> getList(String file){
		return null;//TODO
		
	}
	
	
}
