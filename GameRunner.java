/**
 * A class that creates a thread of the game and executes it
 * @author Mark
 *
 */
public class GameRunner {

	/**
	 * Creates a thread of the game and executes it
	 */
	public static void main(String[] args) {
		Thread t = new Thread(new GameLauncher());
		t.start();
	}
}
