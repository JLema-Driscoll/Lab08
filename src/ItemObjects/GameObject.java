/**
 * 
 */
package ItemObjects;

import java.awt.Graphics;
import java.awt.Point;

import Panels.GameWorld;

/**
 * Game Object
 * 
 * @author Jeremy Driscoll
 * 
 */
public abstract class GameObject {

	/**
	 * holds the row and column of the object
	 */
	private int row, column;
	/**
	 * holds the center panel /game world
	 */
	private GameWorld world;
	/**
	 * holds how much the item is worth
	 */
	private int itemWorth;

	/**
	 * tells if the item can be seen by user and therefore drawn
	 */
	private boolean isSeenByUser = false;
	/**
	 * tells if the bottle has been picked up or not
	 */
	boolean pickedUp = false;
	/**
	 * hold the total number of rows
	 */
	int numberOfRows;
	/**
	 * holds the total number of columns
	 */
	int numberOfColumns;

	/**
	 * constructor for objects
	 * 
	 * @param rowz
	 *            sends in the row the object is at
	 * @param columnz
	 *            sends in the column the object is at
	 * @param numberOfRowz
	 *            sends in the total number of rows
	 * @param numberOfColumnz
	 *            sends in the total number of columns
	 * @param amount
	 *            sends in the item worth
	 * @param worldz
	 *            sends in the world
	 */
	public GameObject(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, int amount, GameWorld worldz) {
		row = rowz;
		column = columnz;
		world = worldz;
		itemWorth = amount;
		numberOfRows = numberOfRowz;
		numberOfColumns = numberOfColumnz;
	}

	/**
	 * constructs the statement that is used to load and save the object
	 * 
	 * @return returns the statement about the object
	 */
	public String printOutStatement() {
		String toRet = "";
		toRet = row + " " + column + "!" + itemWorth + "?" + isSeenByUser;
		return toRet;
	}

	/**
	 * finds out if the object is seen by the user
	 * 
	 * @return returns if the user can see the object
	 */
	public boolean getIsSeenByUser() {
		return isSeenByUser;
	}

	/**
	 * gets the total number of rows and columns
	 * 
	 * @return returns the total number of rows and columns
	 */
	public Point getNumberOfRowsAndColumns() {
		Point z = new Point(numberOfRows, numberOfColumns);
		return z;
	}

	/**
	 * gets the items worth
	 * 
	 * @return returns the items worth
	 */
	public int getItemWorth() {
		return itemWorth;
	}

	/**
	 * sets if the item is picked up or not
	 * 
	 * @param setter
	 *            sends in if the item is picked up or not
	 */
	public void setIsPickedUp(boolean setter) {
		pickedUp = setter;
	}

	/**
	 * gets if the item is picked up or not
	 * 
	 * @return returns if it is or not
	 */
	public boolean getIsPickedUp() {
		return pickedUp;
	}

	/**
	 * gets the gameWorld
	 * 
	 * @return returns the game world for the pizza and bottle class
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
	 * @param setter
	 *            sends in what the row is set as
	 */
	public void setRow(int setter) {
		row = setter;
	}

	/**
	 * sets the column
	 * 
	 * @param setter
	 *            sends in what the column is to be set as
	 */
	public void setColumn(int setter) {
		column = setter;
	}

	/**
	 * finds out if the user can see the item or not
	 */
	public void sight() { // gets the row and column the user is at
		int userColumn = getGameWorld().getAllCharacters()[0].getColumn();
		int userRow = getGameWorld().getAllCharacters()[0].getRow();
		// sees if the item is close enough to be seen
		if (userColumn > getColumn() - 2 && userColumn < getColumn() + 2
				&& userRow > getRow() - 2 && userRow < getRow() + 2) {
			isSeenByUser = true;
		} else {
			isSeenByUser = false;
		}
	}

	/**
	 * used to set if edit mode is on
	 * 
	 * @param editModez
	 *            sends in if it is or not
	 */
	public abstract void setEditMode(boolean editModez);

	/**
	 * looks at the item
	 * 
	 * @return returns a string about what the item is
	 */
	public abstract String lookAt();

	/**
	 * asks for the item to be drawn
	 * 
	 * @param g
	 *            sends in the graphics
	 */
	public abstract void draw(Graphics g);
}
