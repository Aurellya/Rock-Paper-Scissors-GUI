
import javax.swing.JFrame;
import javax.swing.JButton;				
import javax.swing.JLabel;			
import javax.swing.JTextArea;
import javax.swing.JTextField;			
import javax.swing.Icon;				
import javax.swing.ImageIcon;			
import javax.swing.SwingConstants;		
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;				
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Random; 


class Game extends JFrame {

	private JLabel label;
	private Font font;
	private JTextField yourScore;
	private JTextField compScore;
	private int yourCurrentScore;
	private int compCurrentScore;
	private JTextArea frozenTextArea;

	// Array of button
	private final JButton [] jbArray = new JButton [3];

	// Array of values
	private final String [] values = {"rock", "paper", "scissors"}; 
	
	public Game() {

		super("Rock Paper Scissors GUI");
		setLayout(new FlowLayout());

		label = new JLabel("Welcome To Rock Paper Scissors Game!", SwingConstants.CENTER);
		font = new Font("Courier", Font.BOLD,30);
		label.setFont(font);
		add(label);

		Icon ic;

		// Add buttons 
		for (int i = 0; i < values.length; i++){
			ic = new ImageIcon ("Pictures/" + values[i] + ".png");

			jbArray [i] = new JButton (ic);
			jbArray [i].setToolTipText(values[i]);
			jbArray [i].setBackground (Color.YELLOW);
			
			add (jbArray [i]);
		}

		// Add label
		label = new JLabel("Choose one of the option above!", SwingConstants.CENTER);
		font = new Font("Courier", Font.BOLD,26);
		label.setFont(font);
		add(label);

		// Add Horizontal barrier
		frozenTextArea = new JTextArea(1,55);
		frozenTextArea.setEditable(false);
		frozenTextArea.setBackground(Color.BLACK);
		add(frozenTextArea);

		// Add Text field to display Player's Score 
		font = new Font("Arial", Font.BOLD,16);
		label = new JLabel ("Your Score: ");
		label.setForeground(Color.RED);
		label.setFont(font);
		yourScore = new JTextField (10);
		yourScore.setEditable (false);

		add (label);
		add (yourScore);

		// Add Text field to display Computer's Score 
		font = new Font("Arial", Font.BOLD,16);
		label = new JLabel ("Computer Score: ");
		label.setFont(font);
		compScore = new JTextField (10);
		compScore.setEditable (false);

		add (label);
		add (compScore);

		// Add rules
		String rules = "<html><br/><br/><br/>Rules: <br/><br/>" +
							"1. Rock Win against Scissors<br/>" +
							"2. Paper Win against Rock<br/>" + 
							"3. Scissors Win against Paper<br/>" +
							"4. This games will end when player or computer get 10 points<br/>" +
							"5. The first to score 10 points win the game. </html>";

		label = new JLabel(rules);
		add(label);


		// Register Event 
		for (int i = 0; i < values.length; i++) 
			jbArray [i].addActionListener(e -> SubmitQuestion(e));
	}

	
	private void SubmitQuestion(ActionEvent e) {

		// Player Choice
	 	String s = "";
		Icon ic = new ImageIcon ();
		int index = 0;

		while (s.equals("")){
			if (e.getSource() == jbArray [index])
				s = "You" + generateStatement(index);
			index ++;
		}

		index = index - 1;

		// Computer Choice
		String s2 = "Computer";
		int x = generateComp();

		s2 = s2 + generateStatement(x);

		String result = s + "\n" + s2 + "\n";
		
		// Winning Condition
		if (index == 0 && x == 2 || index == 1 && x == 0 || index == 2 && x == 1){
			result = result + "YOU WIN!";
			yourCurrentScore++;
			yourScore.setText(String.valueOf(yourCurrentScore));
		} 

		else if (index == 2 && x == 0 || index == 0 && x == 1 || index == 1 && x == 2){
			result = result + "COMPUTER WIN!";
			compCurrentScore++;
			compScore.setText(String.valueOf(compCurrentScore));
		}

		else {
			result = result + "DRAW!";
		}

		JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE);

		// Overall Winning Condition
		if (yourCurrentScore == 10 || compCurrentScore == 10){
			if (yourCurrentScore == 10){
				result = "CONGRATULATIONS!!! \nYOU WIN!";
				ic = new ImageIcon("Pictures/win.jpg");
				JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE, ic);
			}

			else if (compCurrentScore == 10){
				result = "YOU LOSE! \nBetter Luck Next Time!";
				ic = new ImageIcon("Pictures/lose.jpg");
				JOptionPane.showMessageDialog(null, result, "Result", JOptionPane.INFORMATION_MESSAGE, ic);
			}

			yourCurrentScore = 0;
			yourScore.setText(String.valueOf(yourCurrentScore));
			compCurrentScore = 0;
			compScore.setText(String.valueOf(compCurrentScore));
		}
	}

	private int generateComp(){  
		
		Random ran = new Random(); 
		int x = ran.nextInt(3);

		return x;
	}

	private String generateStatement(int x){

		switch(x){
			case 0: 
				return " Choose Rock";
			case 1:
				return " Choose Paper";
			case 2:
				return " Choose Scissors";
			default:
				return " Choose Scissors";
		}
	}
}

class RockPaperScissorsGUI {

	public static void main(String[] args) {

		Game game = new Game();
		
		game.setSize(750, 450);
		game.setVisible(true);
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}