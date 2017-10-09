package rmiInterface;

import java.rmi.Remote;
import java.util.List;

import server.Book;

public interface MyInterface extends Remote{
	public int getCount() throws Exception; 
	public List<Book> getBooks() throws Exception; 
	public boolean addBook(Book p,String client) throws Exception;
}
