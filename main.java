
public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("BLACK", board);
		engine.generateMoves("BLACK");
		
		
	}

}
