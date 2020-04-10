package web;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.security.KeyPair;
import java.util.Base64;
import java.util.Calendar;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import exception.Except;
import img.simg;
import main.Main;
import main.Root;
import question.Ques;
import room.Question;
import security.AES256;
import security.RSA;

public class Internet {
	String AES;
	public Internet(Socket s) throws Exception {
		this.s=s;
		in=new BufferedReader(new InputStreamReader(s.getInputStream()));
		out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					RSA rsa=new RSA();
					KeyPair pai=rsa.getKey();
					out.write(Base64.getEncoder().encodeToString(pai.getPublic().getEncoded())+"\n");out.flush();
					String s1=in.readLine();byte[] cdk=Base64.getDecoder().decode(s1);
					AES=RSA.decrypt(cdk, pai.getPrivate());
					Main.ipc.dispose();
					/*String imsg=AES256.decryptAES256(Internet.in.readLine(),AES);
					BufferedImage img=simg.decodeToImage(imsg);
					JLabel cap=new JLabel(new ImageIcon(img));
					JFrame ipc = new JFrame();
					ipc.setTitle("EMS");ipc.setIconImage(new ImageIcon("assets/icon.png").getImage());ipc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Container c = ipc.getContentPane();c.setLayout(new FlowLayout(FlowLayout.CENTER,0,4));ipc.setSize(400,210);ipc.setResizable(false);
					JLabel cp = new JLabel("아래 보이는 문자를 있는 대로 쓰세요.");cp.setFont(Root.getF(16, true));c.setBackground(Color.white);
					JTextField pf = new JTextField();pf.setFont(cp.getFont());pf.setPreferredSize(Root.getD(300, 30));
					JButton conf = new JButton("확인");conf.setFont(pf.getFont());conf.setBackground(Color.white);
					c.add(cp);c.add(cap);c.add(pf);c.add(conf);
							try {
								String inp=Root.hash.hash(String.valueOf(pf.getText()), Hash.SHA512);
								out.write(inp+"\n");out.flush();
								String s=AES256.decryptAES256(in.readLine(), AES);
								System.out.println(s);
								if(s.equals("true")) {
									ipc.setVisible(false);
									ipc.dispose();*/
					JFrame main= new JFrame("EMS");main.setExtendedState(JFrame.MAXIMIZED_BOTH);main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);main.setAlwaysOnTop(false);main.setUndecorated(true);
					Container p=main.getContentPane();p.setBackground(Color.white);p.setLayout(null);main.setResizable(false);
					JPanel j = new JPanel(),q=new JPanel();j.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));j.setSize(530,400);j.setBackground(Color.white);
					JLabel nam=new JLabel("이름 : "), cd=new JLabel("수험번호 : "), pri=new JLabel("인증번호 : "), sl=new JLabel("보안문자 : ");nam.setFont(Root.getF(20, true));cd.setFont(nam.getFont());
					JTextField ame=new JTextField("현창운"), tc=new JTextField("1"), cpt=new JTextField();JPasswordField crt=new JPasswordField("1");q.setLayout(new FlowLayout(FlowLayout.CENTER,10,1));
					JButton bnt = new JButton("확인");bnt.setFont(nam.getFont());bnt.setBackground(Color.white);q.setPreferredSize(Root.getD(490, 82));
					String imsg=AES256.decryptAES256(Internet.in.readLine(),AES);sl.setFont(nam.getFont());cpt.setPreferredSize(Root.getD(150,75));
					BufferedImage img=simg.decodeToImage(imsg);JLabel cap=new JLabel(new ImageIcon(img));q.add(sl);q.add(cap);q.add(cpt);cpt.setFont(Root.getF(45, true));
					pri.setFont(cd.getFont());ame.setFont(Root.getF(17, false));crt.setFont(ame.getFont());tc.setFont(ame.getFont());q.setBackground(Color.white);
					nam.setPreferredSize(Root.getD(130, 40));cd.setPreferredSize(nam.getPreferredSize());pri.setPreferredSize(cd.getPreferredSize());
					tc.setPreferredSize(Root.getD(370, 40));ame.setPreferredSize(tc.getPreferredSize());crt.setPreferredSize(tc.getPreferredSize());
					j.add(nam);j.add(ame);j.add(cd);j.add(tc);j.add(pri);j.add(crt);j.add(q);j.setLocation((Root.scr.width-530)/2,(Root.scr.height-400)/2);j.add(bnt);p.add(j);
					bnt.addActionListener(new ActionListener() {
						public void clear() {
							try {
								q.removeAll();
								q.add(sl);
								BufferedImage imgk=simg.decodeToImage(AES256.decryptAES256(Internet.in.readLine(), AES));
								JLabel capv=new JLabel(new ImageIcon(imgk));
								q.add(capv);q.add(cpt);ame.setText("");tc.setText("");cpt.setText("");crt.setText("");
								j.revalidate();
							} catch(Exception e) {
								Except.exception(e);
								Runtime.getRuntime().halt(-1);
							}
						}
						@SuppressWarnings("unchecked")
						@Override
						public void actionPerformed(ActionEvent e) {
							j.setEnabled(false);
							try {
								JSONObject snd=new JSONObject();
								snd.put("name",ame.getText());snd.put("test_code",tc.getText());snd.put("pwd",String.valueOf(crt.getPassword()));snd.put("capt", cpt.getText());
								out.write(AES256.encryptAES256(snd.toJSONString(), AES)+"\n");out.flush();
								if(in.readLine().equals("true")) {//CAPT
									String i=in.readLine();
									if(i.equals("fail")) {
										clear();
									} else if(i.equals("suc")) {
										p.removeAll();
										JPanel pq = new JPanel(), na=new JPanel();pq.setBackground(Color.white);pq.setSize(p.getWidth(),120);
										pq.setLocation(0,(p.getHeight()-120)/2);pq.setLayout(new FlowLayout(FlowLayout.CENTER));na.setBackground(Color.white);na.setSize(p.getWidth(),55);
										na.setLocation(0,p.getHeight()-80);na.setLayout(new FlowLayout(FlowLayout.CENTER));
										JLabel text = new JLabel("");text.setFont(Root.getF(65, true));text.setForeground(new Color(0,0,0,255));pq.add(text);
										JLabel ema = new JLabel("");ema.setFont(Root.getF(35, true));ema.setForeground(Color.black);na.add(ema);
										p.add(pq);p.add(na);
										p.repaint();p.revalidate();
										String des=AES256.decryptAES256(in.readLine(),AES);
										String exmn=AES256.decryptAES256(in.readLine(), AES);ema.setText(exmn);
										JSONObject fq=(JSONObject)Root.jps.parse(des);
										Vector<String> def = new Vector<String>();
										Vector<String> rnm = new Vector<String>();
										JSONArray a1=(JSONArray)fq.get("sentence"),a2=(JSONArray)fq.get("before_start"),a0=(JSONArray)fq.get("join");
										for(int i1=0;i1<a1.size();i1++) {def.add((String)a1.get(i1));}
										for(int i1=0;i1<a2.size();i1++) {rnm.add((String)a2.get(i1));}
										Thread txt2 = new Thread() {
											@Override
											public void run() {
												for(int iq=255;iq>=0;iq--) {
													text.setForeground(new Color(0,0,0,iq));pq.repaint();
													try {Thread.sleep(3);}catch(Exception e) {return;}
												}
												while(true) {
													for(int i=0;i<rnm.size();i++) {
														text.setText(rnm.get(i));
														for(int iq=0;iq<=255;iq++) {
															text.setForeground(new Color(0,0,0,iq));pq.repaint();
															try {Thread.sleep(3);}catch(Exception e) {return;}
														}
														try {Thread.sleep(1700);}catch(Exception e) {return;}
														for(int iq=255;iq>=0;iq--) {
															text.setForeground(new Color(0,0,0,iq));pq.repaint();
															try {Thread.sleep(3);}catch(Exception e) {return;}
														}
													}
												}
											}
										};
										Thread txt1 = new Thread() {
											@Override
											public void run() {
												for(int i=0;i<a0.size();i++) {
													text.setText((String)a0.get(i));
													for(int iq=0;iq<=255;iq++) {
														text.setForeground(new Color(0,0,0,iq));pq.repaint();
														try {Thread.sleep(3);}catch(Exception e) {return;}
													}
													try {Thread.sleep(1700);}catch(Exception e) {return;}
													for(int iq=255;iq>=0;iq--) {
														text.setForeground(new Color(0,0,0,iq));pq.repaint();
														try {Thread.sleep(3);}catch(Exception e) {return;}
													}
												}
												while(true) {
													for(int i=0;i<def.size();i++) {
														text.setText(def.get(i));
														for(int iq=0;iq<=255;iq++) {
															text.setForeground(new Color(0,0,0,iq));pq.repaint();
															try {Thread.sleep(3);}catch(Exception e) {return;}
														}
														try {Thread.sleep(1700);}catch(Exception e) {return;}
														for(int iq=255;iq>=0;iq--) {
															text.setForeground(new Color(0,0,0,iq));pq.repaint();
															try {Thread.sleep(3);}catch(Exception e) {return;}
														}
													}
												}
											}
										};
										Thread req = new Thread() {
											@Override
											public void run() {
												try {
													while(true) {
														JSONObject ap = (JSONObject) Root.jps.parse(AES256.decryptAES256(in.readLine(),AES));
														switch((String)ap.get("type") ) {
															case "timeSync":
																Calendar c = Calendar.getInstance();
																c.setTimeInMillis((long)ap.get("time")*1000);
																break;
															case "beacon":
																out.write((String)ap.get("signal")+"\n");out.flush();
																break;
															case "sig":
																if(((String)ap.get("code")).equals("0")) {
																	txt1.interrupt();
																	txt2.start();
																}
																if(((String)ap.get("code")).equals("1")) {
																	txt2.interrupt();p.removeAll();p.repaint();
																	Wr.rn(main,p);
																}
																out.write("clear\n");out.flush();
																break;
															case "question":
																System.out.println(ap.toJSONString());
																JSONArray q=(JSONArray)ap.get("data");
																q.forEach((con)->{
																	try {
																		JSONObject cach=(JSONObject)Root.jps.parse((String)con);
																		Ques.que.add(new Question(simg.decodeToImage((String)cach.get("pict")),(String)cach.get("name"),(long)cach.get("quenum")));
																	} catch (Exception e) {
																		Except.exception(e);
																	}
																});
																break;
															case "dat":
																Root.exmName=(String)ap.get("examName");
																Root.branch=(String)ap.get("branch");
																break;
														}
													}
												} catch (Exception e) {
													Except.exception(e);
												}
											}
										};
										req.start();
										txt1.start();
									} else {
										JOptionPane.showMessageDialog(j,"Unexpected response","Error",JOptionPane.ERROR_MESSAGE);
										System.exit(-1);
									}
								} else {
									clear();
								}
							} catch (Exception e1) {
								Except.exception(e1);
							}
						}
					});
					main.setVisible(true);
				} catch(Exception e) {
					Except.exception(e);
					System.exit(-1);
				}
			}
		};
		t.start();
	}
	public Socket s;
	public static BufferedReader in;
	public static BufferedWriter out;
}
