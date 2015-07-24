package MovingObjects;

import java.awt.Image;
import java.awt.Toolkit;

import ItemObjects.GameObject;
import Panels.GameWorld;

/**
 * makes the greedy monster
 * 
 * @author Jeremy Driscoll
 * 
 */
public class GreedyMonster extends Monster {
	/**
	 * give the image of the monster
	 */
	private Image monsterIcon = Toolkit.getDefaultToolkit().getImage(
			"greedy.jpg");

	/**
	 * Constuctor for the monster
	 * 
	 * @param rowz
	 * @param columnz
	 * @param numberOfRowz
	 * @param numberOfColumnz
	 * @param namez
	 * @param worldz
	 * @param characterType
	 */
	public GreedyMonster(int rowz, int columnz, int numberOfRowz,
			int numberOfColumnz, String namez, GameWorld worldz,
			int characterType) {
		super(rowz, columnz, numberOfRowz, numberOfColumnz, namez, worldz,
				characterType);
		sight();
		changeMonsterIcon(monsterIcon);
	}

	/**
	 * how it picks up items basicly wants pizza because it is worth more
	 */
	public void pickUpItems() {

		for (int i = 0; i < getGameWorld().getAllItems().length; i++) {
			if (getGameWorld().getIfObjectIsAtLocation(getRow(), getColumn())[i]
					&& getItemCounter() < 5) {
				setInventory(getGameWorld().getAllItems()[i]);
				getGameWorld().getAllItems()[i].setColumn(-1);
				getGameWorld().getAllItems()[i].setRow(-1);
				getGameWorld().getAllItems()[i].setIsPickedUp(true);
			} else if (getGameWorld().getIfObjectIsAtLocation(getRow(),
					getColumn())[i]
					&& getItemCounter() > 4) {

				for (int w = 0; w < getInventory().length; w++) {
					if (getInventory()[w].getItemWorth() < getGameWorld()
							.getAllItems()[i].getItemWorth()) {

						dropUpItem(w);
						setInventory(getGameWorld().getAllItems()[i]);
						getGameWorld().getAllItems()[i].setColumn(-1);
						getGameWorld().getAllItems()[i].setRow(-1);
						getGameWorld().getAllItems()[i].setIsPickedUp(true);

					}

				}
			}

		}
	}

}
