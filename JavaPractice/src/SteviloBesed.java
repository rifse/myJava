import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SteviloBesed {

	public static void main(String[] args) {
		/*Sestavi funkcijo, ki sprejme ime datoteke in izpiše število besed v datoteki. 
		Besede so poljubna zaporedja znakov, ločena z enim ali več presledki. 
		Presledki so lahko tudi na začetku in na koncu vrstic. 
		Funkcijo nato dopolni, da bo na izhodno datoteko, katere ime tudi dobi kot parameter, 
		še izpisala vse besede, vsako v svojo vrsto.*/
		String in = "src/inSteviloBesed.txt";
		String out = "outSteviloBesed.txt";
		BeriStejPisi(in, out);
	}

	public static void BeriStejPisi (String inFilePath, String outFilePath) {
		
		try (
				FileReader in = new FileReader(inFilePath);
				FileWriter out = new FileWriter(outFilePath);) {
			int c;
			int count = 0;
			boolean space = false;
			while ((c = in.read()) != -1) {
				if (Character.isLetter(c)) {
					// if c is a letter write to output file
					// and flag that space is needed after
					out.write(c);
					space = true;
				} else if (space) {
					// if c is not a letter then write a
					// space only if space=true
					out.write((int) '\n');  // ' ' was before
					// After one space we do not neew another
					space = false;
					count++;// count
				}
			}
			System.out.println(count);
		} catch (IOException exn) {
			exn.printStackTrace();
		}
	}

}
