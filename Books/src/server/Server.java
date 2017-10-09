package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import rmiInterface.MyInterface;

public class Server extends Business{

	public static void main(String[] args) {
		try { 
			Business obj = new Business(); 
 
	        MyInterface skeleton = (MyInterface) UnicastRemoteObject.exportObject(obj, 0);  

	         Registry registry = LocateRegistry.getRegistry(); 
	         
	         registry.rebind("Business", skeleton);
	         

	         FileInputStream fin;
				try {
					fin = new FileInputStream("../objects.bin");
					ObjectInputStream in= new ObjectInputStream( fin );
					int count =in.readInt();
					for(int i=1;i<=count;i++) {
						Book book =(Book)in.readObject(); 
						obj.init(book);
					}
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

	         System.err.println("Server ready"); 
	         
	         Timer timer = new Timer();
	 		TimerTask tick =new TimerTask() {

	 			@Override
	 			public void run() {
	 				FileOutputStream fout;
					try {
						fout = new FileOutputStream("../objects.bin");
					
	 				ObjectOutputStream out= new ObjectOutputStream( fout );
	 				List<Book> list = obj.getBooks();
	 				int count=obj.getCount();
	 				out.writeInt(count);
	 				for(int i=1;i<=count;i++) {
	 					Book book = list.get(i-1);
	 					out.writeObject(book);
	 				}
	 				out.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
	 			}
	 			
	 		};
	 		timer.scheduleAtFixedRate(tick, 1000, 1000);
	      } catch (Exception e) { 
	         System.err.println("Server exception: " + e.toString()); 
	         e.printStackTrace(); 
	      } 
	}

}
