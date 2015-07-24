package ItemObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Panels.GameWorld;

/**
 * Bottle
 * 
 * @author Jeremy Driscoll
 * 
 */
public class Bottle extends GameObject {
	/**
	 * Loads the Image of the bottle
	 */
	Image bottleIcon = Toolkit.getDefaultToolkit().getImage("Bottle.jpg");
	/**
	 * tells if it is in editMode
	 */
	boolean editMode = true;
	/**
	 * tells if it is picked up or not
	 */
	boolean pickedUp = false;

	/**
	 * Constructor
	 * 
	 * @param rowz
	 *            the row it is on
	 * @param columnz
	 *            the column it is on
	 * @param numberOfRowz
	 *            the number of rows
	 * @param numberOfColumnz
	 *            the number of columns
	 * @param amount
	 *            the worth of the item
	 * @param worldz
	 *            the world or center panel
	 */
	public Bottle(int rowz, int columnz, int numberOfRowz, int numberOfColumnz,
			int amount, GameWorld worldz) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, amount, worldz);
		// TODO Auto-generated constructor stub
	}

	/**
	 * says if it is edit mode or not
	 */
	public void setEditMode(boolean editModez) {
		editMode = editModez;
	}

	/**
	 * returns string about bottle
	 */
	public String lookAt() {
		String toRet = "";
		toRet = "A bottle is worth " + getItemWorth() + ".";

		return toRet;
	}

	/**
	 * draws object
	 */
	public void draw(Graphics g) {
		sight();
		if ((getIsSeenByUser() || editMode) && !getIsPickedUp()) {
			int startX = (int) ((double) (getColumn()) * ((double) getGameWorld()
					.getWidth() / getNumberOfRowsAndColumns().y));
			int startY = (int) ((double) (getRow())
					* ((double) getGameWorld().getHeight()) / getNumberOfRowsAndColumns().x);
			int width = (getGameWorld().getWidth() / getNumberOfRowsAndColumns().y) + 1;
			int height = (getGameWorld().getHeight() / getNumberOfRowsAndColumns().x) + 1;

			// prints the image to fill one box
			g.drawImage(bottleIcon, startX, startY, width, height,
					getGameWorld());

		}

	}
}
