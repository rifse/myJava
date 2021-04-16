//import java.io.Serializable;
import java.util.HashSet;

//public class Tocka implements Serializable {
public class Tocka {
	
//	static final long serialVersionUID = 1L;
	
	protected String ime;
	protected HashSet<Tocka> sosedi;
	protected int x;
	protected int y;
	
	public Tocka (String ime) {
		this.ime = ime;
		sosedi = new HashSet<Tocka>();
		x = 0;
		y = 0;
	}
	public int stopnja () {
		return sosedi.size();
	}
	@Override  //  //??
	public String toString () {
		return this.ime;
	}
}