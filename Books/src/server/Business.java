package server;

import java.util.ArrayList;
import java.util.List;

import rmiInterface.MyInterface;

public class Business implements MyInterface{
	private List<Book> list = new ArrayList<Book>();
	
	@Override
	public List<Book> getBooks() throws Exception { 
	      return list; 
	      
	}

	@Override
	public boolean addBook(Book p,String client)  throws Exception {
		list.add(p);
		System.out.println(client + " дари книга.");
		return true;
	}

	@Override
	public int getCount() throws Exception {
		return list.size();
	}

	synchronized public boolean init(Book p) {
		list.add(p);;
		return true;
	}
}
