package web;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicScrollBarUI;

import main.Root;
import question.Ques;

public class Wr {
	public static void rn(JFrame f, Container c) {
		c.setLayout(null);
		int x=Root.scr.width,y=Root.scr.height;
		JPanel qr = new JPanel();qr.setBackground(new Color(146,146,207));qr.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
		JPanel head = new JPanel();head.setSize(x-80,70);head.setLocation(40,30);head.setBackground(Color.white);head.setLayout(new FlowLayout(FlowLayout.LEFT));
		JScrollPane quelst = new JScrollPane(qr,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);quelst.setLocation(60,head.getY()+head.getHeight()+150);quelst.setSize(250,y-quelst.getY()-50);
		JLabel nae=new JLabel(Root.exmName), brc = new JLabel("-"+Root.branch);nae.setFont(Root.getF(50, true));brc.setFont(Root.getF(30, false));
		JPanel had = new JPanel();had.setLocation(quelst.getX()+quelst.getWidth(),quelst.getY()-65);had.setSize(x-80-quelst.getWidth(),65);had.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel tex = new JLabel(Ques.que.get(0).getQn()+". "+Ques.que.get(0).getName());tex.setFont(Root.getF(45, true));tex.setBackground(Color.white);
		had.add(tex);
		head.add(nae);head.add(brc);
		quelst.setVerticalScrollBar(new CustomScrollBar(JScrollBar.VERTICAL,new ImageIcon("assets/ScrollBar/thumb.png").getImage(),new ImageIcon("assets/ScrollBar/clk_thumb.png").getImage(),new ImageIcon("assets/ScrollBar/bg.png").getImage()));
		quelst.setHorizontalScrollBar(new CustomScrollBar(JScrollBar.HORIZONTAL,new ImageIcon("assets/ScrollBar/thumb.png").getImage(),new ImageIcon("assets/ScrollBar/clk_thumb.png").getImage(),new ImageIcon("assets/ScrollBar/bg.png").getImage()));
		//qr.setPreferredSize(Root.getD(quelst.getWidth(), 33*Ques.que.size()));
		///ADD QUESTIONS
		Ques.que.forEach((data)->{
			JLabel toAdd = new JLabel(data.getQn()+". "+data.getName());
			toAdd.setFont(Root.getF(23, true));
			toAdd.addMouseListener(new queChoose());
			//toAdd.setPreferredSize(Root.getD(qr.getWidth()-12,30));
			qr.add(toAdd);
		});
		c.add(head);c.add(quelst);c.add(had);
		c.repaint();c.revalidate();
		////DEBUG
	}
}
class queChoose implements MouseListener {
	@Override public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		((JLabel)e.getSource()).setForeground(new Color(220,220,250,255));
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		((JLabel)e.getSource()).setForeground(new Color(0,0,0,255));
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		((JLabel)e.getSource()).setForeground(new Color(200,200,250,255));
	}
	@Override
	public void mouseExited(MouseEvent e) {
		((JLabel)e.getSource()).setForeground(new Color(0,0,0,255));
	}
}
class CustomScrollBar extends JScrollBar
{
	private boolean isThumbPressed;
	private Image thumb;
	private Image thumbPressed;
	private Image track;
	private static final long serialVersionUID = 1L;
    public CustomScrollBar (final int orientation, final Image thumb, final Image thumbPressed, final Image track) { 
        super (orientation);
        this.thumb=thumb;this.thumbPressed=thumbPressed;this.track=track;
        addMouseListener (new MouseAdapter () {
            public void mousePressed (MouseEvent e) {
                isThumbPressed = true;
            }
            public void mouseReleased (MouseEvent e) {
            	 isThumbPressed = false;
            }
        });
        setUI(new cqs());
    }
    class cqs extends BasicScrollBarUI {
    	/*private ImageIcon downArrow, upArrow, leftArrow, rightArrow;
        public void BasicScrollBarUI(){
        	upArrow = new ImageIcon("assets/ScrollBar/vu.png");
        	downArrow = new ImageIcon("assets/ScrollBar/vd.png");
        	rightArrow = new ImageIcon("assets/ScrollBar/hr.png");
        	leftArrow = new ImageIcon("assets/ScrollBar/hl.png");
        }
        @Override
        protected JButton createDecreaseButton(int orientation) {
            JButton decreaseButton = new JButton(getAppropriateIcon(orientation)){
				private static final long serialVersionUID = 1L;

				@Override
                public Dimension getPreferredSize() {
                    return new Dimension(22, 22);
                }
            };
            return decreaseButton;
        }
        @Override
        protected JButton createIncreaseButton(int orientation) {
            JButton increaseButton = new JButton(getAppropriateIcon(orientation)){
				private static final long serialVersionUID = 1L;

				@Override
                public Dimension getPreferredSize() {
                    return new Dimension(22, 22);
                }
            };
            return increaseButton;
        }
        private ImageIcon getAppropriateIcon(int orientation){
            switch(orientation){
                case SwingConstants.SOUTH: return downArrow;
                case SwingConstants.NORTH: return upArrow;
                case SwingConstants.EAST: return rightArrow;
                    default: return leftArrow;
            }
        }*/
        @Override
        protected void paintThumb (Graphics g, JComponent c, Rectangle r) {
            if (isThumbPressed) g.drawImage (thumbPressed, r.x, r.y, r.width, r.height, null);
            else g.drawImage (thumb, r.x, r.y, r.width, r.height, null);
        }
        @Override protected void paintTrack (Graphics g, JComponent c, Rectangle r) {
            g.drawImage(track, r.x, r.y, r.width, r.height, null);
        }
    }
}
