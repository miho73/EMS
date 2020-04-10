package web;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Base64;

import org.json.simple.JSONObject;

import exception.Except;
import img.capt;
import img.simg;
import jj.play.ns.nl.captcha.Captcha;
import main.Root;
import room.reetu;
import security.AES256;
import security.ETC;
import security.Hash;
import security.RSA;

public class Connect {
	public Connect(Socket s,String sq) throws Exception {
		this.s=s;
		in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		Thread t = new Thread() {
			private String name, su, cer,anc;
			@Override
			public void run() {
				try {
					int serial=-1;
					Root.LogAdd("Initializing request", 0);
					AES=ETC.randText(16);
					String pubk=in.readLine();
					out.write(Base64.getEncoder().encodeToString(RSA.encrypt(AES, RSA.StrToKey(pubk)))+"\n");out.flush();
					Root.LogAdd(s.getInetAddress().getHostAddress()+" : Key transfer success",0);
					String ns="false";
					while(true) {
						Captcha c=capt.getImage(200, 70);
						String ans=c.getAnswer(),img=simg.encodeToString(c.getImage(), "png");
						out.write(AES256.encryptAES256(img, AES)+"\n");out.flush();
						String profile=AES256.decryptAES256(in.readLine(),AES);
						JSONObject los=(JSONObject) Root.jps.parse(profile);
						name=(String) los.get("name");su=(String) los.get("test_code");cer=(String) los.get("pwd");anc=(String) los.get("capt");
						if(anc.equals(ans)) {
							ns="true";
						}
						out.write(ns+"\n");out.flush();
						if(ns.equals("false")) {
							continue;
						}
						//전달받은 정보 기반 수험생 학적 확인-캡차 성공
						for(int i=0;i<Root.regis.size(); i++) {
							reetu sam=Root.regis.get(i);
							//System.out.println("EQV : "+sam.name+"-"+name+" "+sam.su+"-"+su+" "+sam.ve+"-"+cer);
							if(sam.su.equals(su)) {
								if(sam.ve.equals(cer)) {
									if(sam.name.equals(name)) {
										serial=i;break;
									} else continue;
								} else continue;
							} else continue;
						}
						if(serial==-1) {
							out.write("fail\n");out.flush();
							continue;
						} else {out.write("suc\n");out.flush();break;}
					}
					Root.LogAdd("CONNECTION SUCCESS", 0);
					out.write(AES256.encryptAES256(sq,AES)+"\n");out.write(AES256.encryptAES256((String)Root.obj.get("examName"),AES)+"\n");out.flush();
					Thread time = new Thread() {
						@Override
						public void run() {
							while(true) {
								try {
									String snd=comfo.get("timeSync", null);
									out.write(snd);
									Thread.sleep(1000);
								} catch(Exception e) {
									Root.LogAdd(s.getInetAddress().getHostAddress()+" TimeSync sleep failure\n"+Except.except(e), 2);
								}
							}
						}
					};
					time.start();
				} catch (Exception e) {
					Root.LogAdd("Initializing failure: ("+e.getMessage()+"),("+s.getInetAddress().getHostAddress()+")", 2);
				}
			}
		};
		t.start();
	}
	public String AES;
	public Socket s;
	public BufferedReader in;
	public BufferedWriter out;
}
