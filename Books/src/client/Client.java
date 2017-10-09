package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import rmiInterface.MyInterface;


public class Client {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
			      try {  
			 	         Registry registry = LocateRegistry.getRegistry(null); 
			 	         MyInterface stub = (MyInterface)registry.lookup("Business"); 

							JFrame frame = new MainFrame("Дарител "+args[0], stub );
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame.setSize(500, 250);
							frame.setResizable(false);//
							frame.setVisible(true);
			 	      } catch (Exception e) {
			 	         System.err.println("Client exception: " + e.toString()); 
			 	         e.printStackTrace(); 
			 	      }
			}
		});

	}

}
