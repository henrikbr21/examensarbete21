
public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board);
		
		long time = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++) {
			engine.generateMoves("WHITE");
		}
		System.out.println(System.currentTimeMillis()-time);
		
	}

}
