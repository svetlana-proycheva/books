package server;

import java.io.Serializable;

public class Book implements Serializable{

	private static final long serialVersionUID = 4419216607947896190L;
	String name;
	String author;
	int year;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Book(String name, String author, int year) {
		this.name = name;
		this.author = author;
		this.year = year;
	}
}
