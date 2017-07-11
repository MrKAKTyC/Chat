package SuPackage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		ServerSocket servSocket = new ServerSocket(Const.PORT);
		while(true){
			Socket clnt = servSocket.accept();
			System.out.println("Clint "+ clnt.toString() +" connected");
			ObjectInputStream inputStream   = new ObjectInputStream(clnt.getInputStream());
			Message text= (Message)inputStream.readObject();
			System.out.println(text.getTime()+" ["+text.getNickName()+"]: "+text.getText());
		}
		
		
	}

}
