package Panels;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ItemObjects.GameObject;
import Lab08.Lab08;
import MovingObjects.GameCharacter;

/**
 * makes the object picker uperrrrr
 * 
 * @author Jeremy Driscoll
 * 
 */
public class ObjectPickUper extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * makes a new frame
	 */
	JFrame myFrame2;
	/**
	 * stores all the items
	 */
	GameObject[] allItems;
	/**
	 * stores the items that cna be picked up
	 */
	GameObject[] itemsToPickup;
	/**
	 * holds if an object is selected
	 */
	boolean[] isObjectSelected;
	/**
	 * holds the buttons to pick up items
	 */
	JButton[] pickUpItem;
	/**
	 * holds the array if info labels
	 */
	JLabel[] itemInformation;
	/**
	 * holds lab 8
	 */
	Lab08 lab;
	/**
	 * holds the game world
	 */
	GameWorld world;
	/**
	 * holds the array string of descriptions
	 */
	String[] ITEM_INFORNATION_STR = { "No item", "No item", "No item",
			"No item", "No item" };

	/**
	 * makes the pick up button string
	 */
	String BUTTON_STR = "Pick Up";

	/**
	 * Constructor that sends in important things
	 * 
	 * @param theTitle
	 * @param theWidth
	 * @param theHeight
	 * @param items
	 * @param isObjectSelectedz
	 * @param worldz
	 */
	public ObjectPickUper(String theTitle, int theWidth, int theHeight,
			GameObject[] items, boolean[] isObjectSelectedz, GameWorld worldz) {

		// Creates the frame and stuff
		myFrame2 = new JFrame(theTitle) {
			private static final long serialVersionUID = 1L;

			// paints the objects
			public void paint(Graphics g) {
				paintComponents(g);
			}
		};
		world = worldz;
		allItems = items;
		// makes the new window
		itemsToPickup = new GameObject[0];
		isObjectSelected = isObjectSelectedz;
		setLayout(new GridLayout(5, 2));
		pickUpItem = new JButton[5];
		itemInformation = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			itemInformation[i] = new JLabel(ITEM_INFORNATION_STR[i]);
			add(itemInformation[i]);
			pickUpItem[i] = new JButton(BUTTON_STR);
			pickUpItem[i].addActionListener(this);
			add(pickUpItem[i]);
		}
		setFindItemsToPickUp();
		setLabels();
		// sets width and height
		myFrame2.setSize(theWidth, theHeight);
		// Makes the applet have a border layout
		myFrame2.setLayout(new BorderLayout());
		myFrame2.add(this);
		myFrame2.setVisible(true);

	}

	/**
	 * finds the items to be picked up
	 */
	public void setFindItemsToPickUp() {
		int counter = 0;
		for (int i = 0; i < allItems.length; i++) {
			if (isObjectSelected[i]) {
				increaseItemsToPickupLength();
				itemsToPickup[counter] = allItems[i];
				counter++;
			}
		}
	}

	/**
	 * increases the array length of the items picked up
	 */
	public void increaseItemsToPickupLength() {
		GameObject[] temp = itemsToPickup;
		itemsToPickup = new GameObject[itemsToPickup.length + 1];
		for (int i = 0; i < temp.length; i++) {
			itemsToPickup[i] = temp[i];
		}
	}

	/**
	 * sets the labels
	 */
	public void setLabels() {
		for (int i = 0; i < itemsToPickup.length && i < 5; i++) {
			itemInformation[i].setText(itemsToPickup[i].lookAt());
		}
	}

	/**
	 * tells if a button is pushed and picks up an item if it cna
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		for (int i = 0; i < pickUpItem.length; i++) {
			try {
				if (pickUpItem[i] == obj
						&& world.getAllCharacters()[0].getItemCounter() < 5) {

					itemsToPickup[i].setIsPickedUp(true);
					itemsToPickup[i].setColumn(-1);
					itemsToPickup[i].setRow(-1);
					world.setUserInventoryItems(itemsToPickup[i]);
					itemInformation[i].setText("You just picked this up");
				}
			} catch (ArrayIndexOutOfBoundsException r) {

			}
		}

	}
}
