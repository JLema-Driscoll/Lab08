package MovingObjects;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import Panels.GameWorld;

/**
 * this creates cowardly monsters
 * 
 * @author Jeremy Driscoll
 * 
 */
public class CowardlyMonster extends Monster {

	/**
	 * holds the right image for this monster
	 */
	private Image monsterIcon = Toolkit.getDefaultToolkit().getImage(
			"coward.jpg");

	/**
	 * CONSTRUCTOR for this fellow
	 * 
	 * @param rowz
	 *            sneds in da row
	 * @param columnz
	 *            sends in the column
	 * @param numberOfRowz
	 *            sends in the total rowz
	 * @param numberOfColumnz
	 *            sends in the total columns
	 * @param namez
	 *            sends in its name
	 * @param worldz
	 *            sends in the world
	 * @param characterType
	 *            sends in the character type
	 */
	public CowardlyMonster(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, String namez, GameWorld worldz,
			int characterType) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, namez, worldz,
				characterType);
		sight();
		changeMonsterIcon(monsterIcon);
	}

	/**
	 * changes how the object moves overrides the mosnters movement
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
		// makes the monster run away if it sees the user
		else {
			int userColumn = getGameWorld().getAllCharacters()[0].getColumn();
			int userRow = getGameWorld().getAllCharacters()[0].getRow();
			Random randColumn = new Random();
			int ranColz = randColumn.nextInt(10);
			Random randRows = new Random();
			int ranRowz = randRows.nextInt(10);

			if (userColumn >= getColumn()) {
				if (userRow >= getRow()) {
					if (ranColz > ranRowz) {
						movementz = "w";
					} else {
						movementz = "n";
					}
				} else {
					if (ranColz > ranRowz) {
						movementz = "w";
					} else {
						movementz = "s";
					}
				}
			} else {
				if (userRow >= getRow()) {
					if (ranColz > ranRowz) {
						movementz = "e";
					} else {
						movementz = "n";
					}
				} else {
					if (ranColz > ranRowz) {
						movementz = "e";
					} else {
						movementz = "s";
					}
				}
			}

		}
		move(movementz);
	}
}
