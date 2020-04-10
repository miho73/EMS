package main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import org.json.simple.parser.JSONParser;

import security.Hash;
import web.Internet;

public class Root {
	public static Hash hash = new Hash();
	public static JSONParser jps = new JSONParser();
	public static String usrRoot=System.getProperty("user.home");
	public static Dimension scr = Toolkit.getDefaultToolkit().getScreenSize();
	public static Dimension getD(int x, int y) {return new Dimension(x,y);}
	public static Font getF(int size, boolean bold) {
		return new Font("¸¼Àº °íµñ",bold?Font.BOLD:Font.PLAIN,size);
	}
	public static String exmName,branch;
	public static Internet we=null;
}
