package tests;

import static org.junit.Assert.*;
import engine.*;

import org.junit.Test;

import engine.Engine;
import engine.Util;

import java.util.ArrayList;

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
		ArrayList<String> moves = engine.generateMoves("WHITE");

		assertEquals(30, moves.size());
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
		ArrayList<String> moves = engine.generateMoves("WHITE");

		assertEquals(47, moves.size());
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
		ArrayList<String> moves = engine.generateMoves("WHITE");

		assertEquals(51, moves.size());
	}

	@Test
	public void testCheck(){

		char[][] testBoard = new char[][] {
				{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', ' ', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', 'B', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'q', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', ' ', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board);
		ArrayList<String> moves = engine.generateMoves("WHITE");
		assertEquals(3, board.check());
	}
}