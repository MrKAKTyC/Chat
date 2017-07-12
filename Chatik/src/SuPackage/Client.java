package SuPackage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import FileWorker.Filer;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		String name = Filer.Authorize(Const.FILENAME);
		Socket skt = connect();
		if (skt != null)
			send(skt, name);
	}

	private static Socket connect() throws UnknownHostException, IOException { //Unhandled 
		String IP = null;
		int PORT = (Integer) null;
		Socket socket = null;
		System.out.println("Enter ip and port or left empty for default");
		Scanner scr = new Scanner(System.in);
		String Path = scr.nextLine();
		// Переробить на регекс і кейси
		if(Path.indexOf(':') != -1)
		{
			String[] Routs = Path.split(":");
			IP = Routs[0];
			PORT = Integer.parseInt(Routs[1]);
		} else {
			IP = Path;
			PORT = Const.PORT;
		}
			
		try {
			socket = new Socket(IP, PORT);
		}
		catch (ConnectException CE) {
			System.err.println("Wrong Ip or Port");
		}
		return socket;
	}

	private static void send(Socket socket, String name) throws IOException {
		Scanner scr = new Scanner(System.in);
		System.out.println("Enter you message ->");
		while (true) {
			System.out.print("say: ");
			String message = scr.nextLine();
			Date currentTime = new Date(System.currentTimeMillis());
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			Message NewMessage = new Message(message, name, currentTime);
			outputStream.writeObject(NewMessage);
		}
	}

}
