/*
 * Programmer: Riley Wilson
 * Date: 11/5/2020
 * Purpose: To run a card game of blackjack
 */
// Import the GUI libraries
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class FinalProject {
	/**
	 * MAIN METHOD
	 * This main method starts the GUI and runs the createMainWindow() method.
     * This method should not be changed.
	 */
	public static void main (String [] args) {
		javax.swing.SwingUtilities.invokeLater (new Runnable () {
			public void run () {
				createMainWindow ();
			}
		});
	}


	/**
	 * STATIC VARIABLES AND CONSTANTS
	 * Declare the objects and variables that you want to access across
     * multiple methods.
	 */
	static JLabel [] cards = {
			createScaledImage("AC.png",100, 150),
			createScaledImage("AD.png",100, 150),
			createScaledImage("AH.png",100, 150),
			createScaledImage("AS.png",100, 150),
			
			createScaledImage("2C.png",100, 150),
			createScaledImage("2D.png",100, 150),
			createScaledImage("2H.png",100, 150),
			createScaledImage("2S.png",100, 150),
			
			createScaledImage("3C.png",100, 150),
			createScaledImage("3D.png",100, 150),
			createScaledImage("3H.png",100, 150),
			createScaledImage("3S.png",100, 150),
			
			createScaledImage("4C.png",100, 150),
			createScaledImage("4D.png",100, 150),
			createScaledImage("4H.png",100, 150),
			createScaledImage("4S.png",100, 150),
			
			createScaledImage("5C.png",100, 150),
			createScaledImage("5D.png",100, 150),
			createScaledImage("5H.png",100, 150),
			createScaledImage("5S.png",100, 150),
			
			createScaledImage("6C.png",100, 150),
			createScaledImage("6D.png",100, 150),
			createScaledImage("6H.png",100, 150),
			createScaledImage("6S.png",100, 150),
			
			createScaledImage("7C.png",100, 150),
			createScaledImage("7D.png",100, 150),
			createScaledImage("7H.png",100, 150),
			createScaledImage("7S.png",100, 150),
			
			createScaledImage("8C.png",100, 150),
			createScaledImage("8D.png",100, 150),
			createScaledImage("8H.png",100, 150),
			createScaledImage("8S.png",100, 150),
			
			createScaledImage("9C.png",100, 150),
			createScaledImage("9D.png",100, 150),
			createScaledImage("9H.png",100, 150),
			createScaledImage("9S.png",100, 150),
			
			createScaledImage("10C.png",100, 150),
			createScaledImage("10D.png",100, 150),
			createScaledImage("10H.png",100, 150),
			createScaledImage("10S.png",100, 150),
			
			createScaledImage("JC.png",100, 150),
			createScaledImage("JD.png",100, 150),
			createScaledImage("JH.png",100, 150),
			createScaledImage("JS.png",100, 150),
			
			createScaledImage("QC.png",100, 150),
			createScaledImage("QD.png",100, 150),
			createScaledImage("QH.png",100, 150),
			createScaledImage("QS.png",100, 150),
			
			createScaledImage("KC.png",100, 150),
			createScaledImage("KD.png",100, 150),
			createScaledImage("KH.png",100, 150),
			createScaledImage("KS.png",100, 150),
	};


	/**
	 * CREATE MAIN WINDOW
     * This method is called by the main method to set up the main GUI window.
	 */
	private static void createMainWindow () {
		// Create and set up the window.
		JFrame frame = new JFrame ("Blackjack");
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		frame.setResizable (false);

		
		// The panel that will hold the components in the frame.
		JPanel contentPane = new JPanel ();
		contentPane.setPreferredSize(new Dimension(900, 400));
		
		
		// Make Layout
		contentPane.setLayout(new BorderLayout());
		
		
		// Make right panel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
		rightPanel.setPreferredSize(new Dimension(190, 300));
		rightPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		contentPane.add(rightPanel, BorderLayout.EAST);
		
		
		//Create game title
		JLabel gameTitle = new JLabel("Blackjack");
		rightPanel.add(gameTitle);
		gameTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
	
		//Add Filler
		rightPanel.add(Box.createVerticalGlue());
		
		
		//Make new game button buttons
		JButton newGameButton = new JButton("New Game");
		newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightPanel.add(newGameButton);
		rightPanel.add(Box.createRigidArea(new Dimension(135, 10)));
		
		
		//Make rules button
		JButton rulesButton = new JButton("Rules");
		rulesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		rightPanel.add(rulesButton);
		rightPanel.add(Box.createRigidArea(new Dimension(135, 10)));
		
		
		//Make quit button
		JButton quitButton = new JButton("Quit");
		quitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitButton.addActionListener(new QuitButtonListener());
		rightPanel.add(quitButton);
		rightPanel.add(Box.createRigidArea(new Dimension(135, 10)));
		
		
		//Make table panel
		JLayeredPane tablePanel = new JLayeredPane();
		contentPane.add(tablePanel, BorderLayout.CENTER);
		
		
		//Add table background
		JLabel tableImage = new JLabel(new ImageIcon("green table.jfif"));
		tablePanel.add(tableImage, Integer.valueOf(-300));
		tableImage.setSize(700, 400);
		tableImage.setLocation(0, 0);
		
		
		//Add deck of cards
		JLabel deck = createScaledImage("red_back.png", 80, 130);
		deck.setSize(80, 130);
		deck.setLocation(25, 150);
		
		tablePanel.add(deck, Integer.valueOf(100));
		
		
		// Add the panel to the frame
		frame.setContentPane(contentPane);
		
		
		//size the window.
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}


    /**
     * HELPER METHODS
     * Methods that you create to manage repetitive tasks.
     */
	private static JLabel createScaledImage (String filename, int width, int height) {
		Image originalImage = new ImageIcon(filename).getImage();
		Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		return new JLabel(new ImageIcon(scaledImage));
	}


    /**
     * EVENT LISTENERS
     * Subclasses that handle events (button clicks, mouse clicks and moves,
     * key presses, timer expirations)
     */
	
	   /** Handles clicks on quit button */
    private static class QuitButtonListener implements ActionListener {
        public void actionPerformed (ActionEvent event) {
        	int answer = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", 
        			"Quit?", JOptionPane.YES_NO_OPTION);
        	if (answer == JOptionPane.YES_OPTION) {
            System.exit(0);
        	}
        }
    }
}