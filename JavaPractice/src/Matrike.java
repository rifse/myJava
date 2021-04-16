import java.util.Arrays;

public class Matrike {

	public static void main(String[] args) {
		//double[][] X = null;  // empty
		//double[][] X = {{1, 2}, null};
		//double[][] X = {{1, 2}, {3, 3}, {5, 5, 6}};
		double[][] X = {{1, 2}, {4, 5}};
		double[][] Xt = Transponiraj(X);
		Izpisi(Xt);
	}

	private static boolean Preveri (double[][] A) {
		
		boolean nepravilna = false;
		if (A != null) {
			int oldLen = 0;
			for (int i = 0; i < A.length; i++) {
				if (A[i] != null) {  // row != null
					int newLen = A[i].length;
					if (i == 0) {
						oldLen = newLen;
					} else {
						if (oldLen != newLen) {
							System.out.println("A[i].length != A[0].length");
							nepravilna = true;
						}
					}
				} else {
					System.out.println("A[i] = null");
					nepravilna = true;
				}
			}
		} else {
			System.out.println("A = null");
			nepravilna = true;
		}
		return !nepravilna;
	}

	private static double[][] Transponiraj (double[][] A) {
		boolean ok = Preveri(A);
		if (ok) {
			int n = A.length;
			int m = A[0].length;
			double[][] At = new double[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					At[j][i] = A[i][j];
				}
			}
			return At;
		} else {
			return null;
		}
	}
	
	private static void Izpisi (double[][] A) {
		double[][] hack = Transponiraj(A);
		if (hack != null) {
			for (int i = 0; i < hack.length; i++) {
				System.out.println(Arrays.toString(hack[i]).replace(",", ""));
			}
		}
	}
}
