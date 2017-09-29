import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class SquareTileShape extends TileShape {

	public SquareTileShape(int x, int y, int width) {
		super(x, y, width);
		// TODO Auto-generated constructor stub
	}

	public TileShape newCopyTo(int x, int y) {
		return new SquareTileShape(x, y, getWidth());
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		int width = getWidth();
		int y = getY();
		int x = getX();
		Color color = getCol();
		Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, width);
		g.setColor(color);
		g.fill(rect);
		g.setColor(Color.BLACK);
		g.draw(rect);

		Line2D.Double line1 = new Line2D.Double(x, y, x + width, y + width);
		Line2D.Double line2 = new Line2D.Double(x + width, y, x, y + width);
		g.draw(line1);
		g.draw(line2);

		Rectangle2D.Double rect2 = new Rectangle2D.Double(x + (width / 4), y + (width / 4), width / 2, width / 2);
		g.fill(rect2);

		g.setColor(color);
		Rectangle2D.Double rect3 = new Rectangle2D.Double(x + (width / 3), y + (width / 3), width / 3, width / 3);
		g.fill(rect3);
	}
}
