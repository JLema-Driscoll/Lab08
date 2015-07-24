package LandObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

import Lab08.Lab08;
import Panels.GameWorld;

/**
 * builds lands
 * 
 * @author Jeremy Driscoll
 * 
 */
public class Land {

	/**
	 * default land type is no land type and stores the land type
	 */
	int landType = -1;
	/**
	 * stores columns and rows
	 */
	int row, column;
	/**
	 * stores if the land is visable to the user
	 */
	boolean visable = false;
	/**
	 * stores all of the possible pictures for the land
	 */
	private Image forestIcon = Toolkit.getDefaultToolkit().getImage(
			"forest.jpg"), fieldIcon = Toolkit.getDefaultToolkit().getImage(
			"field.jpg"), riverIcon = Toolkit.getDefaultToolkit().getImage(
			"river.jpg"), mountainIcon = Toolkit.getDefaultToolkit().getImage(
			"mountain.jpg"), bogIcon = Toolkit.getDefaultToolkit().getImage(
			"bog.jpg");
	/**
	 * stores the land types
	 */
	final int forest = 0, field = 1, river = 2, mountain = 3, bog = 4;
	/**
	 * holds the game world
	 */
	GameWorld q;

	/**
	 * constructs the land
	 * 
	 * @param sendIn
	 *            sends in the game world
	 * @param landTypez
	 *            sends in the land type
	 * @param rowz
	 *            sends in the row
	 * @param columnz
	 *            sends in the column
	 */
	public Land(GameWorld sendIn, int landTypez, int rowz, int columnz) {
		q = sendIn;
		landType = landTypez;
		row = rowz;
		column = columnz;

	}

	/**
	 * makes the printed statement to be read and saved to the file
	 * 
	 * @return returns the statement used to save and reconstruct the land
	 */
	public String printOutStatement() {
		String toRet = "";
		toRet = row + " " + column + "?" + landType;

		return toRet;
	}

	/**
	 * sets the land type
	 * 
	 * @param type
	 *            sends in the land type for the land to be set as
	 */
	public void setLandType(int type) {
		landType = type;
	}

	/**
	 * sets the visablity of the land
	 * 
	 * @param vis
	 *            sends in if land is visable or not
	 */
	public void setVisablity(boolean vis) {
		visable = vis;
	}

	/**
	 * DRAws the land
	 * 
	 * @param myContext
	 *            sends in the graphic stuff
	 * @param width
	 *            sends in the width of the app
	 * @param height
	 *            sends in the heigh of the app
	 * @param numberOfRows
	 *            sends in the total number of rows
	 * @param numberOfColumns
	 *            sends in the total number of columns
	 */
	public void draw(Graphics myContext, int width, int height,
			int numberOfRows, int numberOfColumns) {

		// prints the image to fill one box
		int startX = (int) (column * ((double) width / numberOfColumns));
		int startY = (int) (row * ((double) height / numberOfRows));
		int widthOfIcon = (width / numberOfColumns) + 1;
		int heightOfIcon = (height / numberOfRows) + 1;

		// if it isnt visable it should be painted black
		if (!visable) {
			myContext.setColor(Color.BLACK);
			myContext.fillRect(startX, startY, widthOfIcon, heightOfIcon);
		}
		// if it is visiable the paint it how it should be
		else if (visable) {
			if (landType == forest) {
				myContext.drawImage(forestIcon, startX, startY, widthOfIcon,
						heightOfIcon, q);
			} else if (landType == field) {
				myContext.drawImage(fieldIcon, startX, startY, widthOfIcon,
						heightOfIcon, q);
			} else if (landType == river) {
				myContext.drawImage(riverIcon, startX, startY, widthOfIcon,
						heightOfIcon, q);
			} else if (landType == mountain) {
				myContext.drawImage(mountainIcon, startX, startY, widthOfIcon,
						heightOfIcon, q);
			} else if (landType == bog) {
				myContext.drawImage(bogIcon, startX, startY, widthOfIcon,
						heightOfIcon, q);
			}
		}
	}

}
