package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board, false);

		/*
		ArrayList<String> moves = engine.findMoveList(board, "WHITE");
		for(String move : moves){
			Board simBoard = new Board(board);
			simBoard.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));
			ArrayList<String> moves2 = engine.findMoveList(simBoard, "BLACK");

			for(String move2 : moves2){
				Board simBoard2 = new Board(board);
				simBoard2.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));
				ArrayList<String> moves3 = engine.findMoveList(simBoard2, "WHITE");

				for(String move3 : moves3){
					Board simBoard3 = new Board(board);
					simBoard3.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));
					engine.evalPosition(simBoard3);

				}
			}
		}
*/

		System.out.println("CHECKMATE: " + board.checkmate());
		System.out.println(engine.generateMoves(board, "WHITE"));

		/*
		long time = System.currentTimeMillis();
		ArrayList<String> pv = new ArrayList<String>();
		double result = engine.alphaBetaMax(board, 3, -Double.MAX_VALUE, Double.MAX_VALUE, pv, -1);
		System.out.println(pv);
		System.out.println("RESULT: " + result);
		System.out.println(System.currentTimeMillis()-time);
*/

	}

}
