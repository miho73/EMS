package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import room.reetu;
import room.sector;
import security.Hash;

//MGR

public class Root {
	public static Hash hash = new Hash();
	public static String usrRoot=System.getProperty("user.home");
	public static Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension getD(int x, int y) {return new Dimension(x,y);}
	public static JSONParser jps = new JSONParser();
	public static Vector<sector> uil = new Vector<sector>();
	public static Vector<reetu> regis = new Vector<reetu>();
	public static Font getF(int size, boolean bold) {
		return new Font("¸¼Àº °íµñ",bold?Font.BOLD:Font.PLAIN,size);
	}
	public static JSONObject obj;
	public static String path;
	private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd E kk:mm:ss.SS");
	public static void LogAdd(Object str, int lv) {
		Date Now=new Date();
		String s="LOG-TYPE-NOT-DESIGNATED";
		switch(lv) {
			case 0:
				s="INFO";
				break;
			case 1:
				s="WARN";
				break;
			case 2:
				s="ERROR";
				break;
			case 3:
				s="FATAL";
		}
		Main.logTGET().append(date.format(Now)+" ["+s+"] : "+str+"\n");
		Main.logFGET().getVerticalScrollBar().setValue(Main.logFGET().getVerticalScrollBar().getMaximum()+2);
	}
	public static Vector<Object> access = new Vector<Object>();
}
