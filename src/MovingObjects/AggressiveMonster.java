package MovingObjects;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import Panels.GameWorld;

/**
 * create the aggressive monster
 * 
 * @author Jeremy Driscoll
 * 
 */
public class AggressiveMonster extends Monster {

	/**
	 * stores the image of the monster
	 */
	private Image monsterIcon = Toolkit.getDefaultToolkit().getImage(
			"agressive.jpg");

	/**
	 * Constructor for the monster
	 * 
	 * @param rowz
	 *            sends in the row its at
	 * @param columnz
	 *            sends in the column it is at
	 * @param numberOfRowz
	 *            sends in the total number of rows
	 * @param numberOfColumnz
	 *            sends in the total number of columns
	 * @param namez
	 *            sends in the name
	 * @param worldz
	 *            sends in the world
	 * @param characterType
	 *            sends in the character type
	 */
	public AggressiveMonster(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, String namez, GameWorld worldz,
			int characterType) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, namez, worldz,
				characterType);
		sight();
		// changes the monster icon
		changeMonsterIcon(monsterIcon);
	}

	/**
	 * changes how it moves if it sees the user
	 */
	public void howObjectMoves(String movement) {

		String movementz = "n";
		if (!getIsSeenByUser()) {
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

		}

		// moves to the user
		else {
			int userColumn = getGameWorld().getAllCharacters()[0].getColumn();
			int userRow = getGameWorld().getAllCharacters()[0].getRow();
			Random randColumn = new Random();
			int ranColz = randColumn.nextInt(10);
			Random randRows = new Random();
			int ranRowz = randRows.nextInt(10);

			if (userColumn > getColumn()) {
				if (userRow > getRow()) {
					if (ranColz > ranRowz) {
						movementz = "e";
					} else {
						movementz = "s";
					}
				} else {
					if (ranColz > ranRowz) {
						movementz = "e";
					} else {
						movementz = "n";
					}
				}
			} else {
				if (userRow > getRow()) {
					if (ranColz > ranRowz) {
						movementz = "w";
					} else {
						movementz = "s";
					}
				} else {
					if (ranColz > ranRowz) {
						movementz = "w";
					} else {
						movementz = "n";
					}
				}
			}
			if (userColumn == getColumn()) {
				if (userRow > getRow()) {
					movementz = "s";
				} else {
					movementz = "n";
				}
			}
			if (userRow == getRow()) {
				if (userColumn > getColumn()) {
					movementz = "e";
				} else {
					movementz = "w";
				}
			}

		}
		move(movementz);
	}
}
