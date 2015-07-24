package ItemObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Panels.GameWorld;

/**
 * pizza objects made here
 * 
 * @author Jeremy Driscoll
 * 
 */
public class Pizza extends GameObject {
	Image pizaaIcon = Toolkit.getDefaultToolkit().getImage("Pizza.jpg");
	boolean editMode = true;

	/**
	 * constructs the pizza object
	 * 
	 * @param rowz
	 *            sends in the row it is at
	 * @param columnz
	 *            sends in the column it is at
	 * @param numberOfRowz
	 *            sends in the total number of rows
	 * @param numberOfColumnz
	 *            sends in teh total number of columns
	 * @param amount
	 *            sends in the item worth
	 * @param worldz
	 *            sends in the world
	 */
	public Pizza(int rowz, int columnz, int numberOfRowz, int numberOfColumnz,
			int amount, GameWorld worldz) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, amount, worldz);
		// TODO Auto-generated constructor stub
	}

	/**
	 * sends in if it is edit is on or not
	 */
	public void setEditMode(boolean editModez) {
		editMode = editModez;
	}

	/**
	 * looks at the item and returns what it is
	 */
	public String lookAt() {
		String toRet = "";
		toRet = "This Pizza is worth " + getItemWorth() + ".";

		return toRet;
	}

	/**
	 * draws the item
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
			g.drawImage(pizaaIcon, startX, startY, width, height,
					getGameWorld());

		}

	}
}
