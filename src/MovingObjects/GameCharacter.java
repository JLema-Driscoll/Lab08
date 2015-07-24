package MovingObjects;

import java.awt.Graphics;
import java.awt.Point;

import ItemObjects.GameObject;
import Panels.GameWorld;

/**
 * CREATES the game character objects
 * 
 * @author Jeremy Driscoll
 * 
 */
public abstract class GameCharacter {

	/**
	 * holds the row and column
	 */
	private int row, column;
	/**
	 * holds in the number of columns and rows
	 */
	private int numberOfRows, numberOfColumns;
	/**
	 * stores the game world does anyone read this
	 */
	private GameWorld world;
	/**
	 * stores the return statement
	 */
	private String returnStatement;
	/**
	 * stores the name
	 */
	private String name;
	/**
	 * stores the sight that the object mainly used for the user
	 */
	private boolean sightOrExploredArea[][];
	/**
	 * stores the objects that are in the inventory of the object
	 */
	private GameObject[] inventory = new GameObject[5];
	/**
	 * counts the number of items
	 */
	private int itemCounter = 0;
	/**
	 * holds the cahracter type
	 */
	private int characterType;

	/**
	 * constuctor for game peoplez
	 * 
	 * @param rowz
	 *            sends in the row
	 * @param columnz
	 *            sends in the column
	 * @param numberOfRowz
	 *            sends in the total number of rows
	 * @param numberOfColumnz
	 *            sends in the total number of columns
	 * @param namez
	 *            sends in the nameeeee
	 * @param worldz
	 *            sends in the world
	 * @param characterTypez
	 *            sends in the Type Of CharacTer
	 */
	public GameCharacter(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, String namez, GameWorld worldz,
			int characterTypez) {
		row = rowz;
		column = columnz;
		numberOfRows = numberOfRowz;
		numberOfColumns = numberOfColumnz;
		world = worldz;
		name = namez;
		returnStatement = "";
		sightOrExploredArea = new boolean[numberOfRows][numberOfColumns];
		characterType = characterTypez;

	}

	/**
	 * constructs the statement used to save and load the file
	 * 
	 * @return returns the statement used to save and load and yeah.....
	 */
	public String printOutStatement() {
		String toRet = "";
		toRet = row + " " + column + "!" + characterType + "?" + name;

		return toRet;
	}

	/**
	 * THIS drops a item from the inventory
	 * 
	 * @param placeItemIs
	 *            sends in the place in the inventory that is dropped
	 */
	public void dropUpItem(int placeItemIs) {
		int tempCounter = 0;
		GameObject[] temp = inventory;
		inventory[placeItemIs].setIsPickedUp(false);
		inventory[placeItemIs].setColumn(getColumn());
		inventory[placeItemIs].setRow(getRow());
		inventory = new GameObject[5];
		for (int i = 0; i < temp.length; i++) {

			if (i != placeItemIs) {
				inventory[tempCounter] = temp[i];
				tempCounter++;

			}
		}
		itemCounter--;
	}

	/**
	 * puts another item into the inventory
	 * 
	 * @param setter
	 *            sends in the item
	 */
	public void setInventory(GameObject setter) {
		inventory[itemCounter] = setter;
		itemCounter++;
	}

	/**
	 * returns the inventory of the game object
	 * 
	 * @return returns the inventory of the mob
	 */
	public GameObject[] getInventory() {
		return inventory;
	}

	/**
	 * returns the number of items in the array
	 * 
	 * @return returns the number of items in the array
	 */
	public int getItemCounter() {
		return itemCounter;
	}

	/**
	 * finds what is seen /explored
	 * 
	 * @return returns what is seen/explored
	 */
	public boolean[][] getSightOrExploredArea() {
		return sightOrExploredArea;
	}

	/**
	 * sets what is seen
	 * 
	 * @param sight
	 *            sends in what is seen
	 */
	public void setSightOrExploredArea(boolean sight[][]) {
		sightOrExploredArea = sight;
	}

	/**
	 * gets the gameworld
	 * 
	 * @return returns the world
	 */
	public GameWorld getGameWorld() {
		return world;
	}

	/**
	 * gets the row
	 * 
	 * @return returns the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * gets the column
	 * 
	 * @return returns the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * sets the row
	 * 
	 * @param rowz
	 *            sends in what the row should look like
	 */
	public void setRow(int rowz) {
		row = rowz;
	}

	/**
	 * sets the column
	 * 
	 * @param colz
	 *            sends in the column
	 */
	public void setColumn(int colz) {
		column = colz;
	}

	/**
	 * returns the number of rows and columns
	 * 
	 * @return returns a point that holds the rows and columns
	 */
	public Point getNumberOfRowsAndColumns() {
		Point z = new Point(numberOfRows, numberOfColumns);
		return z;
	}

	/**
	 * gets the name
	 * 
	 * @return returns the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the return statement
	 * 
	 * @param newStatement
	 *            sends in what to set the statement to
	 */
	public void setReturnStatement(String newStatement) {
		returnStatement = newStatement;
	}

	/**
	 * gets the return statement
	 * 
	 * @return returns the returnstatement
	 */
	public String getReturnStatement() {
		return returnStatement;
	}

	/**
	 * how items are picked up
	 */
	public abstract void pickUpItems();

	/**
	 * draws the object
	 * 
	 * @param g
	 *            sends in the graphical things
	 */
	public abstract void draw(Graphics g);

	/**
	 * sets what the objects sees/ if it is seen
	 */
	public abstract void sight();

	/**
	 * sets how the object is moved
	 * 
	 * @param movement
	 *            sends in what the command to move is (only used for the user)
	 */
	public abstract void howObjectMoves(String movement);

	/**
	 * sends in if the edit mode is on
	 * 
	 * @param editModez
	 *            sends in if the edit mode is on
	 */
	public abstract void setEditMode(boolean editModez);

	/**
	 * lookat them and gets information about them (sometimes not really used)
	 */
	public abstract void lookAt();

	/**
	 * actaully moves the object
	 * 
	 * @param movement
	 *            sends in where the object will moved
	 */
	public void move(String movement) {
		int boundX = getNumberOfRowsAndColumns().y - 1;
		int boundY = getNumberOfRowsAndColumns().x - 1;

		// Deals with the barbarian going North
		if (movement.equals("n")) {
			// If you try to go to far North it will prevent you
			if (getRow() - 1 < 0) {
				setReturnStatement(getName()
						+ " can't go north or he will be eaten by aliens");
			}
			// If you can go farther North then it moves the barbarian and tells
			// you that he moved where he is
			// and if he encountered anything
			else

			{
				setRow(getRow() - 1);

				setReturnStatement(getName() + " has moved farther North. "
						+ "(" + getRow() + "," + getColumn() + ")"
						+ getGameWorld().getSpaceInfo());

			}

		}
		// Same as above but with East
		else if (movement.equals("e")) {

			if (getColumn() + 1 > boundX) {
				setReturnStatement(getName()
						+ " can't go East or he will fall off of space (don't question this)");
			} else {

				setColumn(getColumn() + 1);
				setReturnStatement(getName() + " has moved farther East. "
						+ "(" + getRow() + "," + getColumn() + ")"
						+ getGameWorld().getSpaceInfo());
			}
		}
		// South
		else if (movement.equals("s")) {

			if (getRow() + 1 > boundY) {
				setReturnStatement(getName()
						+ " can't go South or he will run into a wall of invisable space stuff");
			} else {
				setRow(getRow() + 1);
				setReturnStatement(getName() + " has moved farther South. "
						+ "(" + getRow() + "," + getColumn() + ")"
						+ getGameWorld().getSpaceInfo());
			}

		}
		// West
		else if (movement.equals("w")) {

			if (getColumn() - 1 < 0) {
				setReturnStatement(getName()
						+ " can't go West because he doesn't feel like going that way anymore");
			} else {
				setColumn(getColumn() - 1);
				setReturnStatement(getName() + " has moved farther West. "
						+ "(" + getRow() + "," + getColumn() + ")"
						+ getGameWorld().getSpaceInfo());
			}

		}
		pickUpItems();
	}

}
