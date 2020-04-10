package security;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.Root;

public class SPS {
	public static void Locker(Vector<Component> ass, JButton lock, JButton unlock, JLabel asl, String from, String tar, Component parent, JPanel group) {
		lock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Root.LogAdd("SES security displayed("+from+" is trying to unlock "+tar+")", 0);
				SES s=new SES(from,tar,parent);
				if(s.isFail()) {
					Root.LogAdd("SES security authentication failure", 1);
					return;
				}
				Root.LogAdd("SES security authentication approved", 0);
				s.keyBreak();
				ass.forEach((con)->con.setEnabled(true));
				asl.setText("완료하려면 자물쇠를 누르세요.");
				group.removeAll();
				group.add(unlock);group.add(asl);
				group.repaint();
			}
		});
		unlock.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Root.LogAdd(tar+" deauthorized", 0);
				ass.forEach((con)->con.setEnabled(false));
				asl.setText("설정을 변경하려면 자물쇠를 누르세요.");
				group.removeAll();
				group.add(lock);group.add(asl);
				group.repaint();
			}
		});
	}
}
