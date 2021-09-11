package tests;

import static org.junit.Assert.*;
import engine.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import engine.Engine;
import engine.Util;

import java.util.ArrayList;

public class BoardTests {
	TPT tpt = new TPT();

	@Before
	public void setUp(){
		AttackSets.init();
	}
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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);

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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);

		assertEquals(2, board.checkmate());
	}

	@Test
	public void testCheckMate3(){

		char[][] testBoard = {
				{' ', ' ', ' ', ' ', ' ', 'R', ' ', 'k'},
				{' ', ' ', ' ', ' ', ' ', ' ', 'p', ' '},
				{' ', ' ', ' ', ' ', 'p', ' ', ' ', ' '},
				{'p', ' ', ' ', 'p', ' ', ' ', 'N', ' '},
				{'P', ' ', 'r', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', 'P', 'b', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', 'B', ' ', ' ', ' ', 'q'},
				{' ', ' ', 'K', ' ', ' ', ' ', ' ', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, tpt);

		System.out.println("Moves: " + engine.findMoveList(board, "BLACK"));
		assertEquals(0, board.checkColor("WHITE"));
		assertEquals(2, board.checkColor("BLACK"));
		assertEquals(2, board.checkmate());
	}

	@Test
	public void testCheckMate4(){

		char[][] testBoard = {
				{'r', ' ', ' ', 'k', ' ', 'b', ' ', 'r'},
				{'p', 'p', 'p', 'Q', ' ', ' ', 'p', 'p'},
				{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
				{' ', ' ', ' ', 'b', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', 'K', ' ', ' '},
				{' ', ' ', ' ', ' ', 'B', ' ', ' ', ' '},
				{'P', 'P', ' ', ' ', ' ', 'P', ' ', 'P'},
				{'n', 'N', ' ', ' ', ' ', ' ', 'q', ' '}
		};
		Board board = new Board(testBoard);
		Engine engine = new Engine("WHITE", board, tpt);

		System.out.println("Moves: " + engine.findMoveList(board, "BLACK"));
		assertEquals(0, board.checkColor("WHITE"));
		assertEquals(2, board.checkColor("BLACK"));
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
		Engine engine = new Engine("WHITE", board, tpt);

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
		Engine engine = new Engine("WHITE", board, tpt);

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
		Engine engine = new Engine("WHITE", board, tpt);

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
		Engine engine = new Engine("BLACK", board, tpt);

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
		Engine engine = new Engine("BLACK", board, tpt);

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
		Engine engine = new Engine("BLACK", board, tpt);

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
		Engine engine = new Engine("BLACK", board, tpt);

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
		Engine engine = new Engine("BLACK", board, tpt);
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
		Engine engine = new Engine("BLACK", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);
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
		Engine engine = new Engine("WHITE", board, tpt);
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

	@Test
	public void testEnPassant2(){
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
		board.makeMove(Util.convertCoordToNum("f7"), Util.convertCoordToNum("f5"));
		assertEquals(true, board.enPassant);
		Engine engine = new Engine("WHITE", board, tpt);
		MoveArrayList moves = engine.findMoveList(board,"WHITE");
		String allMoves = new String();
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			allMoves += Util.convertNumToCoord(move.from);
			allMoves += Util.convertNumToCoord(move.to);
		}
		assertThat(allMoves, CoreMatchers.containsString("e5f6"));
		board.makeMove(Util.convertCoordToNum("e5"), Util.convertCoordToNum("f6"));
		assertEquals(0, board.BP & AttackSets.getPosition(Util.convertCoordToNum("f5"))); //black pawn removed
	}

	@Test
	public void testUpdateHash(){
		char[][] testBoard = {
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
		long hashToBeUpdated = tpt.hash(board);
		Board oldBoard = new Board(board);
		board.makeMove(8, 16);
		long hash = tpt.hash(board);
		hashToBeUpdated = tpt.updateHash(oldBoard, hashToBeUpdated, 8,16);
		assertTrue(hash == hashToBeUpdated);
	}

	@Test
	public void testUpdateHash2(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', ' ', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', 'p', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		long hashToBeUpdated = tpt.hash(board);
		Board oldBoard = new Board(board);
		board.makeMove(8, 17);
		long hash = tpt.hash(board);
		hashToBeUpdated = tpt.updateHash(oldBoard, hashToBeUpdated, 8,17);
		assertTrue(hash == hashToBeUpdated);
	}

	@Test
	public void testUpdateHash3(){
		char[][] testBoard = {
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
		long hashToBeUpdated = tpt.hash(board);
		Board oldBoard = new Board(board);
		board.makeMove(Util.convertCoordToNum("a7"), Util.convertCoordToNum("a6"));
		long hash = tpt.hash(board);
		hashToBeUpdated = tpt.updateHash(oldBoard, hashToBeUpdated, Util.convertCoordToNum("a7"),Util.convertCoordToNum("a6"));
		System.out.println(hash);
		System.out.println(hashToBeUpdated);
		assertTrue(hash == hashToBeUpdated);
	}

	@Test
	public void testUpdateHash4(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', 'P', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', ' ', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		long hashToBeUpdated = tpt.hash(board);
		Board oldBoard = new Board(board);
		int moveFrom = Util.convertCoordToNum("a7");
		int moveTo = Util.convertCoordToNum("b6");
		board.makeMove(moveFrom, moveTo);
		long hash = tpt.hash(board);
		hashToBeUpdated = tpt.updateHash(oldBoard, hashToBeUpdated, moveFrom,moveTo);
		System.out.println(hash);
		System.out.println(hashToBeUpdated);
		assertTrue(hash == hashToBeUpdated);

	}

	@Test
	public void testEnPassantHash(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', 'P', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		char[][] testBoard2 = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', ' ', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', 'P', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', ' ', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Board board2 = new Board(testBoard2);
		int moveFrom = Util.convertCoordToNum("e7");
		int moveTo = Util.convertCoordToNum("e5");
		board.makeMove(moveFrom, moveTo);
		board.makeMove(Util.convertCoordToNum("f5"), Util.convertCoordToNum("e6"));
		board.draw();
		board2.draw();

		long hash = tpt.hash(board);
		long hash2 = tpt.hash(board2);
		System.out.println("HASH1: " + hash);
		System.out.println("HASH2: " + hash2);
		assertTrue(hash == hash2);
	}

	@Test
	public void testCastlingHash1(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{' ', ' ', 'K', 'R', ' ', 'B', 'N', 'R'}
		};
		char[][] testBoard2 = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', ' ', ' ', ' ', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Board board2 = new Board(testBoard2);

		int moveFrom = Util.convertCoordToNum("e1");
		int moveTo = Util.convertCoordToNum("c1");
		board2.makeMove(moveFrom, moveTo);

		long hash = tpt.hash(board);
		long hash2 = tpt.hash(board2);
		assertTrue(hash != hash2);

		board.castleWQValid = false;
		board.castleWKValid = false;
		hash = tpt.hash(board);
		assertTrue(hash == hash2);
	}

	@Test
	public void testCastlingHash2(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', ' ', 'R', 'K', ' '}
		};
		char[][] testBoard2 = {
				{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		Board board = new Board(testBoard);
		Board board2 = new Board(testBoard2);

		int moveFrom = Util.convertCoordToNum("e1");
		int moveTo = Util.convertCoordToNum("g1");
		board2.makeMove(moveFrom, moveTo);

		long hash = tpt.hash(board);
		long hash2 = tpt.hash(board2);
		assertTrue(hash != hash2);

		board.castleWQValid = false;
		board.castleWKValid = false;
		hash = tpt.hash(board);
		assertTrue(hash == hash2);

	}

	@Test
	public void testCastlingHash3(){
		char[][] testBoard = {
				{' ', ' ', 'k', 'r', ' ', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		char[][] testBoard2 = {
				{'r', ' ', ' ', ' ', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Board board2 = new Board(testBoard2);

		int moveFrom = Util.convertCoordToNum("e8");
		int moveTo = Util.convertCoordToNum("c8");
		board2.makeMove(moveFrom, moveTo);

		long hash = tpt.hash(board);
		long hash2 = tpt.hash(board2);
		assertTrue(hash != hash2);

		board.castleBQValid = false;
		board.castleBKValid = false;
		hash = tpt.hash(board);
		assertTrue(hash == hash2);
	}

	@Test
	public void testCastlingHash4(){
		char[][] testBoard = {
				{'r', 'n', 'b', 'q', ' ', 'r', 'k', ' '},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		char[][] testBoard2 = {
				{'r', 'n', 'b', 'q', 'k', ' ', ' ', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
		};
		Board board = new Board(testBoard);
		Board board2 = new Board(testBoard2);

		int moveFrom = Util.convertCoordToNum("e8");
		int moveTo = Util.convertCoordToNum("g8");
		board2.makeMove(moveFrom, moveTo);

		long hash = tpt.hash(board);
		long hash2 = tpt.hash(board2);
		assertTrue(hash != hash2);

		board.castleBQValid = false;
		board.castleBKValid = false;
		hash = tpt.hash(board);
		assertTrue(hash == hash2);

	}
}