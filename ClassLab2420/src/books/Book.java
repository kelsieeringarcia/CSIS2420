package books;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
		List<Book> list = new ArrayList<>();
		String line;
		String[] attributes = null;
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			line = br.readLine();
			while(line != null) {
				attributes= line.split(",");
				String nameOfBook = attributes[0];
				String authorOfBook = attributes[1];
				String yearOfBook = attributes[2];
				Book b = new Book(nameOfBook, authorOfBook, Integer.valueOf(yearOfBook));
				System.err.println("Problem reading in \"" + attributes + "\"");
				list.add(b);
			}
		} catch (IOException e) {
			
			//e.printStackTrace();
		}
		return list;
		
	}
	
	
}
