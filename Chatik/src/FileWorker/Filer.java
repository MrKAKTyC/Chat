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

import SuPackage.Const;

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
				DeSert.Serialize(fileName, lf);
				lf.Print();
			} else {
				loginf li = DeSert.DeSerialize(fileName);
				li.Print();
			}
		} catch (IOException exptt) {
			exptt.printStackTrace();
			System.err.println("Can`t create conf file");
		}
		return name;
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
	public static void showContent() {
		loginf li = DeSert.DeSerialize(Const.FILENAME);
		li.Print();
	}
	public static void changeNick(String NewName) {
		String NameOfFile = Const.FILENAME;
		loginf sesinf = DeSert.DeSerialize(Const.FILENAME);
		sesinf.NickName = NewName;
		DeSert.Serialize(NameOfFile, sesinf);
		
	}
}