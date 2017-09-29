import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class CircleTileShape extends TileShape {

	public CircleTileShape(int x, int y, int width) {
		super(x, y, width);
	}

	public TileShape newCopyTo(int x, int y) {
		return new CircleTileShape(x, y, getWidth());
	}

	@Override
	public void draw(Graphics2D g) {
		int width = getWidth();
		int y = getY();
		int x = getX();
		Color color = getCol();
		Rectangle2D.Double rect = new Rectangle2D.Double(x, y, width, width);
		g.setColor(color);
		g.fill(rect);
		g.setColor(Color.BLACK);
		g.draw(rect);

		Ellipse2D.Double circ1 = new Ellipse2D.Double(x + (width / 4), y + (width / 4), width / 2, width / 2);
		g.fill(circ1);

		g.setColor(color);
		Ellipse2D.Double circ2 = new Ellipse2D.Double(x + (width / 3), y + (width / 3), width / 3, width / 3);
		g.fill(circ2);
	}
}
