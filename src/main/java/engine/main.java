package engine;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board);

		long time = System.currentTimeMillis();
		board.draw();
		board.makeMove(14, 7);
		board.draw();
		System.out.println();
		/*
		for(int i = 0; i < 500000; i++){
			board.makeMove(10, 18);
			board.makeMove(18, 10);
		}
		*/

		System.out.println("Move generation took " + (System.currentTimeMillis()-time) + "ms.");
		/*
		long time = System.currentTimeMillis();
		for(int i = 0; i < 1; i++) {
			engine.generateMoves("WHITE");
		}
		System.out.println("Move generation took " + (System.currentTimeMillis()-time) + "ms.");
		*/
	}

}
