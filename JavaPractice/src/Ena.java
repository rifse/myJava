import java.util.Arrays;

public class Ena {

	public static void main(String[] args) {

		int stClen = 20;
		String[] result = SestaviZaporedje(stClen);
		System.out.println(Arrays.deepToString(result));
	}
	
	private static String[] SestaviZaporedje (int steviloClenov) {

		String[] temp = new String[steviloClenov];
		temp[0] = "1";
		for (int i = 1; i < steviloClenov; i++) {
			String lastStr = temp[i-1];
			int count = 1;
			String newStr = "";
			char lastChar = lastStr.charAt(0);
			for (int j = 1; j < lastStr.length(); j++) {
				char newChar = lastStr.charAt(j);
				if (newChar == lastChar) {
					count++;
				} else {
					newStr += String.valueOf(count) + lastChar;
					lastChar = newChar;
					count = 1;
				}
			}
			newStr += String.valueOf(count) + lastChar;
			temp[i] = newStr;
		}
		return temp;
	}
}
