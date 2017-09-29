import static org.junit.Assert.*;

import org.junit.Test;

public class GridIconTester {

	@Test
	public void test() {
		GridIcon icon = new GridIcon(300, new TileShape(0,0,100), 3);
		assertEquals(300, icon.getIconHeight());
		assertEquals(300, icon.getIconWidth());
	}

	@Test
	public void test2() {
		GridIcon icon = new GridIcon(300, new TileShape(0,0,100), 3);
		assertEquals(true, icon.isSolved());
		icon.switchTile(0, 0);
		assertEquals(false, icon.isSolved());
		icon.reset();
		assertEquals(true, icon.isSolved());
		icon.switchTile(0, 0);
		icon.setNewTileDesign(new SquareTileShape(0,0,100));
		assertEquals(true,icon.isSolved());
	}
}
