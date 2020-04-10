package main;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import security.AES256;
import web.Internet;
import web.Wr;

public class Main {
	public static JFrame ipc = new JFrame();
	public Main() {
		ipc.setTitle("EMS");ipc.setIconImage(new ImageIcon("assets/icon.png").getImage());
		ipc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container x = ipc.getContentPane();x.setBackground(Color.white);x.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel te = new JLabel("시험 관리서버 주소:");ipc.setResizable(false);
		te.setFont(Root.getF(40, true));ipc.setSize(400,200);JButton nex = new JButton("확인");nex.setBorderPainted(false);
		x.add(te);x.setBackground(Color.white);x.setLayout(new FlowLayout(FlowLayout.CENTER));nex.setBackground(Color.white);
		JTextField addr=new JTextField("localhost"), port=new JTextField("8576");JLabel seq=new JLabel(":");port.setFont(Root.getF(27, false));
		addr.setPreferredSize(Root.getD(230, 45));port.setPreferredSize(Root.getD(100, 45));nex.setFont(Root.getF(25, false));
		addr.setFont(Root.getF(27, false));seq.setFont(Root.getF(27, true));x.add(addr);x.add(seq);x.add(port);x.add(nex);
		nex.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					addr.setEnabled(false);nex.setEnabled(false);port.setEnabled(false);
					Socket s = new Socket(addr.getText(),Integer.parseInt(port.getText()));
					Root.we=new Internet(s);
				} catch(Exception e1) {
					JOptionPane.showMessageDialog(x, "연결할 수 없습니다. 서버주소와 포트번호를 확인하세요.\n"+e1.getMessage(),"연결 실패",JOptionPane.ERROR_MESSAGE);
					addr.setEnabled(true);nex.setEnabled(true);port.setEnabled(true);
				}
			}
		});
		ipc.setVisible(true);
	}
	public static void main(String args[]) {
		new Main();
	}
}
