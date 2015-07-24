/**
 * 
 */
package MovingObjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import Panels.GameWorld;

/**
 * @author Jeremy Driscoll
 * 
 */
public class PlayerCharacter extends GameCharacter {
	/**
	 * holds what the user can see
	 */
	private boolean sightz[][];
	/**
	 * holds the barbarian icon
	 */
	Image barbarianIcon = Toolkit.getDefaultToolkit()
			.getImage("Barbarian3.jpg");
	/**
	 * used for the forgetful barbarian holds the ints to see how far back he
	 * remembers
	 */
	private int forgetfulBarbarianChecker[][];

	/**
	 * construct thingy....
	 * 
	 * @param rowz
	 * @param columnz
	 * @param numberOfRowz
	 * @param numberOfColumnz
	 * @param namez
	 * @param worldz
	 */
	public PlayerCharacter(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, String namez, GameWorld worldz) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, namez, worldz, 0);
		// TODO Auto-generated constructor stub

		sightz = new boolean[getNumberOfRowsAndColumns().x][getNumberOfRowsAndColumns().y];
		forgetfulBarbarianChecker = new int[getNumberOfRowsAndColumns().x][getNumberOfRowsAndColumns().y];

		sight();

	}

	/**
	 * sets what the user can see
	 */
	public void sight() {

		if (getGameWorld().getControls().getNightModeIsOn()) {
			sightz = new boolean[getNumberOfRowsAndColumns().x][getNumberOfRowsAndColumns().y];
			for (int i = getColumn() - 3; i < getColumn() + 4; i++) {
				for (int z = getRow() - 3; z < getRow() + 4; z++) {

					if (i >= 0 && i < getNumberOfRowsAndColumns().y && z >= 0
							&& z < getNumberOfRowsAndColumns().x) {

						sightz[z][i] = true;
					}
				}
			}
		} else if (getGameWorld().getControls().getForgetfulBarbarianIsOn()) {
			for (int i = getColumn() - 2; i < getColumn() + 3; i++) {
				for (int z = getRow() - 2; z < getRow() + 3; z++) {

					if (i >= 0 && i < getNumberOfRowsAndColumns().y && z >= 0
							&& z < getNumberOfRowsAndColumns().x) {

						sightz[z][i] = true;
					}

				}
			}
			for (int i = 0; i < getNumberOfRowsAndColumns().y; i++) {
				for (int z = 0; z < getNumberOfRowsAndColumns().x; z++) {

					if (sightz[z][i]) {
						forgetfulBarbarianChecker[z][i]++;
					}
					if (forgetfulBarbarianChecker[z][i] > 20) {
						forgetfulBarbarianChecker[z][i] = 0;
						sightz[z][i] = false;
					}
				}
			}
		} else {
			for (int i = getColumn() - 2; i < getColumn() + 3; i++) {
				for (int z = getRow() - 2; z < getRow() + 3; z++) {

					if (i >= 0 && i < getNumberOfRowsAndColumns().y && z >= 0
							&& z < getNumberOfRowsAndColumns().x) {

						sightz[z][i] = true;
					}
				}
			}
		}
		setSightOrExploredArea(sightz);

	}

	/**
	 * why do i even have this I think i was going to use this....
	 */
	public void lookAt() {
		// TODO Auto-generated method stub

	}

	/**
	 * graphics stuff
	 */
	public void draw(Graphics g) {

		int startX = (int) ((double) (getColumn()) * ((double) getGameWorld()
				.getWidth() / getNumberOfRowsAndColumns().y));
		int startY = (int) ((double) (getRow())
				* ((double) getGameWorld().getHeight()) / getNumberOfRowsAndColumns().x);
		int width = (getGameWorld().getWidth() / getNumberOfRowsAndColumns().y) + 1;
		int height = (getGameWorld().getHeight() / getNumberOfRowsAndColumns().x) + 1;

		// prints the image to fill one box
		g.drawImage(barbarianIcon, startX, startY, width, height,
				getGameWorld());

	}

	/**
	 * not needed
	 */
	public void howObjectMoves(String movement) {
		move(movement);

	}

	/**
	 * not needed
	 */
	public void setEditMode(boolean editModez) {
		// TODO Auto-generated method stub

	}

	/** 
	 * 
	 */
	public void pickUpItems() {
		// TODO Auto-generated method stub

	}
}
