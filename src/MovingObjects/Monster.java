/**
 * 
 */
package MovingObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Panels.GameWorld;

/**
 * @author Jeremy Driscoll
 * 
 */
public class Monster extends GameCharacter {
	/**
	 * gives a image for the monster
	 */
	private Image monsterIcon = Toolkit.getDefaultToolkit().getImage(
			"Troll.jpg");
	/**
	 * holds whetehr the user can see them
	 */
	private boolean isSeenByUser = false;
	/**
	 * holds whether the editmode is on or not
	 */
	private boolean editMode = true;

	/**
	 * CONTRUCTOR pretty obvoius what is sent in....
	 * 
	 * @param rowz
	 * @param columnz
	 * @param numberOfRowz
	 * @param numberOfColumnz
	 * @param namez
	 * @param worldz
	 * @param characterType
	 */
	public Monster(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, String namez, GameWorld worldz,
			int characterType) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, namez, worldz,
				characterType);
		sight();

	}

	/**
	 * sends in if edit mode is on
	 */
	public void setEditMode(boolean editModez) {
		editMode = editModez;
	}

	/**
	 * sees if the user can see the object
	 * 
	 * @return returns if the user can
	 */
	public boolean getIsSeenByUser() {
		return isSeenByUser;

	}

	/**
	 * changes the monster icon if needed
	 * 
	 * @param newIcon
	 *            sends in the new icon
	 */
	public void changeMonsterIcon(Image newIcon) {
		monsterIcon = newIcon;
	}

	/**
	 * finds out if the user can see it so it knows if to draw itself
	 */
	public void sight() {
		int userColumn = getGameWorld().getAllCharacters()[0].getColumn();
		int userRow = getGameWorld().getAllCharacters()[0].getRow();
		if (!getGameWorld().getControls().getNightModeIsOn())
			if (userColumn > getColumn() - 4 && userColumn < getColumn() + 4
					&& userRow > getRow() - 4 && userRow < getRow() + 4) {
				isSeenByUser = true;
			} else {
				isSeenByUser = false;
			}
		else {
			if (userColumn > getColumn() - 3 && userColumn < getColumn() + 3
					&& userRow > getRow() - 3 && userRow < getRow() + 3) {
				isSeenByUser = true;
			} else {
				isSeenByUser = false;
			}
		}
	}

	/**
	 * does nothing
	 */
	public void lookAt() {
		// TODO Auto-generated method stub

	}

	/**
	 * draws object
	 */
	public void draw(Graphics g) {
		sight();
		if (isSeenByUser || editMode) {
			int startX = (int) ((double) (getColumn()) * ((double) getGameWorld()
					.getWidth() / getNumberOfRowsAndColumns().y));
			int startY = (int) ((double) (getRow())
					* ((double) getGameWorld().getHeight()) / getNumberOfRowsAndColumns().x);
			int width = (getGameWorld().getWidth() / getNumberOfRowsAndColumns().y) + 1;
			int height = (getGameWorld().getHeight() / getNumberOfRowsAndColumns().x) + 1;

			// prints the image to fill one box
			g.drawImage(monsterIcon, startX, startY, width, height,
					getGameWorld());
		}

	}

	/**
	 * sets how the object moves
	 */
	public void howObjectMoves(String movement) {
		String movementz = "n";
		int randomizer = (int) (Math.random() * 100);
		if (randomizer >= 0 && randomizer < 25) {
			movementz = "n";
		} else if (randomizer >= 25 && randomizer < 50) {
			movementz = "s";
		} else if (randomizer >= 50 && randomizer < 75) {
			movementz = "e";
		} else if (randomizer >= 75 && randomizer < 100) {
			movementz = "w";
		}

		move(movementz);
	}

	/**
	 * picks up an item if there is an item to be picked up
	 */
	public void pickUpItems() {

		for (int i = 0; i < getGameWorld().getAllItems().length; i++) {
			if (getGameWorld().getIfObjectIsAtLocation(getRow(), getColumn())[i]
					&& getItemCounter() < 5) {
				setInventory(getGameWorld().getAllItems()[i]);
				getGameWorld().getAllItems()[i].setColumn(-1);
				getGameWorld().getAllItems()[i].setRow(-1);
				getGameWorld().getAllItems()[i].setIsPickedUp(true);
			}

		}

	}

}
