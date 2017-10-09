package util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import server.Book;

public class StreamFile {

	public static void main(String[] args) throws IOException {
		FileOutputStream fout= new FileOutputStream("objects.bin");
		ObjectOutputStream out= new ObjectOutputStream( fout );
		int count=15;
		out.writeInt(count);
		for(int i=1;i<=count;i++) {
			Book book = new Book("книга "+i,"автор "+i,2017);
			out.writeObject(book);
		}
		out.close();
	}

}
