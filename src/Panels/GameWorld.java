package Panels;

import java.awt.Graphics;
import java.awt.GridLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ItemObjects.Bottle;
import ItemObjects.GameObject;
import ItemObjects.Pizza;
import Lab08.GameSaverAndLoader;
import Lab08.Lab08;
import Lab08.MapSaverAndLoader;
import LandObjects.Land;
import MovingObjects.AggressiveMonster;
import MovingObjects.CowardlyMonster;
import MovingObjects.GameCharacter;
import MovingObjects.GreedyMonster;
import MovingObjects.Monster;
import MovingObjects.PlayerCharacter;
/**
 * the game world
 * @author Jeremy Driscoll
 *
 */
public class GameWorld extends JPanel implements MouseListener,
		MouseMotionListener {

	/**
	 * stuff
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * counts stuff
	 */
	int counter = 0;
	/**
	 *  holds the mainframe
	 */
	JFrame mainFrame;
	/**
	 * holds the lab
	 */
	Lab08 labz;
	/**
	 * holds all the land
	 */
	Land[][] allLands;
	/**
	 * holds the control panel
	 */
	ControlPanel controls;
	/**
	 * holds the characters
	 */
	GameCharacter allCharacters[];
	/**
	 * holds the items
	 */
	GameObject allItems[];
	/**
	 * holds the total number of rows and columns
	 */
	int numberOfRows, numberOfColumns;
	/**
	 * holds the command sent in
	 */
	UserCommandsSendIn command;
	/**
	 * holds the inventory
	 */
	Inventory itemPanel;
	/**
	 * holds the game saver and loader
	 */
	GameSaverAndLoader gameSaverAndLoaderz;
	/**
	 * holds the map saver and loader
	 */
	private MapSaverAndLoader mapLoadandSaver;
	
	/**
	 * initializes the frame
	 * @param lab
	 * @param numberOfRowz
	 * @param numberOfColumnz
	 * @param myFrame
	 * @param control
	 * @param commandz
	 */
	public GameWorld(Lab08 lab, int numberOfRowz, int numberOfColumnz,
			JFrame myFrame, ControlPanel control, UserCommandsSendIn commandz) {

		labz = lab;
		mainFrame = myFrame;
		controls = control;
		numberOfRows = numberOfRowz;
		numberOfColumns = numberOfColumnz;
		allLands = new Land[numberOfRows][numberOfColumns];
		// adds the mouseListener
		addMouseListener(this);
		addMouseMotionListener(this);
		// draws rows
		command = commandz;
		makeLands();
		allItems = new GameObject[0];
		
		allCharacters = new GameCharacter[1];
		PlayerCharacter player = new PlayerCharacter(5, 3, numberOfRows,
				numberOfColumns, "Ted", this);
		allCharacters[0] = player;
		mapLoadandSaver= new MapSaverAndLoader(this);
		makeCharacters(2, 7);
		//makeItems(2, 5, 4, 5);
		gameSaverAndLoaderz = new GameSaverAndLoader(this);
		makeItems(2, 10, 5, 5);
		
		
	
	}
/**
 * sends in the item panel
 * @param panelthing the item panel
 */
	public void sendInItemPanel(Inventory panelthing)
	{
		itemPanel=panelthing;
	}
	/**
	 * loads a file for the map
	 */
	public void loadFile()
	{
		mapLoadandSaver.readFile();
	}
	/**
	 * saves a map file
	 */
	public void saveToFile()
	{
		mapLoadandSaver.writeList(numberOfRows, numberOfColumns, allLands);
	}
	/**
	 * loads the game but not really
	 */
	public void loadGameFile()
	{
		//gameSaverAndLoaderz.readFile();
	}
	/**
	 * saves the game
	 */
	public void saveToGameFile()
	{
		gameSaverAndLoaderz.writeList(numberOfRows, numberOfColumns,allItems ,allCharacters);
	}
	/**
	 * draws the grid and objects
	 * 
	 * @param g
	 *            sends in the graphics package thing
	 */
	public void draw(Graphics g) {

		// draws rows
		for (int x = 0; x < numberOfRows; x++) {
			g.drawLine(0, (int) (((double) getHeight() / numberOfRows) * x),
					getWidth(),
					(int) (((double) getHeight() / numberOfRows) * x));
		}
		// draws columns
		for (int y = 0; y < numberOfColumns; y++) {
			g.drawLine((int) (((double) getWidth() / numberOfColumns) * y), 0,
					(int) (((double) getWidth() / numberOfColumns) * y),
					getHeight());
		}

		for (int i = 0; i < numberOfRows; i++) {
			for (int z = 0; z < numberOfColumns; z++) {
				if (allLands[i][z] != null) {
					landVisablity();
					allLands[i][z].draw(g, getWidth(), getHeight(),
							numberOfRows, numberOfColumns);
				}
			}
		}
		for (int i = 0; i < allItems.length; i++) {
			allItems[i].draw(g);

		}
		for (int i = 1; i < allCharacters.length; i++) {
			int s = allCharacters[i].getRow();
			int d = allCharacters[i].getColumn();

			if (controls.getEditMode()
					|| allCharacters[0].getSightOrExploredArea()[s][d]) {
				allCharacters[i].draw(g);

			}

		}

		allCharacters[0].draw(g);
		mainFrame.repaint();
	}
/**
 * sets the monsters and items visable or not dependsing on edit or play mode
 */
	public void monsterVisablity() {
		if (controls.getEditMode()) {
			for (int i = 0; i < allCharacters.length; i++) {
				allCharacters[i].setEditMode(true);
			}
		} else
			for (int i = 0; i < allCharacters.length; i++) {
				allCharacters[i].setEditMode(false);
			}
		if (controls.getEditMode()) {
			for (int i = 0; i < allItems.length; i++) {
				allItems[i].setEditMode(true);
			}
		} else
			for (int i = 0; i < allItems.length; i++) {
				allItems[i].setEditMode(false);
			}

	}

	/**
	 * makes the land visable or not 
	 */
	public void landVisablity() {
		if (controls.getEditMode()) {
			for (int row = 0; row < numberOfRows; row++) {
				for (int column = 0; column < numberOfColumns; column++) {
					allLands[row][column].setVisablity(true);
				}
			}
		} else {
			for (int rows = 0; rows < numberOfRows; rows++) {
				for (int columns = 0; columns < numberOfColumns; columns++) {
					allLands[rows][columns].setVisablity(allCharacters[0]
							.getSightOrExploredArea()[rows][columns]);

				}
			}
		}

	}
/**
 * resets everything
 */
	public void restAll()
	{
		counter = 0;
		allLands= new Land[numberOfRows][numberOfColumns];
		allCharacters= new GameCharacter[1];
		allItems= new GameObject[0];
		allCharacters = new GameCharacter[1];
		PlayerCharacter player = new PlayerCharacter(5, 3, numberOfRows,
				numberOfColumns, "Ted", this);
		allCharacters[0] = player;

		makeCharacters(2, 7);
		makeItems(2, 5, 4, 5);
		
	}

	/**
	 * gets the control panel
	 * @return sends out the control panel
	 */
	public ControlPanel getControls()
	{
		return controls;
	}
	/**
	 * increases the array lenght of the characters
	 */
	public void increaseAllCharactersLength() {
		GameCharacter[] temp = allCharacters;
		allCharacters = new GameCharacter[allCharacters.length + 1];
		for (int i = 0; i < temp.length; i++) {
			allCharacters[i] = temp[i];
		}
	}

/**
 * makes the characters
 * @param row sends in the row to make em at
 * @param column sends in the column to make em at
 */
	public void makeCharacters(int row, int column) {

		increaseAllCharactersLength();
		Monster monsterz = new Monster(row, column, numberOfRows,
				numberOfColumns, "DanNumber" + counter, this,1);
		allCharacters[allCharacters.length - 1] = monsterz;
		CowardlyMonster coward = new CowardlyMonster(row, column, numberOfRows,
				numberOfColumns, "ZebNumber" + counter, this,2);
		increaseAllCharactersLength();
		allCharacters[allCharacters.length - 1] = coward;
		AggressiveMonster aggressive = new AggressiveMonster(row, column, numberOfRows,
				numberOfColumns, "YebNumber" + counter, this,3);
		increaseAllCharactersLength();
		allCharacters[allCharacters.length - 1] = aggressive;
		GreedyMonster greedy = new GreedyMonster(row, column, numberOfRows,
				numberOfColumns, "TedNumber" + counter, this,4);
		increaseAllCharactersLength();
		allCharacters[allCharacters.length - 1] = greedy;
		
		counter++;
	}

	/**
	 * increases the item length of array
	 */
	public void increaseAllItemLength() {
		GameObject[] temp = allItems;
		allItems = new GameObject[allItems.length + 1];
		for (int i = 0; i < temp.length; i++) {
			allItems[i] = temp[i];
		}
	}

	/**
	 * makes the items and makes them in 2 different areas
	 * @param row
	 * @param column
	 * @param row2
	 * @param column2
	 */
	public void makeItems(int row, int column, int row2, int column2) {
		increaseAllItemLength();
		Pizza item = new Pizza(row, column, numberOfRows, numberOfColumns, 5,
				this);
		allItems[allItems.length - 1] = item;
		increaseAllItemLength();
		Bottle itemz = new Bottle(row2, column2, numberOfRows, numberOfColumns,
				3, this);
		allItems[allItems.length - 1] = itemz;
		repaint();
	}

	/**
	 * sends in the commands
	 * @param commandz send in the command
	 */
	public void commandSend(String commandz) {
		if (!controls.getEditMode()) {
			for (int i = 0; i < allCharacters.length; i++) {
				allCharacters[i].howObjectMoves(commandz);
			}
			boolean search[] = getIfObjectIsAtLocation(allCharacters[0].getRow(), allCharacters[0].getColumn());
			for(int i=0; i<search.length; i++)
			{
				if(search[i])
				{
					ObjectPickUper newScreen = new ObjectPickUper("Item Pick Up Screen",600,200,allItems,search,this);
					repaint();
					break;
				}
			}
		}
		allCharacters[0].sight();
	}

	/**
	 * paints things and sets the monsters visablities of mosnters
	 */
	public void paintComponent(Graphics g) {

		draw(g);
		monsterVisablity();

	}
/**
 * makes the land
 */
	public void makeLands() {
		for (int row = 0; row < numberOfRows; row++) {
			for (int column = 0; column < numberOfColumns; column++) {
				allLands[row][column] = new Land(this, -1, row, column);
			}
		}

	}

	/**
	 * changes the land
	 * @param row sends in the row of the land
	 * @param column sends in the column of the land
	 */
	public void changeLands(int row, int column) {
		allLands[row][column].setLandType(getLandType());
		repaint();
	}
/**
 * gets the lands type
 * @return returns the lands type
 */
	public int getLandType() {

		return controls.getCurrentSelectedLand();
	}
	/**
	 * sets the lands type and repaints
	 * @param set sends in what to set it as
	 */
	public void setLand(Land set[][])
	{
		allLands=set;
		repaint();
	}
	/**
	 * sets the items
	 * @param item sends in what to set them as
	 */
	public void setItems(GameObject item[])
	{
		allItems=item;
	}
	/**
	 * sets the characters
	 * @param character sends in what the characters are
	 */
	public void setCharacters(GameCharacter character[])
	{
		allCharacters=character;
	}
	
	/**
	 * if the mouse is clicked land is made in edit mode
	 */
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX(), y = e.getY();

		// Creates the counters for the rows and columns
		int columnNumber = 0, rowNumber = 0;

		// finds the column that the click is in
		for (int w = 1; w < numberOfColumns + 1; w++) {
			if (x < (int) (((double) getWidth() / numberOfColumns) * w)) {
				columnNumber = w - 1;
				break;
			}
		}
		// finds the row the click is in
		for (int w = 1; w < numberOfRows + 1; w++) {
			if (y < (int) (((double) getHeight() / numberOfRows) * w)) {
				rowNumber = w - 1;
				break;
			}
		}

		if (controls.getEditMode()) {
			changeLands(rowNumber, columnNumber);
		}
		mainFrame.repaint();

	}
/**
 * when population is hit then the population is increased
 */
	public void populationReceiver() {

			Random randColumn = new Random();
			int ranColz = randColumn.nextInt(numberOfColumns);
			Random randRows = new Random();
			int ranRowz = randRows.nextInt(numberOfRows);
			makeCharacters(ranRowz, ranColz);
		
			int ranColz3 = randColumn.nextInt(numberOfColumns);
			int ranRowz3 = randRows.nextInt(numberOfRows);
			int ranColz2 = randColumn.nextInt(numberOfColumns);
			int ranRowz2 = randRows.nextInt(numberOfRows);
			makeItems(ranRowz3, ranColz3, ranRowz2, ranColz2);
			
	}

	/**
	 * gets what characters are at a certain location
	 * @param row
	 * @param column
	 * @return returns an array saying if a character is there corrisponds with the character area
	 */
	public boolean[] getIfCharacterIsAtLocation(int row, int column) {
		boolean[] isObjectHere= new boolean[allCharacters.length];
		for(int i=0; i<allCharacters.length; i++)
		{
			if(allCharacters[i].getColumn()==column)
			{
				if(allCharacters[i].getRow()==row)
				{
					isObjectHere[i]=true;
				}
			}
		}
		
		return isObjectHere;

	}
	/**
	 * gets the game object
	 * @return returns the game object
	 */
	public GameObject[] getAllItems()
	{
		return allItems;
	}
	/**
	 * finds out if the object is at a location
	 * @param row
	 * @param column
	 * @return returns an array of if an object is at the location corrisponds with the object array
	 */
	public boolean[] getIfObjectIsAtLocation(int row, int column) {
		boolean[] isObjectHere= new boolean[allItems.length];
		
		for(int i=0; i<allItems.length; i++)
		{
			if(allItems[i].getColumn()==column)
			{
				if(allItems[i].getRow()==row)
				{
					isObjectHere[i]=true;
				}
			}
		}
		
		return isObjectHere;

	}
	/**
	 * sets of the users inventory
	 * @param itemz sends in the item that should go in the users inventory
	 */
	public void setUserInventoryItems(GameObject itemz)
	{
		allCharacters[0].setInventory(itemz);
		itemPanel.setUserInventoryItems(itemz);
	}
	
	
	/**
	 * gets all the characters
	 * @return returns all the characters
	 */
	public GameCharacter[] getAllCharacters() {
		return allCharacters;
	}
/**
 * gets the direction that the user moves and reports it to the console
 * @return returns the statement of what happens when the character moves
 */ 
	public String getSpaceInfo() {
		String spaceInformation = "";
		spaceInformation = allCharacters[0].getReturnStatement();
		return spaceInformation;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * paints the land if the mouse is dragged
	 */
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX(), y = e.getY();

		// Creates the counters for the rows and columns
		int columnNumber = 0, rowNumber = 0;

		// finds the column that the click is in
		for (int w = 1; w < numberOfColumns + 1; w++) {
			if (x < (int) (((double) getWidth() / numberOfColumns) * w)) {
				columnNumber = w - 1;
				break;
			}
		}
		// finds the row the click is in
		for (int w = 1; w < numberOfRows + 1; w++) {
			if (y < (int) (((double) getHeight() / numberOfRows) * w)) {
				rowNumber = w - 1;
				break;
			}
		}
		if (controls.getEditMode()) {
			changeLands(rowNumber, columnNumber);
		}
		mainFrame.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
