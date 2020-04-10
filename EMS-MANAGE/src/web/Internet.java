package web;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import exception.Except;
import io.IO;
import main.Root;

public class Internet {
	public static void CTRL() {
		isOn=!isOn;
	}	
	public static boolean isOn=false;
	public static ServerSocket ss=null;
	public static Vector<Connect> v = new Vector<Connect>();
	public static void SERVERON() {
		Thread SERVER = new Thread() {
			@Override
			public void run() {
				JSONObject ds=(JSONObject)Root.obj.get("reqFiles");
				try {
					Root.path=(String)ds.get("RootPath");
					String sd=IO.in(Root.path+(String)ds.get("ready_sentence"));
					while(true) {
						try {
							Socket s=ss.accept();
							Root.LogAdd(s.getInetAddress().getHostAddress()+": new association request", 0);
							v.add(new Connect(s,sd.replaceAll(System.getProperty("line.separator"), "")));
						} catch(Exception e) {
							if(e.getMessage().equals("socket closed")) {
								Root.LogAdd("Request loop stopped since ServerSocket is null", 0);
								return;
							}
							Root.LogAdd("Server association request failure: ("+e.getMessage()+")", 2);
						}
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "시작 메시지를 로드하지 못했습니다.\n기술 코드:"+Except.except(e1),"ERROR",JOptionPane.ERROR_MESSAGE);
					Root.LogAdd("Start sentence load failure : "+Except.except(e1), 2);
				}
			}
		};
		SERVER.start();
		Root.LogAdd("SERVER STARTED (PORT="+ss.getLocalPort()+")", 0);
	}
}
