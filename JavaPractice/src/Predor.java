import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Predor {

	static final int vmax = 80;  // 80 km/h
	static final float l = 622;  // 622 m
	static final float tmin = (l / vmax) * (float) 3.6;
	static final DecimalFormat df = new DecimalFormat("#.##");

	public static void main(String[] args) {
		final String in = "predor.txt";
		final String out = "predorKrsitelji.txt";
		int result = BeriRacunajPisi(in, out);
		System.out.println(result);
	}

	public static int BeriRacunajPisi (String inFilePath, String outFilePath) {
		
		int count = 0;
		try (
				FileReader in = new FileReader(inFilePath);
				FileWriter out = new FileWriter(outFilePath);) {
			int c;
			int _timeIn = 0;
			int _timeOut = 0;
			int _count = 0;
			float _v = 0;
			boolean _tooFast = false;
			while ((c = in.read()) != -1) {
				char x = (char) c;
				if (x != '\n') {
					if (x != ' ') {
						if (_count == 0) {
							_timeIn = 10*_timeIn + (x - '0');
						} else if (_count == 1) {
							_timeOut = 10*_timeOut + (x - '0');
						} else if (_tooFast) {
							// pisi (registrsko) v file
							out.write((int) c);
						}
					} else {  // smo na presledku
						_count++;
						if (_count == 2) {
							int time = _timeOut - _timeIn;
							if (time < tmin) {
								_tooFast = true;
								_v = l / time;
							}
						}
					}
				} else {  // smo na \n
					if (_tooFast) {
						count++;
						// napiši v <out> še povprečno hitrost na dve dec natančno in \n
						String vAverage = " " + df.format(_v*3.6) + '\n';
						out.write(vAverage);
						_tooFast = false;
					}
					_count = 0;
					_timeIn = 0;
					_timeOut = 0;
					_v = 0;
				}
			}
		} catch (IOException exn) {
			exn.printStackTrace();
		}
		return count;
		}
	}
