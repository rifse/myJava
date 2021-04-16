
public class PoudarjanjeZnakov {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String besediloPresledki = "Zadnja novica";
		String tiskPresledki = presledki(besediloPresledki);
		System.out.println(tiskPresledki);

		String besediloOznaceno = "Zadnja *novica* danes!";
		String tiskOznaceno = oznaceno(besediloOznaceno);
		System.out.println(tiskOznaceno);
	}
	
	private static String presledki (String vhod) {
		StringBuilder sb = new StringBuilder();
		int dolzina = vhod.length();
		for (int i = 0; i < dolzina; i++) {
			char c = Character.toUpperCase(vhod.charAt(i));
			sb = sb.append(c);
			if (i != dolzina - 1) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}
	
	private static String oznaceno (String vhod) {
		StringBuilder sb = new StringBuilder();
		int dolzina = vhod.length();
		boolean caps = false;
		//char zvezda = 
		for (int i = 0; i < dolzina; i++) {
			char c = vhod.charAt(i);
			if (c == '*') {
				caps = !caps;
			} else if (caps) {
				sb.append(Character.toUpperCase(c));
				//char c = Character.toUpperCase(c);
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

}
