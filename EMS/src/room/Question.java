package room;

import java.awt.image.BufferedImage;

public class Question {
	public Question(BufferedImage img, String name, long qn) {
		this.img=img;
		this.name=name;
		this.qn=qn;
	}
	private BufferedImage img;
	private String name;
	private long qn;
	public BufferedImage getImg() {
		return img;
	}
	public String getName() {
		return name;
	}
	public long getQn() {
		return qn;
	}
}
