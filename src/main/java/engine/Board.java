package engine;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	long WP = 0, WR = 0, WN = 0, WB = 0, WK = 0, WQ = 0, BP = 0, BR = 0, BN = 0, BB = 0, BK = 0, BQ = 0;
	boolean castleWQValid = true;
	boolean castleWKValid = true;
	boolean castleBQValid = true;
	boolean castleBKValid = true;
	
	public Board() {
		char[][] board = {
				{'r', 'n', 'b', ' ', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', ' ', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', 'B', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', 'q', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', 'P', ' ', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', ' ', 'N', 'R'}
		};
		
		initBitboards(board);
	}
	
	public Board(char[][] board) {
		initBitboards(board);
	}
	
	private long stringToLong(String s) {
		if(s.charAt(0) == '0') {
			return Long.parseLong(s, 2);
		}else {
			return Long.parseLong("1" + s.substring(2), 2)*2;
		}
	}
			
	private void initBitboards(char[][] board) {
		int k = -1;
		
		for(int i = 0; i < 8; i++) {
			for(int j = 7; j >= 0; j--) {
				k++;
				
				String boardString = "0000000000000000000000000000000000000000000000000000000000000000";
				boardString = boardString.substring(k+1)  + "1" + boardString.substring(0, k);
				
				switch(board[i][j]) {
					case 'p':
						BP += stringToLong(boardString);
						break;
					case 'r':
						BR += stringToLong(boardString);
						break;
					case 'n':
						BN += stringToLong(boardString);
						break;
					case 'b':
						BB += stringToLong(boardString);
						break;
					case 'q':
						BQ += stringToLong(boardString);
						break;
					case 'k':
						BK += stringToLong(boardString);
						break;
						
					case 'P':
						WP += stringToLong(boardString);
						break;
					case 'R':
						WR += stringToLong(boardString);
						break;
					case 'N':
						WN += stringToLong(boardString);
						break;
					case 'B':
						WB += stringToLong(boardString);
						break;
					case 'Q':
						WQ += stringToLong(boardString);
						break;
					case 'K':
						WK += stringToLong(boardString);
						break;
				}
			}
		}
	}
	//returns 1 if the white player is checked, 2 if the black player is checked and 3 if both are checked.
	public int check() {
		boolean player1Checked = false;
		boolean player2Checked = false;

		Engine engine = new Engine("WHITE", this);
		Engine engine2 = new Engine("BLACK", this);

		ArrayList<String> whiteMoves = engine.generateMoves("WHITE");
		ArrayList<String> blackMoves = engine2.generateMoves("BLACK");

		String WKPos = "";
		for(int i = 0; i < 64; i++){
			if(((this.WK>>i)&1)==1){
				WKPos = Util.convertNumToCoord(63-i);
			}
		}
		String BKPos = "";
		for(int i = 0; i < 64; i++){
			if(((this.BK>>i)&1)==1){
				BKPos = Util.convertNumToCoord(63-i);
			}
		}
		for(String move : whiteMoves){
			String move2 = move.substring(2);
			if(move2.equals(BKPos)){
				player2Checked = true;
				break;
			}
		}
		for(String move : blackMoves){
			String move2 = move.substring(2);
			if(move2.equals(WKPos)){
				player1Checked = true;
				break;
			}
		}
		if(player1Checked && player2Checked)
			return 3;
		else if(player1Checked)
			return 1;
		else if(player2Checked)
			return 2;
		return 0;

	}
	
	//makes a move, updates the board and castling rights
	public void makeMove(int from, int to) {
		
	}

    public void draw() {
        String chessBoard[][]=new String[8][8];
        for (int i=0;i<64;i++) {
            chessBoard[i/8][i%8]=" ";
        }
        for (int i=0;i<64;i++) {
            if (((WP>>i)&1)==1) {chessBoard[i/8][i%8]="P";}
            if (((WN>>i)&1)==1) {chessBoard[i/8][i%8]="N";}
            if (((WB>>i)&1)==1) {chessBoard[i/8][i%8]="B";}
            if (((WR>>i)&1)==1) {chessBoard[i/8][i%8]="R";}
            if (((WQ>>i)&1)==1) {chessBoard[i/8][i%8]="Q";}
            if (((WK>>i)&1)==1) {chessBoard[i/8][i%8]="K";}
            if (((BP>>i)&1)==1) {chessBoard[i/8][i%8]="p";}
            if (((BN>>i)&1)==1) {chessBoard[i/8][i%8]="n";}
            if (((BB>>i)&1)==1) {chessBoard[i/8][i%8]="b";}
            if (((BR>>i)&1)==1) {chessBoard[i/8][i%8]="r";}
            if (((BQ>>i)&1)==1) {chessBoard[i/8][i%8]="q";}
            if (((BK>>i)&1)==1) {chessBoard[i/8][i%8]="k";}
        }
        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }
}
