import static org.junit.Assert.*;

import org.junit.Test;

public class GridLabelTester {

	@Test
	public void test() {
		TileShape tile = new TileShape(0,0,100);
		GridIcon icon = new GridIcon(300, tile, 3);
		GridLabel gl = new GridLabel(icon, 100, 3);
		assertEquals(true, icon.isSolved());
		gl.invertTile(2, 2);
		assertEquals(false, icon.isSolved());
	}

	@Test
	public void test2() throws Exception {
		TileShape tile = new TileShape(0,0,100);
		GridIcon icon = new GridIcon(300, tile, 3);
		GridLabel gl = new GridLabel(icon, 100, 3);
		gl.randomize(0);
		assertEquals(true, icon.isSolved());
		gl.randomize(1);
		assertEquals(false, icon.isSolved());
	}
	
	@Test
	public void test3() {
		TileShape tile = new TileShape(0,0,100);
		GridIcon icon = new GridIcon(300, tile, 3);
		GridLabel gl = new GridLabel(icon, 100, 3);
		gl.invertTile(0, 0);
		boolean[][] answers = gl.getAnswers();
		assertEquals(false, answers[0][0]);
	}
}
