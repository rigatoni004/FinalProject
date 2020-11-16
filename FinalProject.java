/*
 * Programmer: Riley Wilson
 * Date: 11/16/2020
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
	static JLabel userScoreNumber;
	
	static JButton addCard;
	
	static JButton noCard;
	
	static int userScore = 0;
	
	static int userValue = 0;
	
	static int computerValue = 0;

	static JPanel cardPanel2;

	static JPanel cardPanel;

	static JLabel computerScoreNumber;
	
	static int computerScore = 0; 
	
	static JLabel YouWin;
	
	static JLabel YouLose;
	
	static JLabel Draw;

	static int [] cardValue = {
			11,11,11,11,2,2,2,2,3,3,3,3,4,4,4,4, 
			5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,
			9,9,9,9,10,10,10,10,10,10,10,10,
			10,10,10,10,10,10,10,10};

	static int nextCard = 0;

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

	static JFrame frame;


	/**
	 * CREATE MAIN WINDOW
	 * This method is called by the main method to set up the main GUI window.
	 */
	private static void createMainWindow () {
		// Create and set up the window.
		frame = new JFrame ("Blackjack");
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
		newGameButton.addActionListener(new NewGameHandler());
		rightPanel.add(newGameButton);
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
		JLabel deck = createScaledImage("red_back.png", 100, 150);
		deck.setSize(100, 150);
		deck.setLocation(25, 150);
		tablePanel.add(deck, Integer.valueOf(100));

		//Create user card panel
		cardPanel = new JPanel();
		tablePanel.add(cardPanel, Integer.valueOf(501));
		cardPanel.setSize(400, 150);
		cardPanel.setLocation(175, 200);
		cardPanel.setOpaque(false);
		cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.LINE_AXIS));

		//Create random cards(user)
		shuffle();
		dealCard();
		dealCard();

		//Create computer card panel
		cardPanel2 = new JPanel();
		tablePanel.add(cardPanel2, Integer.valueOf(501));
		cardPanel2.setSize(400, 150);
		cardPanel2.setLocation(175, 0);
		cardPanel2.setOpaque(false);
		cardPanel2.setLayout(new BoxLayout(cardPanel2, BoxLayout.LINE_AXIS));

		//Add computer cards
		

		//Add user score
		JLabel userScoreLabel = new JLabel("Score:");
		userScoreLabel.setSize(150, 75);
		userScoreLabel.setLocation(600, 350);
		userScoreLabel.setForeground(Color.WHITE);
		tablePanel.add(userScoreLabel);
		tablePanel.setLayer(userScoreLabel, 200);


		//add user score number
		userScoreNumber = new JLabel("0");
		tablePanel.add(userScoreNumber);
		userScoreNumber.setSize(150, 75);
		userScoreNumber.setLocation(645, 350);
		userScoreNumber.setForeground(Color.WHITE);
		tablePanel.setLayer(userScoreNumber, 200);
		

		//Add computer score
		JLabel computerScoreLabel = new JLabel("Score:");
		tablePanel.add(computerScoreLabel);
		computerScoreLabel.setSize(150, 50);
		computerScoreLabel.setLocation(600, 0);
		computerScoreLabel.setForeground(Color.WHITE);
		tablePanel.setLayer(computerScoreLabel, 201);

		//Add computer score number
		computerScoreNumber = new JLabel("0");
		tablePanel.add(computerScoreNumber);
		computerScoreNumber.setSize(150, 50);
		computerScoreNumber.setLocation(645, 0);
		computerScoreNumber.setForeground(Color.WHITE);
		tablePanel.setLayer(computerScoreNumber, 201);
		
		//Add Win label
		YouWin = new JLabel("You Win!");
		tablePanel.add(YouWin);
		YouWin.setSize(400, 100);
		YouWin.setLocation(300,120);
		YouWin.setForeground(Color.WHITE);
		YouWin.setFont(YouWin.getFont().deriveFont(32.0f));
		tablePanel.setLayer(YouWin, 201);
		YouWin.setVisible(false);
		
		//Add lose label
		YouLose = new JLabel("You Lose!");
		tablePanel.add(YouLose);
		YouLose.setSize(400, 100);
		YouLose.setLocation(300,120);
		YouLose.setForeground(Color.WHITE);
		YouLose.setFont(YouLose.getFont().deriveFont(32.0f));
		tablePanel.setLayer(YouLose, 201);
		YouLose.setVisible(false);

		//Add draw label
		Draw = new JLabel("Draw!");
		tablePanel.add(Draw);
		Draw.setSize(400, 100);
		Draw.setLocation(300,120);
		Draw.setForeground(Color.WHITE);
		Draw.setFont(Draw.getFont().deriveFont(32.0f));
		tablePanel.setLayer(Draw, 201);
		Draw.setVisible(false);
		
		//Create button Panel
		JPanel buttonPanel = new JPanel();
		tablePanel.add(buttonPanel, Integer.valueOf(500));
		buttonPanel.setSize(130, 40);
		buttonPanel.setLocation(300, 355);
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		tablePanel.setLayer(buttonPanel, 405);
		buttonPanel.setOpaque(false);

		//Add card button
		addCard = new JButton("Hit");
		addCard.setPreferredSize(new Dimension(50, 25));
		addCard.addActionListener(new HitButtonHandler());
		addCard.setLocation(100, 0);
		buttonPanel.add(addCard);
		buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));

		//Add no card button
		noCard = new JButton("Stand");
		noCard.setPreferredSize(new Dimension(50, 25));
		noCard.addActionListener(new StayButtonHandler());
		addCard.setLocation(100, 25); 
		buttonPanel.add(noCard);
		buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));



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
	
	/**Randomizes the order of the cards */
	private static void shuffle () {
		Random rand = new Random();
		for (int index = 0; index < cards.length; index++) {
			int randomIndexToSwap = rand.nextInt(cards.length);
			JLabel temp = cards[randomIndexToSwap];
			cards[randomIndexToSwap] = cards[index];
			cards[index] = temp;
			int tempValue = cardValue[randomIndexToSwap];
			cardValue[randomIndexToSwap] = cardValue[index];
			cardValue[index] = tempValue;
		}
		nextCard = 0;
	}

	/**Generates and shuffles random cards*/
	private static void dealCard() {
		cardPanel.add(cards[nextCard]);
		cardPanel.validate(); 
		userValue += cardValue[nextCard];
		nextCard++;
	}
	
	/**Generates and shuffles random cards(computer) */
	private static void computerCard() {
		cardPanel2.add(cards[nextCard]);
		cardPanel2.validate();
		computerValue += cardValue[nextCard];
		nextCard++;
	}
	
	private static void dealerRules() {
		
		while(computerValue < 17) {
			computerCard();
			
			computerScore = Integer.parseInt(computerScoreNumber.getText());
			computerScoreNumber.setText(String.valueOf(computerValue));
		}
		
		winner();
	}
	
	/** Detects who the winner is*/
	private static void winner() {
		if ((userValue > computerValue && userValue <= 21) || (userValue <= 21 && computerValue > 21)) {
			YouWin.setVisible(true);
		}
		
		else if((computerValue > userValue && computerValue <= 21) || userValue > 21) {
			
			YouLose.setVisible(true); 
		}
		
		
		else {
			Draw.setVisible(true);
		}
		
	}
	
	/**
	 * EVENT LISTENERS
	 * Subclasses that handle events (button clicks, mouse clicks and moves,
	 * key presses, timer expirations)
	 */


	/** Handles clicks on add card button */
	private static class HitButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			dealCard();
			
			userScore = Integer.parseInt(userScoreNumber.getText());
        	userScoreNumber.setText(String.valueOf(userValue));
        	
			if (userValue >= 21) {
				addCard.setEnabled(false);
				noCard.setEnabled(false);
				dealerRules();
				
			}
		}
	}
	
	/** Handles clicks on the stay button */
	private static class StayButtonHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			addCard.setEnabled(false);
			noCard.setEnabled(false);
			dealerRules();
			
			
		}
	}

	/**Handles clicks on new game button */
	private static class NewGameHandler implements ActionListener {
		public void actionPerformed (ActionEvent event) {
			userScoreNumber.setText("0");
			computerScoreNumber.setText("0");
			userValue = 0;
			computerValue = 0;
			YouWin.setVisible(false);
			YouLose.setVisible(false);
			Draw.setVisible(false);
			addCard.setEnabled(true);
			noCard.setEnabled(true);
			cardPanel.removeAll();
			cardPanel2.removeAll();
			shuffle();
			dealCard();
			dealCard();
			
		}
	}


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
