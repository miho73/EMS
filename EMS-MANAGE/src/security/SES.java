package security;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import main.Root;

public class SES {
	private String SESKEY=null;
	public static String SES_API(String from, String reason, Component parent) {
		JPanel c = new JPanel();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		c.setBackground(Color.white);
		c.setPreferredSize(Root.getD(600,170));
		JTextArea inf = new JTextArea(from+"이(가)\n"+reason+"을(를) 하려고 합니다.");inf.setEditable(false);
		JLabel in=new JLabel("계속하려면 암호를 입력하세요.");
		JPasswordField jpw = new JPasswordField();
		in.setFont(Root.getF(20, false));
		inf.setPreferredSize(Root.getD(600, 70));
		inf.setFont(Root.getF(25, true));
		jpw.setPreferredSize(Root.getD(550,30));jpw.setFont(Root.getF(18, false));
		c.add(inf);c.add(in);c.add(jpw);jpw.setFocusable(true);
		Thread t = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						jpw.requestFocus();						
					} catch(Exception e) {
						return;
					}
				}
			}
		};
		t.start();
		String sel[]= {"취소", "잠금 해제"};
		int s=JOptionPane.showOptionDialog(parent, c, "SES 보안", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, sel, sel[1]);
		t.interrupt();
		if(s!=0) {
			if(SE.keyCheck(Root.hash.hash(String.valueOf(jpw.getPassword()), Hash.SHA512))) {
				return String.valueOf(jpw.getPassword());
			}
		}
		return "null";
	}
	public SES(String from, String tar, Component parent) {
		JPanel c = new JPanel();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		c.setBackground(Color.white);
		c.setPreferredSize(Root.getD(600,170));
		JTextArea inf = new JTextArea(from+"이(가) "+tar+"을\n잠금 해제하려고 합니다.");inf.setEditable(false);
		JLabel in=new JLabel("허용하려면 암호를 입력하세요.");
		JPasswordField jpw = new JPasswordField();
		in.setFont(Root.getF(20, false));
		inf.setPreferredSize(Root.getD(600, 70));
		inf.setFont(Root.getF(25, true));
		jpw.setPreferredSize(Root.getD(550,30));jpw.setFont(Root.getF(18, false));
		c.add(inf);c.add(in);c.add(jpw);jpw.setFocusable(true);
		Thread t = new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						jpw.requestFocus();						
					} catch(Exception e) {
						return;
					}
				}
			}
		};
		t.start();
		String sel[]= {"취소", "잠금 해제"};
		int s=JOptionPane.showOptionDialog(parent, c, "SES 보안", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, sel, sel[1]);
		t.interrupt();
		if(s!=0) {
			if(SE.keyCheck(Root.hash.hash(String.valueOf(jpw.getPassword()), Hash.SHA512))) {
				SESKEY=String.valueOf(jpw.getPassword());
			}
		}
	}
	public boolean isFail() {
		return SESKEY==null?true:false; 
	}
	public void keyBreak() {
		SESKEY=null;
	}
}
