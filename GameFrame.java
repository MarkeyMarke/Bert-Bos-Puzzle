import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A class that acts as the frame of the actual game, given parameters for the settings desired
 * @author Mark
 *
 */
public class GameFrame extends JFrame {

	private GridLabel label;
	private final int tilesInARow;
	JTextArea solveText;
	JPanel box;

	/**
	 * Creates a frame that simulates all of the game
	 * @param gridWidth the width of the grid, in terms of pixels on the screen
	 * @param tileWidth the width of the grid, in terms of how many tiles it spans
	 * @param tilesToInvert the minimum amount of tiles the player will need to click on to win
	 * @param tile the tile type to be drawn onto the frame
	 */
	public GameFrame(int gridWidth, int tileWidth, int tilesToInvert, Tile tile) throws Exception {
		setLayout(new FlowLayout());
		box = new JPanel();
		box.setLayout(new BorderLayout());
		add(box);

		JButton solveButton = new JButton("Solve");
		solveButton.setSize(50, 50);
		solveButton.addActionListener(createSolveButtonListener());
		box.add(solveButton, BorderLayout.EAST);

		solveText = new JTextArea(10, 10);
		box.add(solveText, BorderLayout.SOUTH);

		JButton randomizeButton = new JButton("Randomize");
		randomizeButton.setSize(50, 50);
		randomizeButton.addActionListener(createRandomizeButtonListener(tilesToInvert));
		box.add(randomizeButton, BorderLayout.CENTER);

		tilesInARow = gridWidth / tileWidth;
		GridIcon grid = new GridIcon(gridWidth, tile, tilesInARow);
		label = new GridLabel(grid, tileWidth, tilesInARow);
		label.randomize(tilesToInvert);
		add(label);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Creates an ActionListener that translates the 2D array of answers into a displayed string
	 * @return the ActionListener stated above
	 */
	private ActionListener createSolveButtonListener() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				boolean[][] answers = label.getAnswers();
				String tmp = "";
				//Create a string in which X is an answer and O is a dummy for all tiles
				for (int i = 0; i < tilesInARow; i++) {
					for (int j = 0; j < tilesInARow; j++) {
						if (answers[j][i]) {
							tmp += "X ";
						} else {
							tmp += "O ";
						}
					}
					tmp += "\n";
				}
				solveText.setText(tmp);
			}
		};
	}

	/**
	 * returns an ActionListener that randomizes the grid's tiles
	 * @param tilesToInvert the minimum amount of tiles the player will need to click to win
	 * @return the ActionListener stated above
	 */
	private ActionListener createRandomizeButtonListener(int tilesToInvert) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					//Java reflection
					Method m = label.getClass().getDeclaredMethod("randomize", int.class);
					m.invoke(label, tilesToInvert);
					repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}
}
