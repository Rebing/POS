package ee.ut.math.tvt.team1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.controller.impl.SalesDomainControllerImpl;
import ee.ut.math.tvt.salessystem.ui.ConsoleUI;
import ee.ut.math.tvt.salessystem.ui.SalesSystemUI;

public class Intro {
	private static final Logger log = Logger.getLogger(Intro.class);
	private static final String MODE = "console";

	
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
		
		final SalesDomainController domainController = new SalesDomainControllerImpl();

		if (args.length == 1 && args[0].equals(MODE)) {
			log.debug("Mode: " + MODE);

			ConsoleUI cui = new ConsoleUI(domainController);
			cui.run();
		} else {

			IntroUI introUI = new IntroUI(prop);
			introUI.init();
			
			log.info("Intro window is opened");

			final SalesSystemUI ui = new SalesSystemUI(domainController);
			ui.setVisible(true);

			introUI.frame.setAlwaysOnTop(false);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			introUI.frame.setVisible(false);
		}
		
	}
}