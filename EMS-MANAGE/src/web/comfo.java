package web;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

import img.simg;
import main.Root;
import security.ETC;

public class comfo {
	@SuppressWarnings("unchecked")
	public static String get(String type, String data[]) {
		JSONObject snd = new JSONObject();
		switch(type) {
		case "timeSync":
			snd.put("type","timeSync");
			snd.put("time",main.Time.unit);
			break;
		case "beacon":
			snd.put("type","beacon");
			snd.put("sigNal", ETC.randText(16));
			break;
		case "question":
			snd.put("type", "question");
			String path=data[0];
			try {
				BufferedImage cls=ImageIO.read(new File(path));
				String mg=simg.encodeToString(cls, "png");
				snd.put("dat",mg);
			} catch (Exception e) {
				Root.LogAdd("Question packet fail", 2);
				return null;
			}
		}
		return snd.toJSONString()+"\n";
	}
}
