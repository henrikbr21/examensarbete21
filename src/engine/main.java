
public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("BLACK", board);
		
		long time = System.currentTimeMillis();
		for(int i = 0; i < 1; i++) {
			engine.generateMoves("BLACK");
		}
		//System.out.println(System.currentTimeMillis()-time);
		
	}

}
