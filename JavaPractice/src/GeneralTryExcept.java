import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GeneralTryExcept {

		// If not possible try-with-resources?:
		// try (resource-declarations) try-clause
		//	   catch catch-clause finally finally-clause
		public static void main(String[] args) {

			FileReader in = null;
			FileWriter out = null;
			try {
				in = new FileReader("inSteviloBesed.txt");
				out = new FileWriter("outSteviloBesed.txt");
				try {
					int c;
					while ((c= in.read()) != -1) out.write(c);
				} finally {
					out.close(); 
					in.close();
				}
			} catch (IOException exn) {
				exn.printStackTrace();
			}
		}
}