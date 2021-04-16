
public class Test {

	public static void main(String[] args) {
//		Tocka tockaX = new Tocka("a");
//		Graf graf = new Graf();
//		System.out.println(tockaX.stopnja());
//		System.out.println(tockaX.toString());

//		System.out.println(g.tocka("a"));
//		System.out.println(g.dodajTocko("a"));
//		System.out.println(g.tocka("a"));
//		System.out.println(g.dodajTocko());
//		System.out.println(g.dodajTocko());
//		System.out.println(g.tocka("0"));

//		Graf prazen = Graf.prazen(10);
//		prazen.izpis();
//		Graf cikel = Graf.cikel(10);
//		cikel.izpis();
//		Graf poln = Graf.poln(6);
//		poln.izpis();
		Graf polnDvodelen = Graf.polnDvodelen(3, 4);
//		Graf polnDvodelen = Graf.polnDvodelen(4, 7);
//		polnDvodelen.izpis();
		Graf x = polnDvodelen;
//		for (Tocka i: x.tocka(j))

		Okno okno = new Okno(750, 750);
		okno.platno.nastaviGraf(x);
		okno.platno.graf.razporedi(375, 375, 275);
		okno.pack();
		okno.setVisible(true);
//		for (Tocka i : okno.platno.graf.tocke.values()) {
//			System.out.println(okno.platno.graf.tocka(i.toString()).sosedi.toString());
//		}
	}
}