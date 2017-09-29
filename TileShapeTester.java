import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.Rectangle;

import org.junit.Test;

public class TileShapeTester {

	@Test
	public void test() {
		TileShape tile = new TileShape(0,54,100);
		assertEquals(0, tile.getX());
		assertEquals(54, tile.getY());
		assertEquals(100, tile.getWidth());
	}

	@Test
	public void test2(){
		TileShape tile = new TileShape(0,0,200);
		assertEquals(Color.RED, tile.getCol());
		tile.switchColor();
		assertEquals(Color.BLUE, tile.getCol());
	}
	
	@Test
	public void test3(){
		TileShape tile = new TileShape(1,1,57);
		TileShape newTile = tile.newCopyTo(0, 3);
		assertEquals(0, newTile.getX());
		assertEquals(3, newTile.getY());
		assertEquals(Color.RED, newTile.getCol());
	}
	
	@Test
	public void test4(){
		TileShape tile = new TileShape(0,0,100);
		Rectangle rec = tile.getBounds();
		assertEquals(100, (int)rec.getWidth());
	}
}
