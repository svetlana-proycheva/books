package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BatFile {
	//run this file to start the application
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, InterruptedException {
		Scanner input = new Scanner(System.in);
		System.out.println(" олко клиентски приложени€ да се стартират?:");
		int clients = input.nextInt();
		
		PrintWriter writer = new PrintWriter("server.bat");

		writer.println("javac src/rmiInterface/MyInterface.java src/server/Book.java src/server/Server.java src/server/Business.java src/client/Client.java src/client/MainFrame.java");
		writer.println("cd src");
		writer.println("start rmiregistry");
		writer.println("java server/Server");
		writer.println("pause >");
		writer.close();
		Runtime rt = Runtime.getRuntime();
		rt.exec("cmd /c start "+"server.bat");
		Thread.sleep(15000);
		for(int i=1;i<=clients;i++) {
			writer = new PrintWriter("client.bat");
			writer.println("cd src");
			writer.println("java client/Client "+i);
			writer.println("pause >");
			writer.close();		
			rt.exec("cmd /c start "+"client.bat ");
			Thread.sleep(1500);
		}
	}

}
