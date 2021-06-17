package engine;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {
	long WP = 0, WR = 0, WN = 0, WB = 0, WK = 0, WQ = 0, BP = 0, BR = 0, BN = 0, BB = 0, BK = 0, BQ = 0;
	long[] boards = new long[12];
	public boolean castleWQValid = true;
	public boolean castleWKValid = true;
	public boolean castleBQValid = true;
	public boolean castleBKValid = true;
	
	public Board() {
		char[][] board = {
				{'r', 'n', ' ', ' ', 'k', 'b', 'n', 'r'},
				{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'q', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
				{'P', 'P', 'P', ' ', 'P', 'P', 'P', 'P'},
				{'R', 'N', 'B', 'Q', 'K', ' ', ' ', 'R'}
		};
		
		initBitboards(board);
	}
	
	public Board(char[][] board) {
		initBitboards(board);
	}

	//Constructor for copying boards
	public Board(long WP, long WR, long WN, long WB, long WK, long WQ, long BP, long BR, long BN, long BB, long BK, long BQ, boolean castleWKValid, boolean castleWQValid, boolean castleBKValid, boolean castleBQValid) {
		this.WP = WP;
		this.WR = WR;
		this.WN = WN;
		this.WB = WB;
		this.WK = WK;
		this.WQ = WQ;
		this.BP = BP;
		this.BR = BR;
		this.BN = BN;
		this.BB = BB;
		this.BK = BK;
		this.BQ = BQ;
		this.castleWKValid = castleWKValid;
		this.castleWQValid = castleWQValid;
		this.castleBKValid = castleBKValid;
		this.castleBQValid = castleBKValid;
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
		boards[0] = WK;
		boards[1] = WP;
		boards[2] = WR;
		boards[3] = WN;
		boards[4] = WB;
		boards[5] = WQ;
		boards[6] = BK;
		boards[7] = BP;
		boards[8] = BR;
		boards[9] = BN;
		boards[10] = BB;
		boards[11] = BQ;
	}
	//returns 1 if the white player is checked, 2 if the black player is checked and 3 if both are checked.
	public int check() {
		boolean player1Checked = false;
		boolean player2Checked = false;
		Board simBoard = new Board(this.WP, this.WR,this.WN, this.WB, this.WK, this.WQ, this.BP, this.BR, this.BN, this.BB, this.BK, this.BQ, false, false, false, false);

		Engine engine = new Engine("WHITE", simBoard, true);
		Engine engine2 = new Engine("BLACK", simBoard, true);

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

	//returns 0 if no one is checkmated, 1 if the white player is checkmated, 2 if the black player is checkmated
	public int checkmate(){
		int check = this.check();
		if(check == 0){
			return 0;
		}

		Engine engine = new Engine("WHITE", this, true);
		Engine engine2 = new Engine("BLACK", this, true);

		ArrayList<String> whiteMoves = engine.generateMoves("WHITE");
		ArrayList<String> blackMoves = engine2.generateMoves("BLACK");

		if(check == 1){
			for(String move : whiteMoves){
				Board simBoard = new Board(this.WP, this.WR,this.WN, this.WB, this.WK, this.WQ, this.BP, this.BR, this.BN, this.BB, this.BK, this.BQ, this.castleWKValid, this.castleWQValid, this.castleBKValid, this.castleWQValid);
				int startPos = Util.convertCoordToNum(move.substring(0, 2));
				int endPos = Util.convertCoordToNum(move.substring(2));
				simBoard.makeMove(startPos, endPos);
				if(simBoard.check() == 0){
					return 0;
				}
			}
			return 1;
		}else if(check == 2){
			for(String move : blackMoves){
				Board simBoard = new Board(this.WP, this.WR,this.WN, this.WB, this.WK, this.WQ, this.BP, this.BR, this.BN, this.BB, this.BK, this.BQ, this.castleWKValid, this.castleWQValid, this.castleBKValid, this.castleWQValid);
				int startPos = Util.convertCoordToNum(move.substring(0, 2));
				int endPos = Util.convertCoordToNum(move.substring(2));
				simBoard.makeMove(startPos, endPos);
				if(simBoard.check() == 0){
					return 0;
				}
			}
			return 2;
		}
		return 0;
	}
	
	//makes a move, updates the board and castling rights
	public void makeMove(int from, int to) {
		long fromPos = AttackSets.getPosition(from);
		long toPos = AttackSets.getPosition(to);

		if((this.WP & fromPos) != 0){
			clearPosition(toPos);
			this.WP = this.WP ^ toPos;

			if((toPos & AttackSets.wPromotion) != 0){
				this.WP = this.WP ^ toPos;
				this.WQ = this.WQ ^ toPos;
			}
		}else if((this.WK & fromPos) != 0){
			clearPosition(toPos);
			this.WK = this.WK ^ toPos;
			castleWKValid = false;
			castleWQValid = false;

			if(fromPos == AttackSets.WKStart){
				if(toPos == AttackSets.castleWKL){
					this.WR = this.WR ^ AttackSets.wLeftRookStart;
					this.WR = this.WR ^ AttackSets.wLeftRookCastle;
				}else if(toPos == AttackSets.castleWKR){
					this.WR = this.WR ^ AttackSets.wRightRookStart;
					this.WR = this.WR ^ AttackSets.wRightRookCastle;
				}
			}
		}else if((this.WR & fromPos) != 0){
			clearPosition(toPos);
			this.WR = this.WR ^ toPos;

			if((AttackSets.getPosition(7) & fromPos) != 0){
				castleWKValid = false;
			}else if((AttackSets.getPosition(0) & fromPos) != 0){
				castleWQValid = false;
			}
		}else if((this.WN & fromPos) != 0){
			clearPosition(toPos);
			this.WN = this.WN ^ toPos;
		}else if((this.WB & fromPos) != 0){
			clearPosition(toPos);
			this.WB = this.WB ^ toPos;
		}else if((this.WQ & fromPos) != 0){
			clearPosition(toPos);
			this.WQ = this.WQ ^ toPos;
		}else if((this.BP & fromPos) != 0){
			clearPosition(toPos);
			this.BP = this.BP ^ toPos;

			if((toPos & AttackSets.bPromotion) != 0){
				this.BP = this.BP ^ toPos;
				this.BQ = this.BQ ^ toPos;
			}
		}else if((this.BK & fromPos) != 0){
			clearPosition(toPos);
			this.BK = this.BK ^ toPos;
			castleBKValid = false;
			castleBQValid = false;

			if(fromPos == AttackSets.BKStart){
				if(toPos == AttackSets.castleBKL){
					this.BR = this.BR ^ AttackSets.bLeftRookStart;
					this.BR = this.BR ^ AttackSets.bLeftRookCastle;
				}else if(toPos == AttackSets.castleBKR){
					this.BR = this.BR ^ AttackSets.bRightRookStart;
					this.BR = this.BR ^ AttackSets.bRightRookCastle;
				}
			}
		}else if((this.BR & fromPos) != 0){
			clearPosition(toPos);
			this.BR = this.BR ^ toPos;

			if((AttackSets.getPosition(63) & fromPos) != 0){
				castleBKValid = false;
			}else if((AttackSets.getPosition(56) & fromPos) != 0){
				castleBQValid = false;
			}
		}else if((this.BN & fromPos) != 0){
			clearPosition(toPos);
			this.BN = this.BN ^ toPos;
		}else if((this.BB & fromPos) != 0){
			clearPosition(toPos);
			this.BB = this.BB ^ toPos;
		}else if((this.BQ & fromPos) != 0){
			clearPosition(toPos);
			this.BQ = this.BQ ^ toPos;
		}

		//remove the old, duplicate piece
		clearPosition(fromPos);
	}

	public String castleValid(){
		StringBuilder stringBuilder = new StringBuilder();
		if(castleWKValid){
			stringBuilder.append("castleWKValid == true \n");
		}else{
			stringBuilder.append("castleWKValid == false \n");
		}if(castleWQValid){
			stringBuilder.append("castleWQValid == true \n");
		}else{
			stringBuilder.append("castleWQValid == false \n");
		}if(castleBKValid){
			stringBuilder.append("castleBKValid == true \n");
		}else{
			stringBuilder.append("castleBKValid == false \n");
		}if(castleBQValid){
			stringBuilder.append("castleBQValid == true \n");
		}else{
			stringBuilder.append("castleBQValid == false \n");
		}

		return stringBuilder.toString();
	}

	public void clearPosition(long pos){
		WK = WK & (~pos);
		WP = WP & (~pos);
		WR = WR & (~pos);
		WN = WN & (~pos);
		WB = WB & (~pos);
		WQ = WQ & (~pos);
		BK = BK & (~pos);
		BP = BP & (~pos);
		BR = BR & (~pos);
		BN = BN & (~pos);
		BB = BB & (~pos);
		BQ = BQ & (~pos);
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
        String[][] chessBoard2 = new String[8][8];
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				chessBoard2[i][7-j] = chessBoard[i][j];
			}
		}

        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard2[i]));
        }
    }
}
