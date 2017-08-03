package FileWorker;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import FileWorker.Filer.loginf;

abstract class DeSert {
	
	static void Serialize(String fileName, loginf obj) {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	static loginf DeSerialize(String fileName) {
		loginf s = null;
		try {
			FileInputStream fileIn = new FileInputStream(fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			s = (loginf) in.readObject();
			in.close();
			fileIn.close();
			return s;
		} catch (IOException i) {
			i.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.err.println("Can`t find nececery file");
			c.printStackTrace();
			return null;
		}
	}

}
