import java.util.*;

/**
 * A class that simulates a computer that solves the grid puzzle
 * @author Mark
 *
 */
public class GridComputer {

	private boolean[][] answers;
	private int tilesInARow;

	/**
	 * Creates a grid computer that solves a grid of size "N"
	 * @param tilesInARow the "N" amount of tiles that create the size of the grid
	 */
	public GridComputer(int tilesInARow) {
		this.tilesInARow = tilesInARow;
		answers = new boolean[tilesInARow][tilesInARow];
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				answers[i][j] = false;
			}
		}
	}

	/**
	 * Randomizes the grid into a whole new puzzle with a different set of answers
	 * @param tilesToInvert the minimum amount of tiles the user will need to click to win the game
	 * @throws Exception an exception in which the parameter exceeds the total amount of tiles in the grid
	 */
	public void randomizeAnswers(int tilesToInvert) throws Exception {
		if (tilesToInvert > tilesInARow * tilesInARow) {
			throw new Exception(
					"A grid of size " + tilesInARow * tilesInARow + " can't hold " + tilesToInvert + " answers.");
		}
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				answers[i][j] = false;
			}
		}
		Random gen = new Random();
		while (tilesToInvert > 0) {
			int i = gen.nextInt(tilesInARow);
			int j = gen.nextInt(tilesInARow);
			if (!answers[i][j]) {
				answers[i][j] = true;
				tilesToInvert--;
			}
		}
	}

	/**
	 * Returns the answers as a 2Darray boolean representation
	 * @return the answers
	 */
	public boolean[][] giveAnswers() {
		return answers.clone();
	}

	/**
	 * Switches the value of one of the answers in the 2Darray
	 * @param i the horizontal i component of the grid
	 * @param j the horizontal j component of the grid
	 */
	public void update(int i, int j) {
		boolean answer = answers[i][j];
		if (answer) {
			answers[i][j] = false;
		} else {
			answers[i][j] = true;
		}
	}
}
