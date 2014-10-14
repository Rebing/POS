package ee.ut.math.tvt.team1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Intro {
	public static void main(String[] args) {
		Properties prop = new Properties();
		
		//Gets the application properties file
		try {
			InputStream in = new FileInputStream("application.properties");
			prop.load(in);
			
			in = new FileInputStream("version.properties");
			prop.load(in);
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IntroUI first = new IntroUI(prop);
		first.init();
	}
}