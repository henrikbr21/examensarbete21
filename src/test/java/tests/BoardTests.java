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
		Engine engine = new Engine("WHITE", board, false);
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
		Engine engine = new Engine("WHITE", board, false);
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
		Engine engine = new Engine("WHITE", board, false);
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
		Engine engine = new Engine("WHITE", board, false);
		ArrayList<String> moves = engine.generateMoves("WHITE");
		assertEquals(3, board.check());
	}

	@Test
	public void testCastlingValidation(){

		char[][] testBoard = new char[][] {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false);

		board.makeMove(0, 1);
		assertEquals(false, board.castleWQValid);
		board.makeMove(7, 15);
		assertEquals(false, board.castleWKValid);
		board.makeMove(56, 57);
		assertEquals(false, board.castleBQValid);
		board.makeMove(63, 62);
		assertEquals(false, board.castleBKValid);
	}

	@Test
	public void testCheckMate1(){

		char[][] testBoard = {
				{'r', 'n', 'b', ' ', 'k', 'b', 'n', ' '},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'q', ' ', ' ', 'P', 'P', 'P', 'P'},
				{' ', 'r', ' ', ' ', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false);

		assertEquals(1, board.checkmate());
	}

	@Test
	public void testCheckMate2(){

		char[][] testBoard = {
				{'Q', ' ', ' ', ' ', 'k', 'b', 'n', ' '},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false);

		assertEquals(2, board.checkmate());
	}

}