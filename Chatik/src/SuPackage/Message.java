package SuPackage;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {
	private String text;
	private String NickName;
	private String time;
	public String getText() {
		return text;
	}
	public String getNickName() {
		return NickName;
	}
	public Message(String text, String nicName, Date time) {
		this.text = text;
		NickName = nicName;
		 DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	     this.time = formatter.format(time);
	}
	public String getTime() {
		return time;
	}

}
