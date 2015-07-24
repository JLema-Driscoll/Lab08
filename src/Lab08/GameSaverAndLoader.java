package Lab08;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import ItemObjects.Bottle;
import ItemObjects.GameObject;
import ItemObjects.Pizza;
import LandObjects.Land;
import MovingObjects.AggressiveMonster;
import MovingObjects.CowardlyMonster;
import MovingObjects.GameCharacter;
import MovingObjects.GreedyMonster;
import MovingObjects.Monster;
import MovingObjects.PlayerCharacter;
import Panels.GameWorld;

/**
 * supposed to load and save the game
 * 
 * @author Jeremy Driscoll
 * 
 */
public class GameSaverAndLoader {
	GameWorld world;

	/**
	 * constructs the game saver and loader
	 * 
	 * @param z
	 *            sends in the world
	 */
	public GameSaverAndLoader(GameWorld z) {
		world = z;
	}

	/**
	 * writes the items and characters to a file
	 * 
	 * @param totalRows
	 *            sends in the total rows
	 * @param totalColumns
	 *            sends in the total columns
	 * @param items
	 *            sends in the game objects
	 * @param characters
	 *            sends in the the characters
	 */
	public void writeList(int totalRows, int totalColumns, GameObject[] items,
			GameCharacter[] characters) {

		PrintWriter out = null;

		// code I got from joel
		try {

			out = new PrintWriter(new FileWriter("OutFileGameCharacters.txt"));
			// prints out the total rows and columns
			out.println(totalRows + " " + totalColumns);
			// prints out the total number of characters
			out.println(characters.length);
			// prints out the characters
			for (int i = 0; i < characters.length; i++)
				out.println(characters[i].printOutStatement());
			// prints out the number of items
			out.println(items.length);
			// prints out the items data
			for (int i = 0; i < items.length; i++)
				out.println(items[i].printOutStatement());
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Caught " + "ArrayIndexOutOfBoundsException: "
					+ e.getMessage());

		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());

		} finally {
			if (out != null) {

				out.close();
			} else {
				System.out.println("PrintWriter not open");
			}
		}
	}

	/**
	 * a failed attempt at loading the file doesn't work at all
	 */
	public void readFile() {
		try {

			String sCurrentLine;
			int rows = 0;
			int columns = 0;
			int row = 0;
			int column = 0;
			int itemWorth = -1;
			boolean pickedUp;
			BufferedReader br = new BufferedReader(new FileReader(
					"OutFileGameCharacters.txt"));
			int counterFirst = 0;
			int itemCounter = 0;
			int characterCounter = 0;
			int characterType = 0;
			int monsterCounter = 0;
			GameObject[] items;
			String name = "";
			items = new GameObject[1];
			GameCharacter[] character;
			character = new GameCharacter[1];
			while ((sCurrentLine = br.readLine()) != null) {

				if (counterFirst == 0) {
					rows = Integer.parseInt(sCurrentLine.substring(0,
							sCurrentLine.indexOf(" ")));
					columns = Integer.parseInt(sCurrentLine
							.substring(sCurrentLine.indexOf(" ") + 1));

					counterFirst++;
				} else if (counterFirst == 2) {
					character = new GameCharacter[Integer
							.parseInt(sCurrentLine)];
					characterCounter = Integer.parseInt(sCurrentLine);
					counterFirst++;
				} else if (counterFirst < characterCounter + 1) {
					row = Integer.parseInt(sCurrentLine.substring(0,
							sCurrentLine.indexOf(" ")));
					column = Integer.parseInt(sCurrentLine.substring(
							sCurrentLine.indexOf(" ") + 1,
							sCurrentLine.indexOf("!")));
					characterType = Integer.parseInt(sCurrentLine.substring(
							sCurrentLine.indexOf("!") + 1,
							sCurrentLine.indexOf("?")));
					name = sCurrentLine
							.substring(sCurrentLine.indexOf("?") + 1);

					if (characterType == 0) {
						PlayerCharacter monsterz = new PlayerCharacter(row,
								column, rows, columns, name, world);
						character[monsterCounter] = monsterz;
						monsterCounter++;
					} else if (characterType == 1) {
						Monster monsterz = new Monster(row, column, rows,
								columns, name, world, 1);
						character[monsterCounter] = monsterz;
						monsterCounter++;

					} else if (characterType == 2) {
						CowardlyMonster monsterz = new CowardlyMonster(row,
								column, rows, columns, name, world, 2);
						character[monsterCounter] = monsterz;
						monsterCounter++;
					} else if (characterType == 3) {
						AggressiveMonster monsterz = new AggressiveMonster(row,
								column, rows, columns, name, world, 3);
						character[monsterCounter] = monsterz;
						monsterCounter++;

					} else {
						GreedyMonster monsterz = new GreedyMonster(row, column,
								rows, columns, name, world, 4);
						character[monsterCounter] = monsterz;
						monsterCounter++;
					}

				} else if (counterFirst == characterCounter + 2) {
					items = new GameObject[Integer.parseInt(sCurrentLine)];
				} else if (counterFirst > characterCounter + 2) {
					row = Integer.parseInt(sCurrentLine.substring(0,
							sCurrentLine.indexOf(" ")));
					column = Integer.parseInt(sCurrentLine.substring(
							sCurrentLine.indexOf(" ") + 1,
							sCurrentLine.indexOf("!")));
					itemWorth = Integer.parseInt(sCurrentLine.substring(
							sCurrentLine.indexOf("!") + 1,
							sCurrentLine.indexOf("?")));
					pickedUp = Boolean.parseBoolean(sCurrentLine
							.substring(sCurrentLine.indexOf("?") + 1));
					if (itemWorth == 3) {

						Bottle item = new Bottle(rows, columns, row, column, 3,
								world);
						items[itemCounter] = item;

						itemCounter++;
					} else {
						Pizza item = new Pizza(rows, columns, row, column, 5,
								world);
						items[itemCounter] = item;
						itemCounter++;
					}
				}

			}
			world.setItems(items);
			world.setCharacters(character);
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

}
