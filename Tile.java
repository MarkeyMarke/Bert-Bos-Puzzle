
import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Tile {

	/**
	 * Returns a rectangle that is used to get the bounds of the tile
	 * @return the rectangle used for acquiring bounds
	 */
	public Rectangle getBounds();

	/**
	 * Draws the tile in a given graphic
	 * @param g the graphic object
	 */
	void draw(Graphics2D g);

	/**
	 * Switches the color of the tile
	 */
	public void switchColor();

	/**
	 * A factory method that generates a new tile at a set location
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public TileShape newCopyTo(int x, int y);

}
