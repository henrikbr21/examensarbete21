package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class main {

	public static void main(String[] args) {
		Board board = new Board();
		Engine engine = new Engine("BLACK", board, false);


		ArrayList<String> var = new ArrayList<String>();
		double result = engine.search(board, 5, -Double.MAX_VALUE, Double.MAX_VALUE, "WHITE", var, true);
		System.out.println(var);



		/*

		for(int i = 0; i < 1; i++) {
			engine.generateMoves("WHITE");
		}

		*/
	}

}
