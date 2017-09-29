import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * A label that acts as the grid for the player to interact with
 * @author Mark
 *
 */
public class GridLabel extends JLabel {

	private GridIcon icon;
	private int tilesInARow;
	private GridComputer cpu;
	private int turns;

	/**
	 * Creates a grid label that displays in accordance to a GridIcon and a couple of width parameters
	 * @param icon the GridIcon to draw out
	 * @param tileWidth the pixel width of the tiles
	 * @param tilesInARow the amount of tiles per row
	 */
	public GridLabel(GridIcon icon, int tileWidth, int tilesInARow) {
		super(icon);
		turns = 0;
		this.icon = icon;
		this.tilesInARow = tilesInARow;
		cpu = new GridComputer(tilesInARow);

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				Point mousePoint = event.getPoint();
				double y = mousePoint.getY();
				double x = mousePoint.getX();
				int i = (int) (x / tileWidth);
				int j = (int) (y / tileWidth);
				cpu.update(i, j);
				invertTile(i, j);
				repaint();
				turns++;
				checkIfSolved();
			}
		});
	}

	/**
	 * Checks if the game is solved, and displays a winning dialogue if the game is beaten
	 */
	public void checkIfSolved() {
		if (icon.isSolved()) {
			JOptionPane.showMessageDialog(this, "Congratulations. You solved it in " + turns + " turns.");
			turns = 0;
		}
	}

	/**
	 * Switches the color of a tile in an i and j coordinate, and all tiles adjacent to it
	 * @param i the horizontal i component of the grid
	 * @param j the vertical i component of the grid
	 */
	public void invertTile(int i, int j) {
		icon.switchTile(i, j);
		if (i + 1 < tilesInARow) {
			icon.switchTile(i + 1, j);
		}
		if (i - 1 > -1) {
			icon.switchTile(i - 1, j);
		}
		if (j + 1 < tilesInARow) {
			icon.switchTile(i, j + 1);
		}
		if (j - 1 > -1) {
			icon.switchTile(i, j - 1);
		}
	}

	/**
	 * Randomizes the grid
	 * @param tilesToInvert the minimum amount of tiles the player will need to click to win
	 */
	public void randomize(int tilesToInvert) throws Exception {
		turns = 0;
		icon.reset();
		cpu.randomizeAnswers(tilesToInvert);
		boolean[][] answers = cpu.giveAnswers();
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				if (answers[i][j]) {
					invertTile(i, j);
				}
			}
		}
	}

	/**
	 * Returns a 2Darray of booleans that represents the answers of the puzzle
	 * @return the answers
	 */
	public boolean[][] getAnswers() {
		return cpu.giveAnswers();
	}
}
