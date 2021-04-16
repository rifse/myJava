
public class Collatz {
	
	private static int max;

	private static int stevec;

	public static void main(String[] args) {
		final int N = 6;
		izpisi(N);
		max = najvecji(N);
		System.out.format("Največji člen: %d%n", max);
		stevec = dolzina(N);
		System.out.format("Dolžina: %d%n", stevec);
	}
	
	
	private static int dolzina (int n) {
		do {
			n = naslednje (n);
			stevec += 1;
		} while (n != 1);
		return stevec + 1;
	}
	
	private static int najvecji (int n) {
		do {
			n = naslednje (n);
			if (n > max) {
				max = n;
			}
		} while (n != 1);
		return max;
	}

	private static void izpisi (int n) {
		do {
			System.out.format("%d%n", n);
			n = naslednje (n);
		} while (n != 1);
		System.out.println("1");
	}

	private static int naslednje (int n) {
		if (n % 2 == 1) {
			n = n*3 + 1;
		}
		else {
			n /= 2;
		}
		return n;
	}
}
