
public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board);
		engine.generateMoves("WHITE");
	}

}
