import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 * A class that simulates the game launcher
 * @author Mark
 *
 */
public class GameLauncher implements Runnable, Serializable {

	private String currentTileInput;
	private transient JLabel tileDesignInput;
	private transient Tile currentTile;
	private int currentSizeInput;
	private transient JLabel gridSizeInput;
	private final int TILEWIDTH = 100;
	private int currentGridSize;
	private JSlider difficultySlider;

	/**
	 * Initializes the game launcher
	 */
	public GameLauncher() {
		GameLauncher tmp = null;
		try {
			File f = new File("GameOptionSave.ser");
			if (f.exists()) {
				FileInputStream fileIn = new FileInputStream("GameOptionSave.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				tmp = (GameLauncher) in.readObject();
				in.close();
				fileIn.close();
			}
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("GameLauncher class not found");
			c.printStackTrace();
			return;
		}
		if (tmp != null) {
			currentTileInput = "Classic";
			currentTile = new TileShape(0, 0, 100);
			currentSizeInput = tmp.currentSizeInput;
			currentGridSize = tmp.currentGridSize;

		} else {
			currentTileInput = "Classic";
			currentTile = new TileShape(0, 0, 100);
			currentSizeInput = 5;
			currentGridSize = TILEWIDTH * currentSizeInput;
		}
	}

	/**
	 * Returns an ActionListener that changes the tiles used for the game
	 * @param newPattern a string that represents the name of the desired tile design
	 * @return the ActionListener
	 */
	public ActionListener createPatternButtonListener(String newPattern) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if (newPattern.equals("Square")) {
					currentTile = new SquareTileShape(0, 0, 100);
				} else if (newPattern.equals("Circle")) {
					currentTile = new CircleTileShape(0, 0, 100);
				} else {
					currentTile = new TileShape(0, 0, 100);
				}
				currentTileInput = newPattern;
				tileDesignInput.setText("Tile design: " + currentTileInput);
				tileDesignInput.repaint();
			}
		};
	}

	/**
	 * Returns an ActionListener that sets a new size for the grid
	 * @param newSize the new n x n size of the grid
	 * @return the ActionListener
	 */
	public ActionListener createSizeButtonListener(int newSize) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				currentSizeInput = newSize;
				currentGridSize = TILEWIDTH * currentSizeInput;
				gridSizeInput.setText("Grid Size: " + currentSizeInput + "x" + currentSizeInput);
				gridSizeInput.repaint();
			}
		};
	}

	/**
	 * Returns an ActionListener that starts the game
	 * @return the ActionListener
	 */
	public ActionListener launchButtonListener() {
		GameLauncher e = this;
		return new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					FileOutputStream fileOut = new FileOutputStream("GameOptionSave.ser");
					ObjectOutputStream out = new ObjectOutputStream(fileOut);
					out.writeObject(e);
					out.close();
					fileOut.close();
				} catch (IOException i) {
					i.printStackTrace();
				}
				GameFrame frame;
				try {
					frame = new GameFrame(currentGridSize, TILEWIDTH, difficultySlider.getValue(), currentTile);
					frame.setTitle("Inversion Game");
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
	}

	/**
	 * Executes the game as a thread
	 */
	@Override
	public void run() {
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());

		// Panel for the three options
		JPanel optionPanel = new JPanel();
		optionPanel.setLayout(new BorderLayout());
		frame.add(optionPanel);

		// Options for tile pattern
		JPanel tileOptions = new JPanel();
		optionPanel.add(tileOptions, BorderLayout.NORTH);

		JLabel tilePrompt = new JLabel("Pick a Tile Design:");
		tileOptions.add(tilePrompt);

		JButton classicTile = new JButton("Classic");
		classicTile.addActionListener(createPatternButtonListener("Classic"));
		tileOptions.add(classicTile);

		JButton squareTile = new JButton("Square");
		squareTile.addActionListener(createPatternButtonListener("Square"));
		tileOptions.add(squareTile);

		JButton circleTile = new JButton("Circle");
		circleTile.addActionListener(createPatternButtonListener("Circle"));
		tileOptions.add(circleTile);

		// options for grid size
		JPanel sizeOptions = new JPanel();
		optionPanel.add(sizeOptions, BorderLayout.CENTER);

		JLabel sizePrompt = new JLabel("Pick a Grid Size:");
		sizeOptions.add(sizePrompt);

		JButton threeSize = new JButton("3x3");
		threeSize.addActionListener(createSizeButtonListener(3));
		sizeOptions.add(threeSize);

		JButton fourSize = new JButton("4x4");
		fourSize.addActionListener(createSizeButtonListener(4));
		sizeOptions.add(fourSize);

		JButton fiveSize = new JButton("5x5");
		fiveSize.addActionListener(createSizeButtonListener(5));
		sizeOptions.add(fiveSize);

		// Difficulty options
		JPanel difficultyOptions = new JPanel();
		difficultyOptions.setLayout(new BorderLayout());
		optionPanel.add(difficultyOptions, BorderLayout.SOUTH);

		JLabel difficultyPrompt = new JLabel("Difficulty:");
		difficultyOptions.add(difficultyPrompt, BorderLayout.NORTH);

		difficultySlider = new JSlider(1, 9);
		difficultyOptions.add(difficultySlider, BorderLayout.CENTER);

		// Right side of frame
		JPanel selectedAndLaunch = new JPanel();
		selectedAndLaunch.setLayout(new BorderLayout());
		frame.add(selectedAndLaunch);

		tileDesignInput = new JLabel("Tile Design: " + currentTileInput);
		selectedAndLaunch.add(tileDesignInput, BorderLayout.NORTH);

		gridSizeInput = new JLabel("Grid Size: " + currentSizeInput + "x" + currentSizeInput);
		selectedAndLaunch.add(gridSizeInput, BorderLayout.CENTER);

		// launch game
		JButton launch = new JButton("Start Game");
		launch.addActionListener(launchButtonListener());
		selectedAndLaunch.add(launch, BorderLayout.SOUTH);

		// display frame
		frame.setTitle("Inversion Launcher");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
