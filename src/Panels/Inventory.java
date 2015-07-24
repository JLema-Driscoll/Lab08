package Panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ItemObjects.GameObject;
import Lab08.Lab08;

/**
 * makes the invetory
 * 
 * @author Jeremy Driscoll
 * 
 */
public class Inventory extends JPanel implements ActionListener {

	/**
	 * meep
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * makes the drop buttons
	 */
	JButton[] dropper;
	/**
	 * makes the information lables
	 */
	JLabel[] itemInformation;
	/**
	 * holds the lab
	 */
	Lab08 lab;
	/**
	 * holds the string for the labels
	 */
	String[] ITEM_INFORNATION_STR = { "No Item", "No Item", "No Item",
			"No Item", "No Item" };

	/**
	 * holds the string that says drop :D
	 */
	String BUTTON_STR = "Drop";
	/**
	 * holds the GAME WORLD!!!!!!!!!
	 */
	GameWorld world;
	/**
	 * counts the items to place them in the array right
	 */
	private int itemCounter = 0;

	/**
	 * constructs the account
	 * 
	 * @param labz
	 *            sends in the lab 08
	 */
	public Inventory(Lab08 labz) {
		lab = labz;
		setLayout(new GridLayout(5, 2));
		dropper = new JButton[5];
		itemInformation = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			itemInformation[i] = new JLabel(ITEM_INFORNATION_STR[i]);
			add(itemInformation[i]);
			dropper[i] = new JButton(BUTTON_STR);
			dropper[i].addActionListener(this);
			add(dropper[i]);
		}

	}

	/**
	 * imports the world
	 * 
	 * @param z
	 *            the world
	 */
	public void importGameWorld(GameWorld z) {
		world = z;
	}

	/**
	 * sets up the users inventory
	 * 
	 * @param itemz
	 *            sends in teh new item to add
	 */
	public void setUserInventoryItems(GameObject itemz) {
		for (int i = itemInformation.length - 1; i >= 0; i--) {
			if (itemInformation[i].getText().equals("No Item")) {
				itemCounter = i;
			}
		}
		itemInformation[itemCounter].setText(itemz.lookAt());

	}

	/**
	 * drops the selected item
	 */
	public void resetUserItemsAfterDrop() {
		int tempCounter = 0;
		for (int i = 0; i < itemInformation.length; i++) {
			if (!itemInformation[i].getText().equals("No Item")) {
				itemInformation[tempCounter].setText(itemInformation[i]
						.getText());

				if (tempCounter != i) {
					itemInformation[i].setText("No Item");
				}
				tempCounter++;
			}
		}
	}

	/**
	 * gets if a button is hit to drop an item
	 */
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		for (int i = 0; i < dropper.length; i++) {
			if (obj == dropper[i]
					&& !itemInformation[i].getText().equals("No Item")) {
				itemInformation[i].setText("No Item");
				resetUserItemsAfterDrop();
				world.getAllCharacters()[0].dropUpItem(i);
			}
		}
	}

}
