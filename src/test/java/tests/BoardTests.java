package tests;

import static org.junit.Assert.*;
import engine.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import engine.Engine;
import engine.Util;

import java.util.ArrayList;

public class BoardTests {
	TPT tpt = new TPT();

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
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.generateMoves(board,"WHITE");

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
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.generateMoves(board,"WHITE");

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
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.generateMoves(board,"WHITE");

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
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.generateMoves(board,"WHITE");
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
		Engine engine = new Engine("WHITE", board, false, tpt);

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
		Engine engine = new Engine("WHITE", board, false, tpt);
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
		Engine engine = new Engine("WHITE", board, false, tpt);

		assertEquals(2, board.checkmate());
	}

	@Test
	public void testCastlingRemoval(){
		char[][] testBoard = {
				{'r', 'n', ' ', ' ', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false, tpt);

		assertEquals(7, engine.findMoveList(board,"WHITE").size());
	}

	@Test
	public void testCastlingRemoval2(){
		char[][] testBoard = {
				{' ', 'n', ' ', ' ', 'k', 'b', 'n', ' '},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'r', ' ', 'r', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', ' ', ' ', 'P', ' ', 'P'},
				{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false, tpt);

		assertEquals(3, engine.findMoveList(board,"WHITE").size());
	}

	@Test
	public void testCastlingRemoval3(){
		char[][] testBoard = {
				{' ', 'n', ' ', ' ', 'k', 'b', 'n', ' '},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'r', 'r', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', ' ', ' ', ' ', ' ', 'P'},
				{'R', 'N', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false, tpt);

		assertEquals(2, engine.findMoveList(board,"WHITE").size());
	}

	@Test
	public void testCastlingRemoval4(){
		char[][] testBoard = {
				{'r', ' ', ' ', 'b', 'k', ' ', ' ', 'r'},
				{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'R', 'R', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("BLACK", board, false, tpt);

		assertEquals(2, engine.findMoveList(board,"BLACK").size());
	}

	@Test
	public void testCastlingRemoval5(){
		char[][] testBoard = {
				{'r', ' ', ' ', 'b', 'k', ' ', ' ', 'r'},
				{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'R', ' ', 'R', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("BLACK", board, false, tpt);

		assertEquals(4, engine.findMoveList(board,"BLACK").size());
	}

	@Test
	public void testCastlingRemoval6(){
		char[][] testBoard = {
				{'r', ' ', ' ', ' ', 'k', 'b', ' ', 'r'},
				{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', 'R', ' ', 'R', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("BLACK", board, false, tpt);

		assertEquals(4, engine.findMoveList(board,"BLACK").size());
	}

	@Test
	public void testCastlingRemoval7(){
		char[][] testBoard = {
				{'r', ' ', ' ', ' ', 'k', 'b', ' ', 'r'},
				{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', 'R', 'R', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("BLACK", board, false, tpt);

		assertEquals(2, engine.findMoveList(board,"BLACK").size());
	}

	@Test
	public void testCastlingRemoval8(){
		char[][] testBoard = {
				{'r', ' ', ' ', ' ', 'k', 'b', ' ', 'r'},
				{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', 'R', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("BLACK", board, false, tpt);
		MoveArrayList moves = engine.findMoveList(board, "BLACK");
		boolean faultyMoveExists = false;
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			if(move.from == Util.convertCoordToNum("e8")){
				if(move.to == Util.convertCoordToNum("c8")){
					faultyMoveExists = true;
				}
			}
		}
		assertFalse(faultyMoveExists);
	}

	@Test
	public void testCastlingRemoval9(){
		char[][] testBoard = {
				{'r', ' ', ' ', ' ', 'k', ' ', ' ', 'r'},
				{'p', ' ', ' ', ' ', ' ', ' ', ' ', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', 'R', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', 'N', 'B', 'Q', 'K', 'B', 'N', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("BLACK", board, false, tpt);
		MoveArrayList moves = engine.findMoveList(board, "BLACK");
		boolean faultyMoveExists = false;
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			if(move.from == Util.convertCoordToNum("e8")){
				if(move.to == Util.convertCoordToNum("g8")){
					faultyMoveExists = true;
				}
			}
		}
		assertFalse(faultyMoveExists);
	}

	@Test
	public void testCastlingRemoval10(){
		char[][] testBoard = {
				{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', 'r', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
				{'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.findMoveList(board, "WHITE");
		boolean faultyMoveExists = false;
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			if(move.from == Util.convertCoordToNum("e1")){
				if(move.to == Util.convertCoordToNum("c1")){
					faultyMoveExists = true;
				}
			}
		}
		assertFalse(faultyMoveExists);
	}

	@Test
	public void testCastlingRemoval11(){
		char[][] testBoard = {
				{' ', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', 'r', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', ' ', ' ', ' ', ' ', ' ', ' ', 'P'},
				{'R', ' ', ' ', ' ', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.findMoveList(board, "WHITE");
		boolean faultyMoveExists = false;
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			if(move.from == Util.convertCoordToNum("e1")){
				if(move.to == Util.convertCoordToNum("g1")){
					faultyMoveExists = true;
				}
			}
		}
		assertFalse(faultyMoveExists);
	}

	@Test
	public void testEnPassant(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		board.makeMove(51, 35);
		assertEquals(true, board.enPassant);
		Engine engine = new Engine("WHITE", board, false, tpt);
		MoveArrayList moves = engine.findMoveList(board,"WHITE");
		String allMoves = new String();
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			allMoves += Util.convertNumToCoord(move.from);
			allMoves += Util.convertNumToCoord(move.to);
		}
		assertThat(allMoves, CoreMatchers.containsString("e5d6"));
		board.makeMove(36, 43);
		assertEquals(0, board.BP & AttackSets.getPosition(35)); //black pawn removed
	}

}