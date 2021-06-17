package engine;

import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board, false);

		board.draw();
		board.makeMove(12, 28);
		board.draw();
		ArrayList<String> moves = engine.findMoveList("WHITE");
		String allMoves = new String();
		for(String move : moves){
			allMoves += move;
		}
		System.out.println(allMoves);
		board.draw();
		board.makeMove(27, 20);
		board.draw();




		/*
		long time = System.currentTimeMillis();
		for(int i = 0; i < 1; i++) {
			engine.generateMoves("WHITE");
		}
		System.out.println("Move generation took " + (System.currentTimeMillis()-time) + "ms.");
		*/
	}

}
