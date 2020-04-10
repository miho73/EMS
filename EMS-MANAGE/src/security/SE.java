package security;

import main.Root;

public class SE {
	private static String KH;
	public static void setKey(String key) {
		KH=Root.hash.hash(key, Hash.SHA512);
	}
	public static String getKey() {
		return KH;
	}
	public static boolean keyCheck(String key) {
		if(KH.equals(key)) return true;
		else return false;
	}
}
