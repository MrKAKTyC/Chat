package FileWorker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public abstract class Filer {
	public static String Authorize(String fileName) {
		File file = new File(fileName);
		String name = null;
		try {
			if (!file.exists()) {
				file.createNewFile();
				System.out.println('f');
				Scanner scr = new Scanner(System.in);
				System.out.println("Enter you nickname (without spaces)->");
				name = scr.nextLine();
				loginf lf = new loginf();
				lf.NickName = name;
				Serialize(fileName, lf);
				lf.Print();
			} else {
				loginf li = DeSerialize(fileName);
				li.Print();
			}
		} catch (IOException exptt) {
			exptt.printStackTrace();
			System.err.println("Can`t create conf file");
		}
		return name;
	}

	private static void Serialize(String fileName, loginf obj) {
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

	private static loginf DeSerialize(String fileName) {
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

	static class loginf implements Serializable {
		private static final long serialVersionUID = 1L;
		private String NickName;
		private Set<String> IpList = new TreeSet<String>();

		public void Print() {
			System.out.println("Nick: " + NickName);
			if (!IpList.isEmpty()) {
				System.out.println("Your previous chat servers");
				for (Iterator<String> iterator = IpList.iterator(); iterator.hasNext();) {
					String ipadres = (String) iterator.next();
					System.out.println(ipadres);
				}
			}else{
				System.out.println("You still didn't visit any chat server");
			}
		}
	}
}