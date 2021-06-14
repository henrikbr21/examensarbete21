package tests;

import static org.junit.Assert.*;
import engine.*;

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

	@Test
	public void testMoveGeneration1() {

		char[][] testBoard = new char[][] {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', 'P', ' '},
				{' ', ' ', ' ', ' ', 'P', 'N', 'B', ' '},
				{'P', 'P', 'P', 'P', ' ', 'P', ' ', 'P'},
				{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board);
		String moves = engine.generateMoves("WHITE");

		int count = 0;
		for (int i = 0; i < moves.length(); i++) {
			if (moves.charAt(i) == ' ') {
				count++;
			}
		}
		assertEquals(30, count);
	}

	@Test
	public void testMoveGeneration2() {

		char[][] testBoard = new char[][] {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
				{'R', ' ', 'N', 'B', 'B', 'N', 'P', ' '},
				{'P', 'Q', 'P', 'P', ' ', 'P', 'P', 'P'},
				{' ', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board);
		String moves = engine.generateMoves("WHITE");

		int count = 0;
		for (int i = 0; i < moves.length(); i++) {
			if (moves.charAt(i) == ' ') {
				count++;
			}
		}
		assertEquals(47, count);
	}

	@Test
	public void testMoveGeneration3() {

		char[][] testBoard = new char[][] {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', 'B', ' ', ' ', ' ', ' ', ' ', 'N'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'N', ' ', ' ', ' ', 'B', ' ', 'P', ' '},
				{'P', ' ', 'P', 'P', 'K', 'Q', 'P', ' '},
				{'R', ' ', ' ', ' ', ' ', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board);
		String moves = engine.generateMoves("WHITE");

		int count = 0;
		for (int i = 0; i < moves.length(); i++) {
			if (moves.charAt(i) == ' ') {
				count++;
			}
		}
		assertEquals(51, count);
	}
}