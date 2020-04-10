package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.net.ServerSocket;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.json.simple.JSONObject;

import exception.Except;
import io.IO;
import room.reetu;
import room.sector;
import security.Hash;
import security.SE;
import security.SES;
import security.SPS;
import web.Internet;

//MGR

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	JPasswordField jf = new JPasswordField();JButton che = new JButton("확인");JFrame pc = new JFrame("암호 입력");
	public Main() {
		pc.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container rsd=pc.getContentPane();pc.setResizable(false);
		rsd.setBackground(Color.white);pc.setIconImage(new  ImageIcon("assets/icon.png").getImage());
		pc.setSize(400,300);pc.setLocation((Root.scr.width-400)/2,(Root.scr.height-300)/2);
		System.out.println(Root.scr.width+" "+Root.scr.height);
		rsd.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel tit = new JLabel("암호 입력");tit.setFont(Root.getF(30, true));
		jf.setPreferredSize(new Dimension(320,35));
		che.setFont(Root.getF(25, false));che.setBackground(Color.white);
		jf.setFont(Root.getF(20, false));
		che.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PWDC();
			}
		});
		jf.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) PWDC();
			}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
		});
		rsd.add(tit);rsd.add(jf);rsd.add(che);
		pc.setVisible(true);
	}
	private void PWDC() {
		jf.setEnabled(false);che.setEnabled(false);
		String pwd=String.valueOf(jf.getPassword());
		try {
			String pwd1=Root.hash.hash(pwd, Hash.SHA512);
			String pwd2=IO.Filein(Root.usrRoot+"/.EMS-MGR/PWD.sec", pwd);
			if(!pwd1.equals(pwd2)) throw new Exception();
			SE.setKey(pwd);
			pc.dispose();
			after();
		} catch(Exception e1) {
			Thread t = new Thread() {
				@Override
				public void run() {
					Point l=jf.getLocation();
					jf.setEnabled(true);che.setEnabled(true);jf.setText("");
					double angle=0.0;
					while(true) {
						int cs=((int)(Math.cos(Math.toRadians(angle))*2))*-1;
						jf.setLocation(jf.getX()+cs,jf.getY());
						if(angle==360) break;
						angle+=4;
						try {Thread.sleep(1);} catch(Exception e){}
					}
					jf.setLocation(l);
				}
			};
			t.start();
			jf.requestFocus();
		}
	}
	public void svcof(boolean statp) {
		server.removeAll();
		server.add(la);server.add(stat);server.add(rev);
		if(statp) {
			server.add(on);
			rev.setText("실행중");
			port.setEnabled(false);
		} else {
			server.add(off);
			rev.setText("중단");
			port.setEnabled(true);
		}
		server.add(j1);server.add(pt);server.add(port);server.add(SVC);server.add(exf);
	}
	private JFrame edf = new JFrame();
	private String emf=null;
	private JLabel title = new JLabel("EMS"),pt=new JLabel("포트번호  : "),stat=new JLabel("서버 상태 : "),
			rev=new JLabel("중단"),time = new JLabel("준비중"), si1=new JLabel("설정을 변경하려면 자물쇠를 누르세요."),si2=new JLabel("설정을 변경하려면 자물쇠를 누르세요.");
	public static JTextArea Log = new JTextArea(""), etud=new JTextArea();
	private JPanel p1=new JPanel(), p2=new JPanel(),server=new JPanel(),la=new JPanel(), etu=new JPanel(), ev=new JPanel(), head = new JPanel(), secl=new JPanel();
	private JButton lcked = new JButton(new ImageIcon("assets/pro.png")),ulcked = new JButton(new ImageIcon("assets/unpro.png")),on=new JButton(new ImageIcon("assets/on.png")),off=new JButton(new ImageIcon("assets/off.png")),
			SVC = new JButton("서버 열기/닫기"), etued=new JButton("수험생 정보"), elo = new JButton(new ImageIcon("assets/pro.png")),eul = new JButton(new ImageIcon("assets/unpro.png")), exf=new JButton("시험데이터"),
			setd=new JButton("등록된 수험생");
	private JTextField port = new JTextField("8576");
	public static JScrollPane log=new JScrollPane(Log);
	private JLabel j1=new JLabel(), infetu=new JLabel("본 정보는 본인 외 누설하지 마세요.");
	private JPanel rls =  new JPanel(),ctrp = new JPanel();
	private JScrollPane ls = new JScrollPane(rls), ed = new JScrollPane(etud);
	private void after() {
		setTitle("EMS-Manage");setExtendedState(MAXIMIZED_BOTH);setIconImage(new ImageIcon("assets/icon.png").getImage());
		setDefaultCloseOperation(EXIT_ON_CLOSE);infetu.setForeground(Color.red);etu.setLayout(new FlowLayout(FlowLayout.CENTER));infetu.setFont(Root.getF(18, true));
		Container c = getContentPane();c.setBackground(Color.white);c.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));etu.add(infetu);etued.setBackground(Color.white);
		p1.setLayout(new FlowLayout(FlowLayout.LEFT));p2.setLayout(new FlowLayout(FlowLayout.RIGHT));etued.setFont(Root.getF(22, false));etu.add(ev);etu.add(etued);
		title.setFont(Root.getF(50, true));Log.setEditable(false);etu.setBackground(Color.white);etu.setBorder(new TitledBorder("수험생 정보"));ev.add(elo);ev.add(si2);
		pt.setFont(Root.getF(20, false));stat.setFont(pt.getFont());rev.setFont(pt.getFont());on.setLayout(new FlowLayout(FlowLayout.LEFT));
		server.setBorder(new TitledBorder("시험서버 상태"));server.setLocation(10,80);server.setBackground(Color.white);lcked.setPreferredSize(Root.getD(30, 30));
		log.setBorder(new TitledBorder("로그"));Log.setFont(Root.getF(15, false));elo.setBorderPainted(false);elo.setPreferredSize(Root.getD(30, 30));elo.setBackground(Color.white);
		eul.setBackground(Color.white);eul.setPreferredSize(Root.getD(30, 30));eul.setBorderPainted(false);ev.setBackground(Color.white);secl.setBorder(new TitledBorder("잠금 및 보안"));
		time.setFont(Root.getF(35, true));lcked.setBorderPainted(false);ulcked.setBorderPainted(false);ctrp.setBorder(new TitledBorder("Control Panel"));
		p1.setBackground(Color.white);p2.setBackground(Color.white);si1.setFont(Root.getF(20, false));si2.setFont(Root.getF(20,false));ctrp.setBackground(Color.white);
		server.setLayout(new FlowLayout(FlowLayout.LEFT));lcked.setBackground(Color.white);ulcked.setBackground(Color.white);port.setFont(pt.getFont());secl.setBackground(Color.white);
		la.add(lcked);la.add(si1);server.add(la);server.add(stat);server.add(rev);server.add(off);server.add(j1);server.add(pt);server.add(port);etud.setFont(Root.getF(20, false));
		on.setBackground(Color.white);off.setBackground(Color.white);on.setBorderPainted(false);off.setBorderPainted(false);la.setBackground(Color.white);
		port.setEnabled(false);pt.setEnabled(false);stat.setEnabled(false);rev.setEnabled(false);SVC.setFont(pt.getFont());on.setEnabled(false);setd.setFont(etued.getFont());
		SVC.setBackground(Color.white);off.setEnabled(false);log.setBackground(Color.white);exf.setBackground(Color.white);exf.setFont(SVC.getFont());setd.setBackground(Color.white);
		Vector<Component> lg=new Vector<Component>();lg.add(stat);lg.add(rev);lg.add(off);lg.add(on);lg.add(j1);lg.add(pt);lg.add(port);lg.add(SVC);lg.add(exf);
		Vector<Component> et=new Vector<Component>();et.add(etued);et.add(setd);JButton cls = new JButton("닫기");edf.setTitle("등록된 수험생");edf.setResizable(false);edf.setSize(500,300);
		edf.setLocation((Root.scr.width-500)/2,(Root.scr.height-300)/2);edf.setLayout(null);ed.setSize(500,300);ed.setLocation(0,0);edf.add(ed);head.setBackground(Color.white);
		SPS.Locker(lg, lcked, ulcked, si1, "서버", "서버구성", c, la);SPS.Locker(et, elo, eul, si2, "수험생인증", "수험생 목록", c, ev);etud.setEditable(false);ctrp.setBackground(Color.white);
		la.setLayout(new FlowLayout(FlowLayout.LEFT));server.add(SVC);ev.setLayout(new FlowLayout(FlowLayout.LEFT));edf.setIconImage(new ImageIcon("assets/icon.png").getImage());
		p1.add(title);p2.add(time);head.add(p1);head.add(p2);c.add(head);c.add(server);c.add(log);c.add(etu);server.add(exf);etu.add(setd);c.add(ctrp);c.add(secl);Time.start();head.setLayout(null);
		//etued.setEnabled(false);SVC.setEnabled(false);exf.setEnabled(false);setd.setEnabled(false);
		
		////////////////////////////////////////////////
		JFrame ep = new JFrame("수험생 정보");
		setd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				edf.setVisible(true);
			}
		});
		cls.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ep.setVisible(false);
			}
		});
		exf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container rn = new Container();
				JLabel inf = new JLabel("시험데이터 위치 : ");JTextField pat = new JTextField(emf);JButton fd = new JButton("찾기");
				rn.setLayout(new FlowLayout(FlowLayout.LEFT,3,0));inf.setFont(Root.getF(23, true));pat.setFont(Root.getF(17, false));fd.setFont(Root.getF(14, false));
				pat.setPreferredSize(Root.getD(500, 30));fd.setBackground(Color.white);
				fd.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFrame fde=new JFrame();
						FileDialog fd = new FileDialog(fde);
						fd.setTitle("시험데이터 위치 선택");fd.setMode(FileDialog.LOAD);
						fd.setIconImage(new ImageIcon("assets/icon.png").getImage());
						fd.setFile(System.getProperty("user.home")+"/Document");
						fd.setVisible(true);
						if(fd.getDirectory()!=null&&fd.getFile()!=null) {
							pat.setText(fd.getDirectory()+fd.getFile());
						}
					}
				});
				rn.add(inf);rn.add(pat);rn.add(fd);
				int sig=JOptionPane.showOptionDialog(c, rn, "서버 데이터파일", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
				if(sig==JOptionPane.OK_OPTION) {
					emf=pat.getText();
				}
			}
		});
		etued.addActionListener(new ActionListener() {
			private Container ec=null;
			@Override
			public void actionPerformed(ActionEvent e) {
				ep.setResizable(false);ep.setSize(1000,700);ec = ep.getContentPane();cls.setBackground(Color.white);
				ec.setBackground(Color.white);ep.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);cls.setSize(100,40);cls.setFont(Root.getF(25, false));
				JLabel tit = new JLabel("수험생 정보");tit.setFont(Root.getF(35, true));ec.setLayout(null);tit.setLocation(5,5);tit.setSize(300,40);cls.setLocation(450,625);
				ec.add(tit);ep.add(cls);ep.setIconImage(new ImageIcon("assets/icon-adm.png").getImage());
				JButton load=new JButton("불러오기"), save=new JButton("저장"), cle=new JButton("초기화"), add=new JButton("수험생 추가");add.setBackground(Color.white);
				load.setBackground(Color.white);save.setBackground(Color.white);cle.setBackground(Color.white);load.setFont(Root.getF(20, false));save.setFont(load.getFont());cle.setFont(save.getFont());
				load.setSize(200,35);save.setSize(200,35);cle.setSize(200,35);add.setSize(200,35);ls.setSize(985,400);add.setFont(save.getFont());
				load.setLocation(5,55);save.setLocation(load.getX()+load.getWidth()+5,55);cle.setLocation(save.getX()+save.getWidth()+5,55);add.setLocation(cle.getX()+cle.getWidth()+5,55);
				ec.add(load);ec.add(save);ec.add(cle);ec.add(add);ec.add(ls);rls.setLayout(null);
				addFb();
				add.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						adde();
					}
				});
				cle.addActionListener(new ActionListener( ) {
					@Override
					public void actionPerformed(ActionEvent e) {
						Root.uil.clear();rls.removeAll();rls.repaint();ls.repaint();rls.setPreferredSize(Root.getD(0, 0));ls.revalidate();rls.revalidate();
					}
				});
				save.addActionListener(new ActionListener() {
					@SuppressWarnings("unchecked")
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame fz=new JFrame();
						FileDialog f = new FileDialog(fz,"위치 결정",FileDialog.SAVE);
						f.setIconImage(new ImageIcon("assets/icon-adm.png").getImage());f.setVisible(true);
						if(f.getDirectory()==null||f.getFile()==null) return;
						String path=f.getDirectory()+f.getFile();
						int cq=JOptionPane.showConfirmDialog(ec, "저장하겠습니까?\n파일은 암호화되어 저장되지 않습니다.","저장 확인",JOptionPane.YES_NO_OPTION);
						if(cq==JOptionPane.YES_OPTION) {
							String sp=SES.SES_API("수험생인증", "수험생 정보 저장", ec);
							if(!sp.equals("null")) {
								ep.setEnabled(false);
								JSONObject q=new JSONObject(),inf=new JSONObject(),etu=new JSONObject();
								inf.put("head", 0);inf.put("tail",Root.uil.size());
								for(int i=0;i<Root.uil.size();i++) {
									JSONObject s = new JSONObject();
									sector x=Root.uil.get(i);
									s.put("name", x.name.getText());
									s.put("exam_code", x.su.getText());
									s.put("verification", x.su.getText());
									etu.put(i,s);
								}
								inf.put("hash", Root.hash.hash(etu.toJSONString(), Hash.SHA256));
								q.put("inf",inf);q.put("etu", etu);
								try {
									IO.Fileout(path, q.toJSONString(),sp);
								} catch (Exception e1) {
									JOptionPane.showMessageDialog(c, "저장하지 못했습니다.\n기술 코드 : "+Except.except(e1), "Error", JOptionPane.ERROR_MESSAGE);
								} finally {
									sp=null;ep.setEnabled(true);
								}
							}
						}
					}
				});
				load.addActionListener(new ActionListener() {
					@SuppressWarnings("deprecation")
					@Override
					public void actionPerformed(ActionEvent e) {
						JFrame f = new JFrame();
						FileDialog fd = new FileDialog(f,"응시자 파일 지정", FileDialog.LOAD);
						fd.setIconImage(new ImageIcon("assets/icon.png").getImage());
						fd.setVisible(true);
						if(fd.getDirectory()==null || fd.getFile()==null) return;
						String pd=SES.SES_API("수험생인증", "수험생정보 불러오기", ec);
						if(pd.equals("null")) return;
						String json=null;
						try {
							json = IO.Filein(fd.getDirectory()+fd.getFile(), pd);
							pd=null;
							JSONObject p=(JSONObject)Root.jps.parse(json), inf=(JSONObject)p.get("inf"), etu=(JSONObject)p.get("etu");
							if(!inf.get("hash").equals(Root.hash.hash(etu.toJSONString(),Hash.SHA256))) {
								Root.LogAdd("Student file hash fail", 2);Internet.CTRL();return;
							}
							int tail=new Long((long)inf.get("tail")).intValue();
							for(int i=0;i<tail;i++) {
								JSONObject q=(JSONObject)etu.get(new Integer(i).toString());
								adde();
								Root.uil.lastElement().name.setText((String)q.get("name"));
								Root.uil.lastElement().su.setText((String)q.get("exam_code"));
								Root.uil.lastElement().ve.setText((String)q.get("verification"));
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(c, "불러오지 못했습니다.\n기술 코드 : "+Except.except(e1), "Error", JOptionPane.ERROR_MESSAGE);
							pd=null;
							return;
						} finally {
							pd=null;
						}
					}
				});
				ep.setVisible(true);
			}
			private void addFb() {
				JLabel name = new JLabel("이름"), su=new JLabel("수험번호"), ve = new JLabel("인증번호"), de=new JLabel(new ImageIcon("assets/x.png"));
				name.setFont(Root.getF(25, true));su.setFont(name.getFont());ve.setFont(su.getFont());
				name.setSize(128,40);su.setSize(616,40);ve.setSize(200,40);name.setBorder(new EtchedBorder());su.setBorder(name.getBorder());de.setSize(40,40);
				ve.setBorder(su.getBorder());ec.add(name);ec.add(ve);name.setLocation(5,100);su.setLocation(name.getWidth()+5,100);ve.setLocation(su.getX()+su.getWidth(),100);ec.add(su);
				ls.setLocation(5,ve.getY()+ve.getHeight());de.setBorder(ve.getBorder());ec.add(de);de.setLocation(ve.getX()+ve.getWidth(),100);
			}
			private void adde() {
				JPanel p = new JPanel();p.setSize(ls.getWidth()-5,40);p.setLocation(0,40*Root.uil.size());p.setBackground(Color.white);p.setLayout(null);JButton del = new JButton(new ImageIcon("assets/x.png"));
				JTextField name=new JTextField(), su=new JTextField(), ve=new JTextField();name.setSize(128,40);name.setLocation(0,0);su.setSize(616,40);su.setLocation(128,0);
				ve.setSize(200,40);ve.setLocation(744,0);p.add(name);p.add(su);p.add(ve);name.setFont(Root.getF(20, false));ve.setFont(Root.getF(20, false));su.setFont(Root.getF(20, false));
				del.setSize(39,39);del.setLocation(944,0);del.setBackground(Color.white);
				sector me = new sector(name,su,ve,p);
				Root.uil.add(me);rls.add(p);rls.setPreferredSize(Root.getD(ls.getWidth()-5, 40*Root.uil.size()+30));rls.repaint();ls.repaint();ls.revalidate();p.add(del);
				del.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Root.uil.remove(me);rls.remove(p);rls.setPreferredSize(Root.getD(ls.getWidth()-5, 40*Root.uil.size()+30));rls.repaint();ls.repaint();ls.revalidate();p.add(del);
						for(int i=0;i<Root.uil.size();i++) {
							Root.uil.get(i).display.setLocation(0,i*40);
						}
					}
				});
			}
		});
		SVC.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Internet.CTRL();
					if(Internet.isOn) {
						File f = new File(emf);
						if(!f.exists()) {
							JOptionPane.showMessageDialog(c, "설정파일이 존재하지 않습니다.");
						}
						Root.obj=(JSONObject) Root.jps.parse(IO.in(emf));
						String pdw=SES.SES_API("서버", "서버시작", c);
						if(pdw.equals("null")) {
							Root.LogAdd("ServerStart: Password Fail", 1);Internet.CTRL();
							return;
						}
						String etus=IO.Filein((String)(((JSONObject)Root.obj.get("reqFiles")).get("RootPath"))+(String)(((JSONObject)Root.obj.get("reqFiles")).get("studata")), pdw);
						JSONObject p=(JSONObject)Root.jps.parse(etus), inf=(JSONObject)p.get("inf"), etu=(JSONObject)p.get("etu");
						if(!inf.get("hash").equals(Root.hash.hash(etu.toJSONString(),Hash.SHA256))) {
							Root.LogAdd("Student file hash fail", 2);Internet.CTRL();return;
						}
						int tail=new Long((long)inf.get("tail")).intValue();
						etud.setText("이름>>수험번호>>인증번호\n");
						Root.regis.clear();
						for(int i=0;i<tail;i++) {
							JSONObject q=(JSONObject)etu.get(new Integer(i).toString());
							Root.regis.add(new reetu((String)q.get("name"),(String)q.get("exam_code"),(String)q.get("verification")));
							etud.append((String)q.get("name")+">>"+(String)q.get("exam_code")+">>"+(String)q.get("verification")+"\n");
						}
						Internet.ss=null;
						Internet.ss=new ServerSocket(Integer.parseInt(port.getText()));
						Internet.ss.setSoTimeout(0);
						svcof(true);
						Thread t = new Thread() {
							@Override
							public void run() {
								SVC.setBackground(new Color(0,255,0));
								try {
									for(int i=0;i<255;i++) {
										Thread.sleep(4);
										SVC.setBackground(new Color(i,255,i));
									}
								} catch (InterruptedException e) {
									JOptionPane.showMessageDialog(c, "그래픽 이벤트 실패\n기술 코드 : "+Except.except(e),"오류",JOptionPane.ERROR_MESSAGE);
								}SVC.setBackground(Color.white);
							}
						};
						Internet.SERVERON();
						t.start();
					} else {
						Internet.ss.close();
						svcof(false);Root.LogAdd("SERVER CLOSED", 0);
						Thread t = new Thread() {
							@Override
							public void run() {
								SVC.setBackground(new Color(0,255,0));
								try {
									for(int i=0;i<255;i++) {
										Thread.sleep(4);
										SVC.setBackground(new Color(i,255,i));
									}
								} catch (InterruptedException e) {
									JOptionPane.showMessageDialog(c, "그래픽 이벤트 실패\n기술 코드 : "+Except.except(e),"오류",JOptionPane.ERROR_MESSAGE);
								}SVC.setBackground(Color.white);
							}
						};
						t.start();
					}
				} catch(Exception e1) {
					Internet.CTRL();
					Root.LogAdd("SERVER CONTROL FAILURE: ("+e1.getMessage()+")\nCode : "+Except.except(e1), 2);
					Thread t = new Thread() {
						@Override
						public void run() {
							SVC.setBackground(new Color(255,0,0));
							try {
								for(int i=0;i<255;i++) {
									Thread.sleep(4);
									SVC.setBackground(new Color(255,i,i));
								}
							} catch (InterruptedException e) {
								JOptionPane.showMessageDialog(c, "그래픽 이벤트 실패\n기술 코드 : "+Except.except(e),"오류",JOptionPane.ERROR_MESSAGE);
							}SVC.setBackground(Color.white);
						}
					};
					t.start();
				}
			}
		});
		Thread autoGUI = new Thread(){
			@Override
			public void run() {
				while(true) {
					int x=getWidth(),y=getHeight();
					String m=String.valueOf(Time.m),h=String.valueOf(Time.h),ms=String.valueOf(Time.ms),s=String.valueOf(Time.s);
					p1.setSize(new Dimension(x-p2.getWidth()-20,70));server.setPreferredSize(new Dimension(x*3/13,200));server.setLocation(5,p1.getY()+p1.getHeight());
					p2.setSize(p2.getPreferredSize());p2.setLocation(p1.getWidth()+10,6);
					j1.setPreferredSize(Root.getD(server.getPreferredSize().width-250, 10));etu.setPreferredSize(server.getPreferredSize());etu.setLocation(server.getX()+server.getWidth(),server.getY());
					port.setPreferredSize(Root.getD(server.getPreferredSize().width-150, 26));head.setPreferredSize(Root.getD(x, 70));
					log.setLocation(server.getX(),server.getY()+server.getHeight()+5);log.setPreferredSize(Root.getD(server.getWidth(), y-log.getY()-40));
					la.setPreferredSize(Root.getD(server.getPreferredSize().width-20, 40));ev.setPreferredSize(Root.getD(etu.getPreferredSize().width-20, 40));
					ctrp.setLocation(etu.getX(),log.getY());ctrp.setPreferredSize(Root.getD(x-ctrp.getX()-30,log.getHeight()));
					secl.setSize(etu.getSize());secl.setLocation(etu.getX()+etu.getWidth()+5,etu.getY());
					if(ms.length()<2) continue;
					time.setText(Time.y+"년 "+Time.M+"월 "+Time.d+"일 "+(h.length()==2?h:'0'+h)+"시 "+(m.length()==2?m:'0'+m)+"분 "+(s.length()==2?s:'0'+s)+"."+ms.substring(0,2)+'초');
				}
			}
		};
		autoGUI.start();Root.LogAdd("UI Loaded", 0);
		setVisible(true);
	}
	public static void main(String args[]) {
		try {
			String s=Root.hash.hash(args[0], Hash.SHA512);
			if(!s.equals("10bfd4733169bbdf35962dd5d2b9da94e92d80eb9de25943cb8502bddb278f8e90ea715abe0bb3f423ea63b81c59b1d1246ccced68fa52aabc5d596781279137")) {
				throw new Exception("Cannot auth start.");				
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, Except.except(e), "시작 실패", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		if(!new File(Root.usrRoot+"/.EMS-MGR/PWD.sec").exists()) {
			JPanel p = new JPanel();p.setLayout(new FlowLayout());
			JPasswordField pf = new JPasswordField();p.add(pf);
			pf.setPreferredSize(new Dimension(400,20));
			JOptionPane.showMessageDialog(null, p, "초기 암호 설정", JOptionPane.DEFAULT_OPTION);
			if(!new File(Root.usrRoot+"/.EMS-MGR").exists()) {
				new File(Root.usrRoot+"/.EMS-MGR").mkdir();
			}
			try {
				if(String.valueOf(pf.getPassword()).equals("null")) {
					new Exception("암호는 null 일 수 없습니다.");
				}
				//암호 생성 정책//
				char[] c=pf.getPassword();
				StringBuffer lq = new StringBuffer();boolean flg=false;
				if(c.length<4) {
					lq.append("암호는 4자리 이상이여야 합니다.\n");
					flg=true;
				}
				for(char ch : c) {
					if(ch==' ') {
						lq.append("암호에는 공백이 포함될 수 없습니다.\n");flg=true;break;
					}
				}
				if(flg) {
					JOptionPane.showMessageDialog(null, "죄송합니다. 암호 생성 정책에 맞지 않습니다.\n"+lq,"PPB",JOptionPane.ERROR_MESSAGE);return;
				}
				//
				while(true) {
					JFrame fds=new JFrame();
					FileDialog fd=new FileDialog(fds, "복구키 저장 파티션", FileDialog.SAVE);
					fd.setTitle("복구키 저장");
					fd.setIconImage(new ImageIcon("assets/icon.png").getImage());
					fd.setVisible(true);
					String dir=fd.getDirectory(), fil=fd.getFile();
					System.out.println(dir+" "+fil);
					if(dir==null||fil==null) return;
					int cnt=0;
					for(char cd : dir.toCharArray()) {
						if(cd=='/'||cd=='\\') cnt++;
					}
					if(cnt>1) {
						JOptionPane.showMessageDialog(null, "복구키는 파티션의 ");
					}
					break;
				}
				IO.Fileout(Root.usrRoot+"/.EMS-MGR/PWD.sec", Root.hash.hash(String.valueOf(pf.getPassword()), Hash.SHA512), String.valueOf(pf.getPassword()));
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "죄송합니다. 암호를 저장하지 못했습니다.\n기술 코드:"+Except.except(e),"ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
		new Main();
	}
	public static JScrollPane logFGET() {
		return log;
	}
	public static JTextArea logTGET() {
		return Log;
	}
}
