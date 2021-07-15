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
					engine.evalPosition(simBoard3, 30, 30);
				}
			}
		}
*/

		/*
		board.makeMove(Util.convertCoordToNum("a2"), Util.convertCoordToNum("a3"));
		int whiteMoves = engine.findMoveList(board, "WHITE").size();
		int blackMoves = engine.findMoveList(board, "BLACK").size();
		System.out.println(engine.evalPosition(board, whiteMoves, blackMoves));
		*/


/*
		ArrayList<Move> moves = engine.findMoveList(board, "WHITE");
		for(Move move : moves){
			System.out.print(Util.convertNumToCoord(move.from));
			System.out.println(Util.convertNumToCoord(move.to));
		}
		System.out.println(moves.size());
*/
		/*
		long time = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++){
			board.check();
		}
		System.out.println(System.currentTimeMillis()-time);
		*/
		/*
		 */



		long time = System.currentTimeMillis();
		ArrayList<Move> pv = new ArrayList<Move>();
		double result = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0, 0);
		System.out.println(System.currentTimeMillis()-time);
		for(Move move : pv){
			System.out.print(Util.convertNumToCoord(move.from));
			System.out.println(Util.convertNumToCoord(move.to));
		}
		System.out.println("RESULT: " + result);

	}

}
