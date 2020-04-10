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
		JTextArea inf = new JTextArea(from+"��(��)\n"+reason+"��(��) �Ϸ��� �մϴ�.");inf.setEditable(false);
		JLabel in=new JLabel("����Ϸ��� ��ȣ�� �Է��ϼ���.");
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
		String sel[]= {"���", "��� ����"};
		int s=JOptionPane.showOptionDialog(parent, c, "SES ����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, sel, sel[1]);
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
		JTextArea inf = new JTextArea(from+"��(��) "+tar+"��\n��� �����Ϸ��� �մϴ�.");inf.setEditable(false);
		JLabel in=new JLabel("����Ϸ��� ��ȣ�� �Է��ϼ���.");
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
		String sel[]= {"���", "��� ����"};
		int s=JOptionPane.showOptionDialog(parent, c, "SES ����", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, null, sel, sel[1]);
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
