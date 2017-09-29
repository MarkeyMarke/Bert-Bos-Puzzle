import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;
import javax.swing.JFrame;

/**
 * A class that simulates a group of tiles as grid icon
 * @author Mark
 *
 */
public class GridIcon implements Icon {

	private Tile tileDesign;
	private TileShape[][] tiles;
	private int pixelWidth;
	private int tilesInARow;

	/**
	 * Creates a grid icon
	 * @param pixelWidth the pixel width of the entire grid
	 * @param tileDesign the desired tile to use
	 * @param tilesInARow the amount of tiles per row
	 */
	public GridIcon(int pixelWidth, Tile tileDesign, int tilesInARow) {
		tiles = new TileShape[tilesInARow][tilesInARow];
		this.tileDesign = tileDesign;
		this.pixelWidth = pixelWidth;
		this.tilesInARow = tilesInARow;

		int d = pixelWidth / tilesInARow;
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				tiles[i][j] = tileDesign.newCopyTo(i * d, j * d);
			}
		}
	}

	/**
	 * Sets a new tile design for the grid icon, and generates an unscrambled grid
	 * @param tileDesign the desired tile design
	 */
	public void setNewTileDesign(Tile tileDesign) {
		this.tileDesign = tileDesign;
		int d = pixelWidth / tilesInARow;
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				tiles[i][j] = this.tileDesign.newCopyTo(i * d, j * d);
			}
		}
	}

	/**
	 * Resets all of the tiles by setting the same tile design
	 */
	public void reset() {
		setNewTileDesign(tileDesign);
	}

	/**
	 * Checks if all tiles are matching
	 * @return true if all tiles are the same, false if the puzzle isn't solved yet
	 */
	public boolean isSolved() {
		Tile sampleTile = tiles[0][0];
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				if (!sampleTile.equals(tiles[i][j])) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * Switches the color of a specific tile in the grid
	 * @param i the horizontal i component of the grid
	 * @param j the vertical j component of the grid
	 */
	public void switchTile(int i, int j) {
		tiles[i][j].switchColor();
	}

	/**
	 * Paints the icon to a graphic object
	 */
	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2 = (Graphics2D) g;
		for (int i = 0; i < tilesInARow; i++) {
			for (int j = 0; j < tilesInARow; j++) {
				tiles[i][j].draw(g2);
				;
			}
		}
	}

	/**
	 * Returns the width of the icon
	 * @return the width
	 */
	@Override
	public int getIconWidth() {
		return pixelWidth;
	}

	/**
	 * Returns the height of the icon
	 * @return the height
	 */
	@Override
	public int getIconHeight() {
		return pixelWidth;
	}
}
