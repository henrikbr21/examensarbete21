package engine;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board, false);

		long time = System.currentTimeMillis();

		System.out.println("Move count: " + engine.findMoveList("WHITE").size());
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
