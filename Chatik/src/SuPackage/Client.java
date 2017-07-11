package SuPackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import FileWorker.Filer;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String name = Filer.Authorize(Const.FILENAME);
		send(connect(Const.IP), name);	
	}
	private static Socket connect(String IP) throws UnknownHostException, IOException{
	     Socket socket = new Socket(IP, Const.PORT);
		return socket;
	}
	
	private static void send(Socket socket, String name) throws IOException {
		Scanner scr = new Scanner(System.in);
	     System.out.println("Enter you message ->");
	     while(true)
	     {
	     System.out.print("say: ");
	     String message = scr.nextLine();
	     Date now = new Date(System.currentTimeMillis());
	     ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
		  Message NewMessage = new Message(message,name,now);
		 outputStream.writeObject(NewMessage);
	     }
	}

}

