import java.util.Arrays;

public class OdvodPolinoma {

	// Poskrbi, da bo funkcijo možno poklicati tudi brez parametra n. V tem primeru naj vrne prvi odvod. ????

	public static void main(String[] args) {

		//float[] p1 = {1, 2, 3};
		float[] p2 = {4, -1, 2, 0, 1};
		//float[] p3 = {1};

		float [] polinom = p2;
		float[] result = Odvod (polinom, 2);
		System.out.println(Arrays.toString(result));
	}
	
	public static float[] Odvod (float[] original, int steviloOdvajanj) {
			
		int stopnja = original.length;
		float[] odvod = new float[stopnja - 1];
		for (int i = 1; i < stopnja; i++) {
			odvod[i-1] = (i) * original[i];
		}
		if (steviloOdvajanj > 1) {
			return Odvod(odvod, steviloOdvajanj - 1); 
		} else {
			return odvod;
		}
	}

}
