package Panels;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Lab08.Lab08;

/**
 * makes the user commands send in
 * 
 * @author Jeremy Driscoll
 * 
 */
public class UserCommandsSendIn extends JPanel implements KeyListener {

	/**
	 * blahhhhrg
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * holds the lab 8
	 */
	Lab08 world;
	/**
	 * holds the game world
	 */
	GameWorld realWorld;

	/**
	 * holds the text box that is typed in
	 */
	JTextField text = new JTextField("Insert Commands Here");
	/**
	 * holds the label where things are printed out to
	 */
	JLabel whatHappened = new JLabel("Prints out what happens here");

	/**
	 * sets up the command thing
	 * 
	 * @param worldz
	 * @param realWorldz
	 */
	public UserCommandsSendIn(Lab08 worldz, GameWorld realWorldz) {
		setLayout(new GridLayout(2, 1));
		world = worldz;
		realWorld = realWorldz;
		setJTextField();

		text.setEditable(true);
		add(whatHappened);
		add(text);

	}

	/**
	 * sets up the jtext field
	 */
	public void setJTextField() {
		text.setText("Insert Commands Here");
		text.addKeyListener(this);
		repaint();
	}

	/**
	 * sends back the commands that the user writes in
	 * 
	 * @return returns the commands
	 */
	public String sendBackCommand() {
		String textback = text.getText();
		return textback;

	}

	/**
	 * gets if the user hits enter or not and then sends in what the user
	 * commands
	 */
	public void keyPressed(KeyEvent e) {
		// got from http://www.rgagnon.com/javadetails/java-0253.html the keey
		// part
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_ENTER) {

			realWorld.commandSend(sendBackCommand());
			whatHappened.setText(realWorld.getSpaceInfo());
			realWorld.allCharacters[0].setReturnStatement("");
			text.setText("");

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
