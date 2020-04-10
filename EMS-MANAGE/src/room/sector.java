package room;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class sector {
	public sector(JTextField a,JTextField b,JTextField c, JPanel p) {
		name=a;su=b;ve=c;
		display=p;
	}
	public JTextField name, su, ve;
	public JPanel display;
}
