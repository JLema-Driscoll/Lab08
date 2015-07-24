package Panels;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

import Lab08.Lab08;

/**
 * makes the control panel
 * 
 * @author Jeremy Driscoll
 * 
 */
public class ControlPanel implements ActionListener {

	/**
	 * creates the select shape combo box
	 */
	JComboBox selectLandType;

	/**
	 * creates the create or edit toggle
	 */
	JToggleButton createOrEdit;

	/**
	 * Creates the normal buttons
	 * 
	 */
	JButton[] button;
	/**
	 * tells if night move is on or not
	 */
	boolean nightModeIsOn;
	/**
	 * holds the current selected land
	 */
	int currentSelectedLand;
	/**
	 * tells if the barbarian is forgetful
	 */
	boolean forgetfulBarbarianIsOn;
	/**
	 * creates the string that holds all the button names
	 */
	static final String[] BUTTON_STR = { "Populate", "Reset", " Save Map  ",
			" Load Map  " };
	/**
	 * holds the string of the nightmode
	 */
	private String nightMode = "Night Mode";

	/**
	 * button for da night of the modes
	 */
	JToggleButton nightModez;
	/**
	 * string for the forgetful barbarian
	 */
	private String forgetfulBarbarian = "Forgetful Barbarian Mode";

	/**
	 * turns on and off the barbarian
	 */
	JToggleButton forgetfulBarbarianz;
	/**
	 * creates the string of the shape options
	 */
	static final String[] LAND_OPTIONS_STR = { "Forest", "Field", "River",
			"Mountain", "Bog" };
	/**
	 * creates the string for the create and edit button
	 */
	static final String Play_OR_EDIT_STR = "Edit";

	/**
	 * holds if the edit mode is on
	 */
	boolean editMode = true;
	/**
	 * holds the lab 8
	 */
	Lab08 q;
	/**
	 * holds in the world
	 */
	GameWorld world;

	/**
	 * initializes the control panel
	 * 
	 * @param lab
	 *            sends in the world
	 */
	public ControlPanel(Lab08 lab) {
		q = lab;
	}

	/**
	 * creates the control panel
	 * 
	 * @return returns the panel for the controls
	 */
	public JPanel createControls() {
		// gives the control panel space for 22 buttons in a row and creates the
		// panel
		JPanel controlPanel = new JPanel(new GridLayout(10, 1));

		// Creates the jcombo box that is used for the shapes
		selectLandType = new JComboBox(LAND_OPTIONS_STR);
		selectLandType.addActionListener(this);
		selectLandType.setEditable(false);

		// Creates the button for turning on and off create and edit mode
		createOrEdit = new JToggleButton(Play_OR_EDIT_STR);
		createOrEdit.addActionListener(this);

		// puts the buttons into the display
		controlPanel.add(createOrEdit);
		controlPanel.add(selectLandType);

		// Creates the normal buttons
		button = new JButton[BUTTON_STR.length];
		for (int i = 0; i < BUTTON_STR.length; i++) {
			button[i] = new JButton(BUTTON_STR[i]);
			button[i].addActionListener(this);
			controlPanel.add(button[i]);
		}

		nightModez = new JToggleButton(nightMode);
		nightModez.addActionListener(this);
		controlPanel.add(nightModez);
		// returns the created panel to the world

		forgetfulBarbarianz = new JToggleButton(forgetfulBarbarian);
		forgetfulBarbarianz.addActionListener(this);
		controlPanel.add(forgetfulBarbarianz);
		return controlPanel;
	}

	/**
	 * gets the current selected land
	 * 
	 * @return returns the land
	 */
	public int getCurrentSelectedLand() {
		return currentSelectedLand;
	}

	/**
	 * imports in the game world
	 * 
	 * @param z
	 *            gameworld
	 */
	public void importGameWorld(GameWorld z) {
		world = z;
	}

	/**
	 * gets the edit mode
	 * 
	 * @return returns if it is used or not
	 */
	public boolean getEditMode() {
		return editMode;
	}

	/**
	 * gets if night mode is on
	 * 
	 * @return returns if night mode is on or not
	 */
	public boolean getNightModeIsOn() {
		return nightModeIsOn;
	}

	/**
	 * gets if the forgetful barbarian is on
	 * 
	 * @return returns if it is on or not
	 */
	public boolean getForgetfulBarbarianIsOn() {
		return forgetfulBarbarianIsOn;
	}

	/**
	 * gets what happens when a button is hit
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// Gets if it is in play or edit mode and makes the buttons right
		if (createOrEdit.isSelected()) {
			editMode = false;
			button[0].setVisible(false);
			button[1].setVisible(false);
			button[2].setText("Save Game");
			button[3].setText("Load Game");
			createOrEdit.setText("Play");
		} else {
			editMode = true;
			button[0].setVisible(true);
			button[1].setVisible(true);
			button[2].setText(" Save Map  ");
			button[3].setText(" Load Map  ");
			createOrEdit.setText("Edit");

		}
		// deals with the buttons
		if (obj == button[0]) {
			q.sendOverPopulate();
		}
		if (obj == button[1]) {
			world.restAll();
		}
		if (obj == button[2] && editMode) {
			world.saveToFile();
		}
		if (obj == button[3] && editMode) {
			world.loadFile();
		}
		if (obj == button[2] && !editMode) {
			world.saveToGameFile();
		}
		if (obj == button[3] && !editMode) {
			world.loadGameFile();
		}
		// toggles
		if (nightModez.isSelected()) {
			nightModeIsOn = true;
		} else {
			nightModeIsOn = false;
		}
		if (forgetfulBarbarianz.isSelected()) {
			forgetfulBarbarianIsOn = true;
		} else {
			forgetfulBarbarianIsOn = false;
		}
		// deals with the land
		String w = (String) selectLandType.getSelectedItem();
		if (w.equals("Forest")) {
			currentSelectedLand = 0;
		} else if (w.equals("Field")) {
			currentSelectedLand = 1;
		} else if (w.equals("River")) {
			currentSelectedLand = 2;
		} else if (w.equals("Mountain")) {
			currentSelectedLand = 3;
		} else {
			currentSelectedLand = 4;
		}
	}
}
