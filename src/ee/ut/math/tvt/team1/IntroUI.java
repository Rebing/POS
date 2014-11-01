package ee.ut.math.tvt.team1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Class for team intro UI
public class IntroUI extends JFrame{
	//Components for the UI
	String team_name, team_leader, leader_email;
	String[] members;
	ImageIcon logo;
	String version;

	//The general frame for later referencing as well
	//public JFrame frame = new JFrame("Intro");
	
	public IntroUI(Properties prop) {
		//Assign the info to the required variables
		team_name = prop.getProperty("teamName");
		team_leader = prop.getProperty("teamLeader");
		leader_email = prop.getProperty("leaderEmail");
		members = prop.getProperty("teamMembers").split(",");
		
		logo = new ImageIcon("smiley.jpg");
		
		String minor = prop.getProperty("build.minor.number");
		String major = prop.getProperty("build.major.number");
		String revision = prop.getProperty("build.revision.number");

		version = minor + "."  + major + "." + revision;
	}
	
	//Creates the UI
	public void init() {
		JPanel big_panel = new JPanel(new BorderLayout());
		JPanel left_panel = new JPanel(new GridLayout(0, 1));
		JPanel right_panel = new JPanel(new BorderLayout());
		
		JLabel lName = new JLabel("Team name: " + team_name);
		JLabel lLeader = new JLabel("Team leader: " + team_leader);
		JLabel lEmail = new JLabel("Leader's email: " + leader_email);
		JLabel lVersion = new JLabel("Version: " + version);
		JLabel picture = new JLabel("", logo, JLabel.CENTER);
		
		left_panel.setBackground(Color.white);
		right_panel.setBackground(Color.white);
		
		//Adds the names and picture to the panel
		left_panel.add(lName);
		left_panel.add(lLeader);
		left_panel.add(lEmail);
		for (int i = 0; i < members.length; i++) {
			left_panel.add(new JLabel("Member" + (i+1) + ": " + members[i]));
		}
		left_panel.add(lVersion);
		right_panel.add(picture);
		
		big_panel.add(left_panel, BorderLayout.WEST);
		big_panel.add(right_panel, BorderLayout.CENTER);
		
		//Add the panels to the frame and display the frame
		this.getContentPane().add(big_panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        
        //Sets the frame in the middle of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
        Point newLocation = new Point(middle.x - (this.getWidth() / 2), 
                                      middle.y - (this.getHeight() / 2));
        this.setLocation(newLocation);
        
        this.setAlwaysOnTop(true);
		this.setVisible(true);
	}

}