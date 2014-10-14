package ee.ut.math.tvt.team1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class Intro {
	private static final Logger log = Logger.getLogger(Intro.class);
	
	public static void main(String[] args) {
		ConsoleAppender ca = new ConsoleAppender();
		ca.setWriter(new OutputStreamWriter(System.out));
		ca.setLayout(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %c{1} - %m%n"));
		log.addAppender(ca);
		
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
		log.info("Intro window is opened");
	}
}