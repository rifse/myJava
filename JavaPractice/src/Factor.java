
public class Factor {

	private static int najmanjse = 2;
	
	public static void main(String[] args) {
		final int N = 5761665;
		String tekst = faktoriziraj(N);
		System.out.println(tekst);
	}

	private static String faktoriziraj (int n) {
		StringBuilder sb = new StringBuilder();
		sb = sb.append(String.format("%d =", n));
		while (najmanjse <= n) {
			int eksponent = 0;
			while (n % najmanjse == 0) {
				n /= najmanjse;
				eksponent += 1;
			}
			if (eksponent > 0) {
				sb = sb.append(String.format(" %s", najmanjse));
				if (eksponent > 1) {
					sb = sb.append(String.format("^%d", eksponent));
				}
				if (n != 1) {
					sb = sb.append(" *");
				}
			}
			najmanjse += 1;
		}
		String result = sb.toString();
		return result;
	}
}
