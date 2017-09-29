import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

/**
 * A class that simulates a tile on a grid
 * @author Mark
 *
 */
public class TileShape implements Tile {

	private int x;
	private int y;
	private int width;
	private Color color;

	/**
	 * Creates a tile width a x/y coordinate, and a set width
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param width the width of the tile
	 */
	public TileShape(int x, int y, int width) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.color = Color.RED;
	}

	/**
	 * Returns a rectangle that is used to get the bounds of the tile
	 * @return the rectangle used for acquiring bounds
	 */
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, width);
	}

	/**
	 * Switches the color of the tile
	 */
	public void switchColor() {
		if (color.equals(Color.RED)) {
			color = Color.BLUE;
		} else {
			color = Color.RED;
		}
	}

	/**
	 * A factory method that generates a new tile at a set location
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public TileShape newCopyTo(int x, int y) {
		return new TileShape(x, y, width);
	}

	/**
	 * Draws the tile in a given graphic
	 * @param g the graphic object
	 */
	@Override
	public void draw(Graphics2D g) {
		Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, width);
		g.setColor(color);
		g.fill(rect);
		g.setColor(Color.BLACK);
		g.draw(rect);
	}

	/**
	 * Returns the x coordinate of the tile
	 * @return the x coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Returns the y coordinate of the tile
	 * @return the y coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns the color of the tile
	 * @return the color of the tile
	 */
	public Color getCol() {
		return color;
	}

	/**
	 * Returns the width of the tile
	 * @return the width of the tile
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * An override of Object's equals method
	 * @return true if the objects match, otherwise false
	 */
	public boolean equals(Object o) {
		if (o == null)
			return false;
		TileShape thatTile = (TileShape) o;
		return this.color.equals(thatTile.getCol()) && this.width == thatTile.getWidth();
	}
}
