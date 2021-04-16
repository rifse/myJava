//import java.io.Serializable;
import java.util.HashMap;

//public class Graf implements Serializable {
public class Graf {
	
//	static final long serialVersionUID = 1L;

	private int stevec = 0;
	protected HashMap<String, Tocka> tocke;
	
	public Graf () {
		tocke = new HashMap<String, Tocka>();
	}
	public Tocka tocka (String ime) {
		return tocke.get(ime);
	}
	public boolean povezava (Tocka a, Tocka b) {
		return a.sosedi.contains(b);
	}
	public Tocka dodajTocko (String ime) {
		if (tocka(ime) != null) {
			return tocka(ime);
		} else {
			Tocka T0 = new Tocka(ime);
			tocke.put(ime, T0);
			return T0;
		}
	}
	public Tocka dodajTocko () {
		if (tocka(Integer.toString(stevec)) != null) {
			stevec++;
			return dodajTocko();
		} else {
			String ime = Integer.toString(stevec);
			Tocka T0 = new Tocka(ime);
			tocke.put(ime, T0);
			return T0;	
		}
	}
	public void dodajPovezavo (Tocka a, Tocka b) {
		if (a.toString() != b.toString()) {
			a.sosedi.add(b);
			b.sosedi.add(a);
		}
	}
	public void odstraniPovezavo (Tocka a, Tocka b) {
		if (povezava(a, b)) {
			a.sosedi.remove(b);
			b.sosedi.remove(a);
		}
	}
	public void odstraniTocko (Tocka a) {
		for (Tocka x : a.sosedi) {
			x.sosedi.remove(a);
		}
		tocke.remove(a.toString());
		
	}
	private Tocka[] dodajTocke (int n) {
		Tocka[] result = new Tocka[n];
		for (int i=0; i<n; i++) {
			result[i] = dodajTocko();
		}
		return result;
	}
	static Graf prazen (int n) {
		Graf result = new Graf();
		result.dodajTocke(n);
		return result;
	}
	static Graf cikel (int n) {
		Graf result = Graf.prazen(n);
		for (int i=0; i<n; i++) {
			result.dodajPovezavo(result.tocka(Integer.toString(i)), result.tocka(Integer.toString((i+1)%n)));
		}
		return result;
	}
	static Graf poln (int n) {
		Graf result = new Graf();
		for (int i=0; i<n; i++) {
			Tocka nova = result.dodajTocko();
			for (Tocka x : result.tocke.values()) {
				result.dodajPovezavo(nova, x);
			}
		}
		return result;
	}
	static Graf polnDvodelen (int n, int m) {
		Graf result = Graf.poln(n);
		Tocka[] nove = result.dodajTocke(m);
		for (Tocka x : nove) {
			for (Tocka y: nove) {
				result.dodajPovezavo(x, y);
			}
		}
		return result;
	}
	public void izpis () {
		System.out.println("Tocke:     Povezave:");
		for (Tocka x : tocke.values()) {
			String tabX = "";
			for (int i=0; i<(11 - x.toString().length()); i++) {
				tabX += " ";
			}
			System.out.print(x + tabX);
			for (Tocka y : x.sosedi) {
				String tabY = "";
				for (int i=0; i<(5 - x.toString().length()); i++) {
					tabY += " ";
				}
				System.out.print(y.toString() + tabY);
			}
			System.out.println();
		}
	}
	public void razporedi (int x, int y, int r) {
		int i = 0;
		final int num = tocke.size();
		for (Tocka T0 : tocke.values()) {
			double fi = 2*Math.PI*i/num;
			T0.x = (int) Math.round(x + r*Math.cos(fi));
			T0.y = (int) Math.round(y + r*Math.sin(fi));
			i++;
		}
	}
	public String toString () {
		String a = new String(); //"";
		int i = 0;
		for (Tocka T : tocke.values()) {
			a += T.toString()+": "+Integer.toString(T.x)+" "+Integer.toString(T.y)+'\n';
		}
		a +="***\n";//;  '\*'+'\*'+'\*'+'\n'
		for (Tocka Tx : tocke.values()) {
			i++;
			a += Tx.toString()+":"; //+Integer.toString(T.x)+" "+Integer.toString(T.y);
			for (Tocka Ty : Tx.sosedi) {
				a += " "+Ty.toString();
			}
			if (i < tocke.size())  {
				a += '\n';
			}
		}
		return a;
	}

}