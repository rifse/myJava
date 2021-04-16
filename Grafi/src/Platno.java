import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;

//import javax.swing.DebugGraphics;
import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.BasicStroke;


@SuppressWarnings("serial")
public class Platno extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	protected Graf graf;

	protected Color barvaPovezave = Color.blue;
	protected Color barvaTocke = Color.green;
	protected Color barvaRoba = Color.black;
	private BasicStroke debelinaPovezave = new BasicStroke(2);
	protected int debelinaRoba = 1;
//	private BasicStroke dRoba = new BasicStroke(debelinaRoba);
	private float polmer = 4;
	private int sirina = 750;
	private int visina = 750;

	private Tocka aktivnaTocka;
	protected Color barvaAktivneTocke = Color.red;
	private HashSet<Tocka> mnozicaOznacenihTock = new HashSet<Tocka>();
	protected Color barvaOznacenihTock = Color.white;
	
	private int prejsnjiX, prejsnjiY;

	public Platno (int sirina, int visina) {
		this.setPreferredSize(new Dimension(sirina, visina));
		this.setFocusable(true);
		this.setBackground(Color.white);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		this.graf = new Graf();
	}
	protected void nastaviGraf (Graf g) {
		this.graf = g;
	}
	protected void setBarvaRoba (Color barva) {
		this.barvaRoba = barva;
	}
	@Override
	protected void paintComponent (Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.clearRect(0, 0, sirina, visina);
		HashSet<Tocka> drawn = new HashSet<Tocka>();
		for (Tocka Tx : graf.tocke.values()) {
			g2.setColor(barvaPovezave);
			g2.setStroke(debelinaPovezave);
			for (Tocka Ty : graf.tocke.values()) {
				if (!drawn.contains(Ty) && Tx.sosedi.contains(Ty)) {
					g2.draw(new Line2D.Float(Tx.x, Tx.y, Ty.x, Ty.y));
				}
			}	
			if (Tx.equals(aktivnaTocka) && (barvaAktivneTocke != null)) {
				g2.setColor(barvaAktivneTocke);
			}  else if (mnozicaOznacenihTock.contains(Tx)) {
				g2.setColor(barvaOznacenihTock);
			} else {
				g2.setColor(barvaTocke);
			}
			int x = Tx.x - (int) Math.round(Math.sqrt(2)*polmer);
			int y = Tx.y - (int) Math.round(Math.sqrt(2)*polmer);
			g2.fillOval(x, y, (int) Math.round(2*polmer), (int) Math.round(2*polmer));
			g2.setColor(barvaRoba);
			if (debelinaRoba > 0) {
				g2.setStroke(new BasicStroke(debelinaRoba));
				g2.drawOval(x, y, (int) Math.round(2*polmer), (int) Math.round(2*polmer));
			}
			drawn.add(Tx);
		}
		this.repaint();
	}
	public void mousePressed (MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		for (Tocka t : graf.tocke.values()) {
			if (Math.pow((t.x-x), 2) + Math.pow((t.y-y), 2) < Math.pow(5*polmer/4, 2)) {
				aktivnaTocka = t;
				break;
			}
		}
		prejsnjiX = x;
		prejsnjiY = y;
	}
    public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
    	if (aktivnaTocka != null) {
			aktivnaTocka.x += x - prejsnjiX;
			aktivnaTocka.y += y - prejsnjiY;
    	} else if (!mnozicaOznacenihTock.isEmpty()) {
			for (Tocka T : mnozicaOznacenihTock) {
				T.x += x - prejsnjiX;
				T.y += y - prejsnjiY;
			}
    	}
		prejsnjiX = x;
		prejsnjiY = y;
    }
    public void mouseReleased (MouseEvent event) {
    	if (aktivnaTocka != null) {
    		if (mnozicaOznacenihTock.contains(aktivnaTocka)) {
    			mnozicaOznacenihTock.remove(aktivnaTocka);
    		} else {
    			mnozicaOznacenihTock.add(aktivnaTocka);
    		}
    		aktivnaTocka = null;
    	} else {
    		Tocka T = this.graf.dodajTocko();
    		T.x = prejsnjiX;
    		T.y = prejsnjiY;
    		for (Tocka K : mnozicaOznacenihTock) {
    			this.graf.dodajPovezavo(K, T);
    		}
    	}
    }
	public void keyPressed (KeyEvent event) {
		int key = event.getKeyCode();
		if (key == KeyEvent.VK_A) {
			mnozicaOznacenihTock = new HashSet<Tocka>(this.graf.tocke.values());
		} else if (key == KeyEvent.VK_W) {
			for (Tocka X : mnozicaOznacenihTock) {
				this.graf.odstraniTocko(X);
			}
		} else if (key == KeyEvent.VK_S) {
			for (Tocka X : mnozicaOznacenihTock) {
				for (Tocka Y : mnozicaOznacenihTock) {
					this.graf.odstraniPovezavo(X, Y);
				}
			}
		} else if (key == KeyEvent.VK_D) {
			mnozicaOznacenihTock = new HashSet<Tocka>();
		} else if (key == KeyEvent.VK_N) {
			for (Tocka X : mnozicaOznacenihTock) {
				for (Tocka Y : mnozicaOznacenihTock) {
					this.graf.dodajPovezavo(X, Y);
				}
			}
		}
//		switch (event.getKeyCode()) {
//			case KeyEvent.VK_A:
//				mnozicaOznacenihTock = new HashSet<Tocka>(this.graf.tocke.values());
//				break;
//		}
	}
	public void keyTyped (KeyEvent event) {
	}
	public void keyReleased (KeyEvent event) {
	}
	public void mouseEntered (MouseEvent e) {
	}
	public void mouseExited (MouseEvent e) {
	}
	public void mouseClicked (MouseEvent e) {
	}
	public void mouseMoved (MouseEvent e) {
	}
}