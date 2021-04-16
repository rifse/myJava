import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class Okno  extends JFrame implements ActionListener {

	protected Platno platno;
	
	private JMenu grafi, barve, rwx; // read write exit
	private JMenuItem prazen, cikel, poln, dvodelen;
	private JMenuItem barvaTocke, barvaAktivneTocke, barvaOznaceneTocke, barvaRoba, debelinaRoba, barvaPovezav;
	private JMenuItem nalozi, shrani, izhod;

	private final JFileChooser FC = new JFileChooser();

	public Okno (int width, int height) {
		setTitle("Graf");

		JMenuBar vrsticazMenujem = new JMenuBar();
		grafi = new JMenu("Grafi");
		prazen = new JMenuItem("Prazen graf");
		grafi.add(prazen);
		cikel = new JMenuItem("Cikel");
		grafi.add(cikel);
		cikel.addActionListener(this);
		poln = new JMenuItem("Poln");
		grafi.add(poln);
		poln.addActionListener(this);
		dvodelen = new JMenuItem("Dvodelen poln");
		grafi.add(dvodelen);
		dvodelen.addActionListener(this);
		vrsticazMenujem.add(grafi);

		barve = new JMenu("Barve");

		barvaTocke = new JMenuItem("Barva točke");
		barve.add(barvaTocke);
		barvaTocke.addActionListener(this);

		barvaAktivneTocke = new JMenuItem("Barva aktivne točke");
		barve.add(barvaAktivneTocke);
		barvaAktivneTocke.addActionListener(this);

		barvaOznaceneTocke = new JMenuItem("Barva označene točke");
		barve.add(barvaOznaceneTocke);
		barvaOznaceneTocke.addActionListener(this);

		barvaRoba = new JMenuItem("Barva roba točke");
		barve.add(barvaRoba);
		barvaRoba.addActionListener(this);

		debelinaRoba = new JMenuItem("Debelina roba točke");
		barve.add(debelinaRoba);
		debelinaRoba.addActionListener(this);
		barvaPovezav = new JMenuItem("Barva povezave");
		barve.add(barvaPovezav);
		barvaPovezav.addActionListener(this);
		vrsticazMenujem.add(barve);

		rwx = new JMenu("Drugo");
		nalozi = new JMenuItem("Naloži");
		rwx.add(nalozi);
		nalozi.addActionListener(this);
		shrani = new JMenuItem("Shrani");
		rwx.add(shrani);
		shrani.addActionListener(this);
		izhod = new JMenuItem("Zapri to žalost");
		rwx.add(izhod);
		izhod.addActionListener(this);

		vrsticazMenujem.add(rwx);
		this.setJMenuBar(vrsticazMenujem);

		JPanel glavnaPlosca = new JPanel();
		this.add(glavnaPlosca);

		platno = new Platno(width, height);
		this.add(platno);
	}
	public void actionPerformed (ActionEvent event) {
		Object source = event.getSource();
		if (source == prazen) {
			String input = (String) JOptionPane.showInputDialog(
					this, 
					"Koliko točk naj vsebuje?", 
					"Nov prazen graf", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					null, 
					null);
			int oi = (int) JOptionPane.showConfirmDialog(
					this, 
					"Ste prepričani, da želite zavreči trenutni graf?", 
					"Popolnoma prepričani?", 
					JOptionPane.YES_NO_OPTION);
			if (oi == 0) {
				int n = Integer.parseInt(input);
				platno.nastaviGraf(Graf.prazen(n));
				platno.graf.razporedi(375, 375, 275);
			}
		} else if (source == poln) {
			String input = (String) JOptionPane.showInputDialog(
					this, 
					"Koliko točk naj vsebuje?", 
					"Nov prazen graf", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					null, 
					null);
			int oi = (int) JOptionPane.showConfirmDialog(
					this, 
					"Ste prepričani, da želite zavreči trenutni graf?", 
					"Popolnoma prepričani?", 
					JOptionPane.YES_NO_OPTION);
			if (oi == 0) {
				int n = Integer.parseInt(input);
				platno.nastaviGraf(Graf.poln(n));
				platno.graf.razporedi(375, 375, 275);
			}
		} else if (source == cikel) {
			String input = (String) JOptionPane.showInputDialog(
					this, 
					"Koliko točk naj vsebuje?", 
					"Nov cikel", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					null, 
					null);
			int oi = (int) JOptionPane.showConfirmDialog(
					this, 
					"Ste prepričani, da želite zavreči trenutni graf?", 
					"Popolnoma prepričani?", 
					JOptionPane.YES_NO_OPTION);
			if (oi == 0) {
				int n = Integer.parseInt(input);
				platno.nastaviGraf(Graf.cikel(n)); 
				platno.graf.razporedi(375, 375, 275);
			}
		} else if (source == dvodelen) {
			String input1 = (String) JOptionPane.showInputDialog(
					this, 
					"Izdelava dvodelnega polnega grafa zahteva vnos dveh številk\nPrva številka:", 
					"Nov dvodelen poln graf 1/2", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					null, 
					null);
			String input2 = (String) JOptionPane.showInputDialog(
					this, 
					"Druga številka:", 
					"Nov dvodelen poln graf 2/2", 
					JOptionPane.PLAIN_MESSAGE, 
					null, 
					null, 
					null);
			int oi = (int) JOptionPane.showConfirmDialog(
					this, 
					"Ste prepričani, da želite zavreči trenutni graf?", 
					"Popolnoma prepričani?", 
					JOptionPane.YES_NO_OPTION);
			if (oi == 0) {
				int n = Integer.parseInt(input1);
				int m = Integer.parseInt(input2);
				platno.nastaviGraf(Graf.polnDvodelen(n, m));
				platno.graf.razporedi(375, 375, 275);
			}
		} else if (source == barvaTocke) {
			Color novaBarva = JColorChooser.showDialog(this, "Izberite barvo točke", platno.barvaTocke);
			if (novaBarva != null) {
				platno.barvaTocke = novaBarva;
			}
		} else if (source == barvaAktivneTocke) {
			Color novaBarva = JColorChooser.showDialog(this, "Izberite barvo aktivne točke", platno.barvaAktivneTocke);
			if (novaBarva != null) {
				platno.barvaAktivneTocke = novaBarva;
			}
		} else if (source == barvaOznaceneTocke) {
			Color novaBarva = JColorChooser.showDialog(this, "Izberite barvo označenih točk", platno.barvaOznacenihTock);
			if (novaBarva != null) {
				platno.barvaOznacenihTock = novaBarva;
			}
		} else if (source == barvaRoba) {
			Color novaBarva = JColorChooser.showDialog(this, "Izberite barvo roba točke", platno.barvaRoba);
			if (novaBarva != null) {
				platno.setBarvaRoba(novaBarva);
			}
		} else if (source == debelinaRoba) {
			Integer[] possibilities = {0, 1, 2, 3};
			platno.debelinaRoba = (int) JOptionPane.showInputDialog(this, "Izberite debelino roba točke", "Debelina roba", 
																  	JOptionPane.QUESTION_MESSAGE, null, possibilities, 
																  	platno.debelinaRoba);
		} else if (source == barvaPovezav) {
			Color novaBarva = JColorChooser.showDialog(this, "Izberite barvo povezav", platno.barvaPovezave);
			
			if (novaBarva != null) {
				platno.barvaPovezave = novaBarva;
			}
		} else if (source == shrani) {
			int returnVal = FC.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = FC.getSelectedFile();
				try (
						FileOutputStream fileOut = new FileOutputStream(file); 
						) {
					String s = platno.graf.toString();
					for (int i=0; i<s.length(); i++) {
						fileOut.write((int) s.charAt(i));
					}
				} catch (IOException exn) {
					exn.printStackTrace();
				}
			}
		} else if (source == nalozi) {
			int returnVal = FC.showOpenDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = FC.getSelectedFile();
				try (FileReader f = new FileReader(file)) {
					int c;
					boolean up = true;
					String x = "";
					Graf g = new Graf();
					while ((c = f.read()) != -1) {
						char a = (char) c;
						if (a != '\n') {
							x += a;
						} else if (up) {
							String[] imeXY = parseUpperLine(x);
							String ime = imeXY[0];
							if (ime.equals("***")) {
								up = false;
								x = "";
							} else {
								g.dodajTocko(ime);
								g.tocka(ime).x = Integer.parseInt(imeXY[1]);
								g.tocka(ime).y = Integer.parseInt(imeXY[2]);
								x = "";
							}
						} else {
							Tocka[] sosedi = parseLowerLine(x, g);
							for (int i = 1; i<sosedi.length; i++) {
								g.dodajPovezavo(sosedi[0], sosedi[i]);
							}
							x = "";
						}
					}
					platno.nastaviGraf(g);
				} catch (IOException exn) {
					exn.printStackTrace();
				}
			}
		} else if (source == izhod) {
			int oi = (int) JOptionPane.showConfirmDialog(
					this, 
					"Ste prepričani, da želite zavreči trenutni graf?", 
					"Popolnoma prepričani?", 
					JOptionPane.YES_NO_OPTION);
			if (oi == 0) {
				this.dispose();
			}
		}
	}
	private String[] parseUpperLine (String line) {
		String[] r = {"", "", ""};
		boolean namePassed = false;
		boolean xPassed = false;
		for (int i=0; i<line.length(); i++) {
			char c = line.charAt(i); 
			if (c == ':') {
				namePassed = true;
			} else if (c == ' ') {
				if (line.charAt(i-1) != ':') {
					xPassed = true;
				}
			} else if (!namePassed) {
				r[0] += c;
			} else if (namePassed && !xPassed) {
				r[1] += c;
			} else {
				r[2] += c;
			}
		}
		return r;
	}
	private Tocka[] parseLowerLine (String line, Graf h) {
		HashSet<Tocka> R = new HashSet<Tocka>();
		Tocka K = null;
		String r = "";
		for (int i=0; i<line.length(); i++) {
			char c = line.charAt(i); 
			if (c == ':') {
				K = h.tocka(r);
				r = "";
			}
			else if (c == ' ') {
				if (line.charAt(i-1) != ':') {
					R.add(h.tocka(r));
					r = "";
				}
			} else {
				r += c;
			}
		}
		if (!r.equals(null) && !r.equals("") && !r.equals(" ")) {
			Tocka X = h.tocka(r);
			R.add(X);
		}
		Tocka[] result = new Tocka[R.size()+1];
		result[0] = K;
		int k = 1;
		for (Tocka A : R) {
			result[k] = A;
			k++;
		}
		return result;
	}
	// metode
}