
public abstract class Util {

	public static String convertNumToAlph(int i) {
		int k = i%8;
		
		switch(k) {
			case 0:
				return "a";
			case 1:
				return "b";
			case 2:
				return "c";
			case 3:
				return "d";
			case 4:
				return "e";
			case 5:
				return "f";
			case 6:
				return "g";
			case 7: 
				return "h";
		}
		
		return "ERROR";
	}
	
	public static String padBinaryString(String string) {
		while(string.length() < 64) {
			string = "0" + string;
		}
		
		return string;
	}
}
