package security;

import java.util.Random;

public class ETC {
	public static String randText(int lenght) {
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < lenght; i++) {
		    int rIndex = rnd.nextInt(3);
		    switch (rIndex) {
		    case 0:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 97));
		        break;
		    case 1:
		        temp.append((char) ((int) (rnd.nextInt(26)) + 65));
		        break;
		    case 2:
		        temp.append((rnd.nextInt(10)));
		        break;
		    }
		}
		return temp.toString();
	}
}
