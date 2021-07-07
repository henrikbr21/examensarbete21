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
		/*
		board.makeMove(Util.convertCoordToNum("a2"), Util.convertCoordToNum("a3"));
		int whiteMoves = engine.findMoveList(board, "WHITE").size();
		int blackMoves = engine.findMoveList(board, "BLACK").size();
		System.out.println(engine.evalPosition(board, whiteMoves, blackMoves));
		*/
		board.makeMove(Util.convertCoordToNum("e2"), Util.convertCoordToNum("e3"));
		board.makeMove(Util.convertCoordToNum("e7"), Util.convertCoordToNum("e5"));
		board.makeMove(Util.convertCoordToNum("d1"), Util.convertCoordToNum("h5"));
		board.makeMove(Util.convertCoordToNum("d7"), Util.convertCoordToNum("d6"));
		board.makeMove(Util.convertCoordToNum("f1"), Util.convertCoordToNum("b5"));
		board.makeMove(Util.convertCoordToNum("b8"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("g1"), Util.convertCoordToNum("e2"));
		board.makeMove(Util.convertCoordToNum("g7"), Util.convertCoordToNum("g6"));
		board.makeMove(Util.convertCoordToNum("h5"), Util.convertCoordToNum("f3"));
		board.makeMove(Util.convertCoordToNum("g8"), Util.convertCoordToNum("e7"));
		board.makeMove(Util.convertCoordToNum("f3"), Util.convertCoordToNum("f6"));
		board.makeMove(Util.convertCoordToNum("b7"), Util.convertCoordToNum("b6"));
		board.makeMove(Util.convertCoordToNum("b5"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("e7"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("f6"), Util.convertCoordToNum("h8"));
		board.makeMove(Util.convertCoordToNum("g6"), Util.convertCoordToNum("g5"));
		board.makeMove(Util.convertCoordToNum("d2"), Util.convertCoordToNum("d3"));
		board.makeMove(Util.convertCoordToNum("g5"), Util.convertCoordToNum("g4"));
		board.makeMove(Util.convertCoordToNum("e3"), Util.convertCoordToNum("e4"));
		board.makeMove(Util.convertCoordToNum("e8"), Util.convertCoordToNum("d7"));
		board.makeMove(Util.convertCoordToNum("c1"), Util.convertCoordToNum("h6"));
		board.makeMove(Util.convertCoordToNum("f8"), Util.convertCoordToNum("h6"));
		board.makeMove(Util.convertCoordToNum("h8"), Util.convertCoordToNum("d8"));
		board.makeMove(Util.convertCoordToNum("d7"), Util.convertCoordToNum("d8"));
		board.makeMove(Util.convertCoordToNum("f2"), Util.convertCoordToNum("f4"));
		board.makeMove(Util.convertCoordToNum("e5"), Util.convertCoordToNum("f4"));
		board.makeMove(Util.convertCoordToNum("b1"), Util.convertCoordToNum("d2"));
		board.makeMove(Util.convertCoordToNum("c6"), Util.convertCoordToNum("e5"));
		board.makeMove(Util.convertCoordToNum("d3"), Util.convertCoordToNum("d4"));
		board.makeMove(Util.convertCoordToNum("e5"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("d2"), Util.convertCoordToNum("c4"));
		board.makeMove(Util.convertCoordToNum("d8"), Util.convertCoordToNum("d7"));
		board.makeMove(Util.convertCoordToNum("d4"), Util.convertCoordToNum("d5"));
		board.makeMove(Util.convertCoordToNum("c6"), Util.convertCoordToNum("b8"));
		board.makeMove(Util.convertCoordToNum("a2"), Util.convertCoordToNum("a4"));
		board.makeMove(Util.convertCoordToNum("h6"), Util.convertCoordToNum("g5"));
		board.makeMove(Util.convertCoordToNum("e1"), Util.convertCoordToNum("g1"));
		board.makeMove(Util.convertCoordToNum("b8"), Util.convertCoordToNum("a6"));
		board.makeMove(Util.convertCoordToNum("a1"), Util.convertCoordToNum("a3"));
		board.makeMove(Util.convertCoordToNum("c7"), Util.convertCoordToNum("c5"));
		board.makeMove(Util.convertCoordToNum("d5"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("d7"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("e2"), Util.convertCoordToNum("d4"));
		board.makeMove(Util.convertCoordToNum("c6"), Util.convertCoordToNum("b7"));
		board.makeMove(Util.convertCoordToNum("c4"), Util.convertCoordToNum("d6"));
		board.makeMove(Util.convertCoordToNum("b7"), Util.convertCoordToNum("c7"));
		board.makeMove(Util.convertCoordToNum("d4"), Util.convertCoordToNum("b5"));
		board.makeMove(Util.convertCoordToNum("c7"), Util.convertCoordToNum("d7"));
		board.makeMove(Util.convertCoordToNum("a3"), Util.convertCoordToNum("d3"));
		board.makeMove(Util.convertCoordToNum("a6"), Util.convertCoordToNum("c5"));
		board.makeMove(Util.convertCoordToNum("d3"), Util.convertCoordToNum("c3"));
		board.makeMove(Util.convertCoordToNum("c8"), Util.convertCoordToNum("a6"));
		board.makeMove(Util.convertCoordToNum("d6"), Util.convertCoordToNum("f7"));
		board.makeMove(Util.convertCoordToNum("a6"), Util.convertCoordToNum("b5"));
		board.makeMove(Util.convertCoordToNum("a4"), Util.convertCoordToNum("b5"));
		board.makeMove(Util.convertCoordToNum("g5"), Util.convertCoordToNum("f6"));
		board.makeMove(Util.convertCoordToNum("f1"), Util.convertCoordToNum("d1"));
		board.makeMove(Util.convertCoordToNum("d7"), Util.convertCoordToNum("e8"));
		board.makeMove(Util.convertCoordToNum("f7"), Util.convertCoordToNum("d6"));
		board.makeMove(Util.convertCoordToNum("e8"), Util.convertCoordToNum("f8"));
		board.makeMove(Util.convertCoordToNum("e4"), Util.convertCoordToNum("e5"));
		board.makeMove(Util.convertCoordToNum("f6"), Util.convertCoordToNum("e7"));
		board.makeMove(Util.convertCoordToNum("d6"), Util.convertCoordToNum("f5"));
		board.makeMove(Util.convertCoordToNum("e7"), Util.convertCoordToNum("g5"));
		board.makeMove(Util.convertCoordToNum("h2"), Util.convertCoordToNum("h4"));
		board.makeMove(Util.convertCoordToNum("g5"), Util.convertCoordToNum("d8"));
		board.makeMove(Util.convertCoordToNum("h4"), Util.convertCoordToNum("h5"));
		board.makeMove(Util.convertCoordToNum("h7"), Util.convertCoordToNum("h6"));
		board.makeMove(Util.convertCoordToNum("f5"), Util.convertCoordToNum("h6"));
		board.makeMove(Util.convertCoordToNum("d8"), Util.convertCoordToNum("e7"));
		board.makeMove(Util.convertCoordToNum("h6"), Util.convertCoordToNum("f5"));
		board.makeMove(Util.convertCoordToNum("e7"), Util.convertCoordToNum("d8"));
		board.makeMove(Util.convertCoordToNum("d1"), Util.convertCoordToNum("d6"));
		board.makeMove(Util.convertCoordToNum("c5"), Util.convertCoordToNum("e4"));
		board.makeMove(Util.convertCoordToNum("d6"), Util.convertCoordToNum("d8"));
		board.makeMove(Util.convertCoordToNum("a8"), Util.convertCoordToNum("d8"));
		board.makeMove(Util.convertCoordToNum("c3"), Util.convertCoordToNum("d3"));
		board.makeMove(Util.convertCoordToNum("d8"), Util.convertCoordToNum("a8"));
		board.makeMove(Util.convertCoordToNum("d3"), Util.convertCoordToNum("d4"));
		board.makeMove(Util.convertCoordToNum("f4"), Util.convertCoordToNum("f3"));
		board.makeMove(Util.convertCoordToNum("d4"), Util.convertCoordToNum("e4"));
		board.makeMove(Util.convertCoordToNum("a8"), Util.convertCoordToNum("c8"));
		board.makeMove(Util.convertCoordToNum("e4"), Util.convertCoordToNum("g4"));
		board.makeMove(Util.convertCoordToNum("c8"), Util.convertCoordToNum("c5"));
		board.makeMove(Util.convertCoordToNum("c2"), Util.convertCoordToNum("c3"));
		board.makeMove(Util.convertCoordToNum("c5"), Util.convertCoordToNum("e5"));
		board.makeMove(Util.convertCoordToNum("g4"), Util.convertCoordToNum("g5"));
		board.makeMove(Util.convertCoordToNum("e5"), Util.convertCoordToNum("e1"));
		board.makeMove(Util.convertCoordToNum("g1"), Util.convertCoordToNum("f2"));
		board.makeMove(Util.convertCoordToNum("e1"), Util.convertCoordToNum("e6"));
		board.makeMove(Util.convertCoordToNum("f5"), Util.convertCoordToNum("d4"));
		board.makeMove(Util.convertCoordToNum("e6"), Util.convertCoordToNum("e7"));
		board.makeMove(Util.convertCoordToNum("g5"), Util.convertCoordToNum("f5"));
		board.makeMove(Util.convertCoordToNum("f8"), Util.convertCoordToNum("g7"));
		board.makeMove(Util.convertCoordToNum("f5"), Util.convertCoordToNum("g5"));
		board.makeMove(Util.convertCoordToNum("g7"), Util.convertCoordToNum("h8"));
		board.makeMove(Util.convertCoordToNum("g2"), Util.convertCoordToNum("f3"));
		board.makeMove(Util.convertCoordToNum("e7"), Util.convertCoordToNum("f7"));
		board.makeMove(Util.convertCoordToNum("d4"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("f7"), Util.convertCoordToNum("h7"));
		board.makeMove(Util.convertCoordToNum("f3"), Util.convertCoordToNum("f4"));
		board.makeMove(Util.convertCoordToNum("h7"), Util.convertCoordToNum("c7"));
		board.makeMove(Util.convertCoordToNum("g5"), Util.convertCoordToNum("g1"));
		board.makeMove(Util.convertCoordToNum("c7"), Util.convertCoordToNum("d7"));
		board.makeMove(Util.convertCoordToNum("c6"), Util.convertCoordToNum("d4"));
		board.makeMove(Util.convertCoordToNum("d7"), Util.convertCoordToNum("h7"));
		board.makeMove(Util.convertCoordToNum("d4"), Util.convertCoordToNum("c6"));
		board.makeMove(Util.convertCoordToNum("h7"), Util.convertCoordToNum("d7")); //Schackdatorns svar är knas? d7e8??

		board.draw();
		System.out.println(engine.findMoveList(board, "BLACK"));

		long time = System.currentTimeMillis();
		ArrayList<String> pv = new ArrayList<String>();
		double result = engine.alphaBetaMax(board, 3, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0, 0);
		System.out.println(pv);
		System.out.println("RESULT: " + result);
		System.out.println(System.currentTimeMillis()-time);


	}

}
