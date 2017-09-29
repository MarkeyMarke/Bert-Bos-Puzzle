import static org.junit.Assert.*;

import org.junit.Test;

public class GridComputerTester {

	@Test
	public void test() {
		GridComputer cpu = new GridComputer(3);
		boolean[][] answers = cpu.giveAnswers();
		for(int i=0; i<3; i++)
		{
			for(int j=0; j<3; j++)
			{
				assertEquals(false, answers[i][j]);
			}
		}
	}
	
	@Test
	public void test2() {
		GridComputer cpu = new GridComputer(4);
		boolean[][] answers = cpu.giveAnswers();
		assertEquals(false, answers[0][3]);
		cpu.update(0, 3);
		boolean[][] answers2 = cpu.giveAnswers();
		assertEquals(true, answers2[0][3]);
	}
}
