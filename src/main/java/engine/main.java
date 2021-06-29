package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board, false);


/*

		int i = 0;
		ArrayList<String> moves = engine.findMoveList(board, "WHITE");
		for(String move : moves) {
			Board simBoard = new Board(board);
			simBoard.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));

			ArrayList<String> moves2 = engine.findMoveList(simBoard, "BLACK");
			for(String move2 : moves2){
				Board simBoard2 = new Board(simBoard);
				simBoard2.makeMove(Util.convertCoordToNum(move2.substring(0, 2)), Util.convertCoordToNum(move2.substring(2)));
				if(i == 4005){
					System.out.println("HEJ");
				}

				ArrayList<String> moves3 = engine.findMoveList(simBoard2, "WHITE");
				for(String move3 : moves3){
					Board simBoard3 = new Board(simBoard2);
					simBoard3.makeMove(Util.convertCoordToNum(move3.substring(0, 2)), Util.convertCoordToNum(move3.substring(2)));
					i++;

					if(i == 4005){
						System.out.println("HEJ");
					}
					engine.evalPosition(simBoard3);
				}

			}
		}
*/


		/*
		board.makeMove(Util.convertCoordToNum("a2"), Util.convertCoordToNum("a3"));
		board.draw();
		*/
		/*
		board.draw();
		System.out.println(board.checkmate());
*/
		/*
		board.makeMove(51, 44);
		board.draw();

		System.out.println(engine.generateMoves(board, "BLACK"));
		System.out.println(engine.findMoveList(board, "BLACK"));
*/




		Stack<String> pv = new Stack<String>();
		double result = engine.alphaBetaMax(board, 3, -Double.MAX_VALUE, Double.MAX_VALUE, pv);
		while(pv.size() != 0){
			System.out.println(pv.pop());
		}
		System.out.println("RESULT: " + result);



/*
		for(int i = 0; i < 1; i++) {
			engine.generateMoves("WHITE");
		}

		*/
	}

}
