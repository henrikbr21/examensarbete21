package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.Engine;
import engine.Util;

public class BoardTests {

	@Test
	public void test() {
		char[][] board = new char[][] {
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'0', '0', '0', '0', '0', '0', '0', '0'},
			{'1', '0', '0', '0', '0', '0', '0', '0'},
		};
		long bitboard = Util.boardFromArray(board);
		long bottomLeft1 = 9223372036854775807L;
		Util.draw(bottomLeft1);
		assertEquals(bitboard, ~bottomLeft1);
			
		
	}

}
