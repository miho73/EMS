package exception;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import main.Root;

public class Except {
	private static GridBagLayout layout = new GridBagLayout();
	private static JPanel p = new JPanel();
	public static void exception(Exception e) {
		p.removeAll();
		JLabel title = new JLabel("There was a problem while performing the operation.");
		JLabel caption = new JLabel("Exception Message: "+e.getMessage());
		JLabel exp = new JLabel("¡å Exception Log");
		JTextArea log = new JTextArea();
		JScrollPane alo = new JScrollPane(log,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		title.setFont(Root.getF(15, true));
		caption.setFont(Root.getF(15, false));
		exp.setFont(Root.getF(15, false));
		log.setFont(Root.getF(13, false));
		log.setEditable(false);
		alo.setBorder(new EtchedBorder());
		StackTraceElement[] ste = e.getStackTrace();
		StringBuffer trace=new StringBuffer("Exception excepted: "+e.toString()+'\n');
		for(StackTraceElement q : ste) {
			trace.append("   at "+q+"\n");
		}
		log.setText(trace.toString());alo.setVisible(true);
		gbinsert(title,0,0,1,1);
		gbinsert(caption,0,1,1,1);
		gbinsert(exp,0,2,1,1);
		gbinsert(alo,0,3,1,3);
		p.setLayout(layout);
		alo.setPreferredSize(Root.getD(title.getPreferredSize().width, 150));
		/*exp.addMouseListener(new MouseListener() {
			private boolean isDisplay=true;
			@Override
			public void mouseClicked(MouseEvent e) {
				isDisplay=!isDisplay;
				if(isDisplay) {
					gbinsert(alo,0,3,1,3);
				} else {
					p.remove(alo);
				}
				p.repaint();
			}
			@Override
			public void mousePressed(MouseEvent e) {
				exp.setForeground(Color.gray);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				exp.setForeground(Color.black);
			}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});*/
		JOptionPane.showOptionDialog(null, p, e.getMessage(), JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, null, null);
	}
	private static void gbinsert(Component c, int x, int y, int w, int h){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridx = x; 
        gbc.gridy = y;
        gbc.gridwidth = w; 
        gbc.gridheight = h;
        layout.setConstraints(c,gbc);
        p.add(c);
    }
}
