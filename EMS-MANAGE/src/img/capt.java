package img;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import jj.play.ns.nl.captcha.Captcha;
import jj.play.ns.nl.captcha.backgrounds.GradiatedBackgroundProducer;
import jj.play.ns.nl.captcha.gimpy.DropShadowGimpyRenderer;
import jj.play.ns.nl.captcha.text.renderer.DefaultWordRenderer;

public class capt {
	public static Captcha getImage(int x, int y) {
		List<Font> fl=new ArrayList<Font>();
		fl.add(new Font("", Font.HANGING_BASELINE, 40));
        fl.add(new Font("Courier", Font.ITALIC, 40));
        fl.add(new Font("", Font.PLAIN, 40));//new NumbersAnswerProducer(10),
		return new Captcha.Builder(x, y).addText(new DefaultWordRenderer(Color.black,fl))
										.addBackground(new GradiatedBackgroundProducer())
										.addNoise().addNoise().addNoise()
										.gimp(new DropShadowGimpyRenderer())
										.addBorder()
										.build();
	}
}
