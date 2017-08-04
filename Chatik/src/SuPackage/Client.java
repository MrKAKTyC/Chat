package SuPackage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import FileWorker.Filer;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		menu();
	}
//comment
	private static void menu() throws IOException {
		String name = Filer.Authorize(Const.FILENAME);
		Scanner scr = new Scanner(System.in);
		int key = 0;
		System.out.println("1 Connect to server\n2 Change name");
		key = Integer.parseInt(scr.nextLine());
		switch (key) {
		case 1:
			Socket socket;
			do {
				socket = connect();
			} while (socket == null);
			if (socket != null)
				send(socket, name);
			break;
		case 2:
			System.out.println("Enter you new nick");
			String NewName = scr.nextLine();
			System.out.println("You new name is "+ NewName);
			Filer.changeNick(NewName);
			break;
		default:
			break;
		}
	}

	private static Socket connect() throws IOException { // Unhandled
		String IP = Const.IP;
		int PORT = Const.PORT;
		Socket socket = null;
		System.out.println("Enter ip or left empty for default");
		Scanner scr = new Scanner(System.in);
		String Path = scr.nextLine();

		if (IpValidate(Path)) {
			IP = Path;
		}
		try {
			socket = new Socket(IP, PORT);
		} catch (ConnectException CE) {
			System.out.println("Can`t find server on this IP");
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

	private static boolean IpValidate(String Ip) {
		Pattern ippatern = Pattern
				.compile("^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
						+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		Matcher corectip = ippatern.matcher(Ip);
		return corectip.matches();
	}

}
