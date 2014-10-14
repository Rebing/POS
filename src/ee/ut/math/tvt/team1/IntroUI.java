package ee.ut.math.tvt.team1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//Class for team intro UI
public class IntroUI {
	//Components for the UI
	String team_name, team_leader, leader_email;
	String[] members;
	ImageIcon logo;
	String version;
	
	public IntroUI(Properties prop) {
		//Assign the info to the required variables
		team_name = prop.getProperty("teamName");
		team_leader = prop.getProperty("teamLeader");
		leader_email = prop.getProperty("leaderEmail");
		members = prop.getProperty("teamMembers").split(",");
		
		logo = new ImageIcon("smiley.jpg");
		
		version = prop.getProperty("build.number");
	}
	
	//Creates the UI
	public void init() {
		JFrame frame = new JFrame("Intro");
		
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
		frame.getContentPane().add(big_panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocation(300, 300);
		frame.setVisible(true);
	}

}