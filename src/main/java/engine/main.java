package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("WHITE", board, false);

		System.out.println(engine.generateMoves(board, "WHITE"));

		/*
		Stack<String> pv = new Stack<String>();
		double result = engine.alphaBetaMax(board, 2, -Double.MAX_VALUE, Double.MAX_VALUE, pv);
		while(pv.size() != 0){
			System.out.println(pv.pop());
		}
		System.out.println(result);
*/



		/*

		for(int i = 0; i < 1; i++) {
			engine.generateMoves("WHITE");
		}

		*/
	}

}
