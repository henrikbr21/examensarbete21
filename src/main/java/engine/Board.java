package engine;

import java.util.Arrays;

public class Board {
	public long WP = 0, WR = 0, WN = 0, WB = 0, WK = 0, WQ = 0, BP = 0, BR = 0, BN = 0, BB = 0, BK = 0, BQ = 0;
	public boolean castleWQValid = true;
	public boolean castleWKValid = true;
	public boolean castleBQValid = true;
	public boolean castleBKValid = true;
	public boolean enPassant = false;
	public int enPassantPos = -1;
	public int enPassantPlayer = 0; //1 for white player, 2 for black
	public boolean wHasCastled = false;
	public boolean bHasCastled = false;
	public int lastMovedPos = -1;

	private static final char[][] defaultBoard = {
			{'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
			{'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
			{'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
			{'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
	};
	
	public Board() {
		initBitboards(defaultBoard);
	}

	public void reset(){
		this.WP = 0;
		this.WR = 0;
		this.WN = 0;
		this.WB = 0;
		this.WK = 0;
		this.WQ = 0;
		this.BP = 0;
		this.BR = 0;
		this.BN = 0;
		this.BB = 0;
		this.BK = 0;
		this.BQ = 0;

		initBitboards(defaultBoard);
		castleWQValid = true;
		castleWKValid = true;
		castleBQValid = true;
		castleBKValid = true;
		wHasCastled = false;
		bHasCastled = false;
	}
	
	public Board(char[][] board) {
		initBitboards(board);
	}

	//copying
	public Board(Board board){
		this.WP = board.WP;
		this.WR = board.WR;
		this.WN = board.WN;
		this.WB = board.WB;
		this.WK = board.WK;
		this.WQ = board.WQ;
		this.BP = board.BP;
		this.BR = board.BR;
		this.BN = board.BN;
		this.BB = board.BB;
		this.BK = board.BK;
		this.BQ = board.BQ;
		this.castleWKValid = board.castleWKValid;
		this.castleWQValid = board.castleWQValid;
		this.castleBKValid = board.castleBKValid;
		this.castleBQValid = board.castleBQValid;
		this.wHasCastled = board.wHasCastled;
		this.bHasCastled = board.bHasCastled;
		this.enPassant = board.enPassant;
		this.enPassantPos = board.enPassantPos;
		this.enPassantPlayer = board.enPassantPlayer; //1 for white player, 2 for black
		this.lastMovedPos = board.lastMovedPos;
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

	public void playLine(MoveArrayList moves){
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			this.makeMove(move.from, move.to);
		}
	}

	//returns 1 if the white player is checked, 2 if the black player is checked and 3 if both are checked.
	public int check() {
		Board simBoard = new Board(this);
		Engine engine = new Engine("WHITE", simBoard, new TPT(0));

		int ret = 0;
		if(engine.isAttackedByOpponent(this, "BLACK", this.BK)){
			ret |= 2;
		}

		if(engine.isAttackedByOpponent(this, "WHITE", this.WK)){
			ret |= 1;
		}

		return ret;
	}

	public int checkColor(String color){
		TPT tpt = new TPT(0);
		Engine engine = new Engine("WHITE", this, tpt);

		if(color.equals("BLACK")){
			if(engine.isAttackedByOpponent(this, "BLACK", this.BK)){
				return 2;
			}else return 0;
		}else if(color.equals("WHITE")){
			if(engine.isAttackedByOpponent(this, "WHITE", this.WK)){
				return 1;
			}else return 0;

		}
		return -1;
	}

	//to do
	public int stalemate(){
		TPT tpt = new TPT(0);
		Engine engine = new Engine("WHITE", this, tpt);
		boolean allCauseCheckForWhite = true;
		boolean allCauseCheckForBlack = true;

		MoveArrayList whiteMoves = engine.generateMoves(this,"WHITE");
		for(int i = 0; i < whiteMoves.size(); i++){
			Move move = whiteMoves.get(i);
			Board simBoard = new Board(this);
			simBoard.makeMove(move.from, move.to);
			if(simBoard.BK == 0L || simBoard.WK == 0L)
				continue;
			if(simBoard.checkColor("WHITE") == 0){
				allCauseCheckForWhite = false;
			}
		}

		MoveArrayList blackMoves = engine.generateMoves(this,"BLACK");
		for(int i = 0; i < blackMoves.size(); i++){
			Move move = blackMoves.get(i);
			Board simBoard = new Board(this);
			simBoard.makeMove(move.from, move.to);
			if(simBoard.BK == 0L || simBoard.WK == 0L)
				continue;
			if(simBoard.checkColor("BLACK") == 0){
				allCauseCheckForBlack = false;
			}
		}
		if(allCauseCheckForWhite || allCauseCheckForBlack)
			return 4;

		return 0;
	}

	//returns 0 if no one is checkmated, 1 if the white player is checkmated, 2 if the black player is checkmated
	public int checkmate(){
		TPT tpt = new TPT(0);
		Engine engine = new Engine("WHITE", this, tpt);

		int check = this.check();

		if(check == 0){
			return 0;
		}
		if(check == 1){
			MoveArrayList whiteMoves = engine.generateMoves(this,"WHITE");
			for(int i = 0; i < whiteMoves.size(); i++){
				Move move = whiteMoves.get(i);
				Board simBoard = new Board(this);
				simBoard.makeMove(move.from, move.to);
				if(simBoard.BK == 0L || simBoard.WK == 0L)
					continue;
				if(simBoard.checkColor("WHITE") == 0){
					MoveArrayListManager.renounceMoveArrayList(whiteMoves);
					return 0;
				}
			}
			MoveArrayListManager.renounceMoveArrayList(whiteMoves);
			return 1;
		}else if(check == 2){
			MoveArrayList blackMoves = engine.generateMoves(this,"BLACK");
			for(int i = 0; i < blackMoves.size(); i++){
				Move move = blackMoves.get(i);
				Board simBoard = new Board(this);
				simBoard.makeMove(move.from, move.to);
				if(simBoard.BK == 0L || simBoard.WK == 0L)
					continue;
				if(simBoard.checkColor("BLACK") == 0){
					MoveArrayListManager.renounceMoveArrayList(blackMoves);
					return 0;
				}
			}
			MoveArrayListManager.renounceMoveArrayList(blackMoves);
			return 2;
		}
		return 0;
	}
	
	//makes a move, updates the board and castling rights
	public void makeMove(int from, int to) {
		long fromPos = AttackSets.getPosition(from);
		long toPos = AttackSets.getPosition(to);
		boolean enPassantEnabledThisTurn = false;
		this.lastMovedPos = to;

		if((this.WP & fromPos) != 0){
			//en passant, make possible
			if((fromPos & AttackSets.WPStartRow) != 0 && (toPos & AttackSets.WPStartRowPlus2) != 0){
				enPassant = true;
				enPassantEnabledThisTurn = true;
				enPassantPos = to;
				enPassantPlayer = 1;
			}

			//en passant move
			if((to % 8) != (from % 8)){ //if sideways attack
				if((occupied() & toPos) == 0){ //if doing sideways attack to empty position => en passant move
	 				this.BP = this.BP ^ AttackSets.getPosition(to-8);
					this.WP = this.WP ^ toPos;
				}else if((toPos & AttackSets.wPromotion) != 0) { //pawn promotion
					clearPosition(toPos);
					this.WQ = this.WQ ^ toPos;
				}else{
					clearPosition(toPos);
					this.WP = this.WP ^ toPos;
				}

				//pawn promotion
			}else if((toPos & AttackSets.wPromotion) != 0){
				this.WQ = this.WQ ^ toPos;
			}else { //"normal" pawn moves
				this.WP = this.WP ^ toPos;
			}
		}else if((this.WK & fromPos) != 0){
			clearPosition(toPos);
			this.WK = this.WK ^ toPos;
			castleWKValid = false;
			castleWQValid = false;

			if(fromPos == AttackSets.WKStart){
				if(toPos == AttackSets.castleWKL){
					this.wHasCastled = true;
					this.WR = this.WR ^ AttackSets.wLeftRookStart;
					this.WR = this.WR ^ AttackSets.wLeftRookCastle;
				}else if(toPos == AttackSets.castleWKR){
					this.wHasCastled = true;
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

			//en passant, make possible
			if((fromPos & AttackSets.BPStartRow) != 0 && (toPos & AttackSets.BPStartRowMinus2) != 0){
				enPassant = true;
				enPassantEnabledThisTurn = true;
				enPassantPos = to;
				enPassantPlayer = 2;
			}

			//en passant move
			if((to % 8) != (from % 8)){ //if sideways attack
				if((occupied() & toPos) == 0){ //if doing sideways attack to empty position => en passant move
					this.WP = this.WP ^ AttackSets.getPosition(to+8);
					this.BP = this.BP ^ toPos;
				}else if((toPos & AttackSets.bPromotion) != 0) { //pawn promotion
					clearPosition(toPos);
					this.BQ = this.BQ ^ toPos;
				}else{
					clearPosition(toPos);
					this.BP = this.BP ^ toPos;
				}
				//pawn promotion
			}else if((toPos & AttackSets.bPromotion) != 0){
				this.BQ = this.BQ ^ toPos;
			}else { //"normal" pawn moves
				this.BP = this.BP ^ toPos;
			}
		}else if((this.BK & fromPos) != 0){
			clearPosition(toPos);
			this.BK = this.BK ^ toPos;
			castleBKValid = false;
			castleBQValid = false;

			if(fromPos == AttackSets.BKStart){
				if(toPos == AttackSets.castleBKL){
					this.bHasCastled = true;
					this.BR = this.BR ^ AttackSets.bLeftRookStart;
					this.BR = this.BR ^ AttackSets.bLeftRookCastle;
				}else if(toPos == AttackSets.castleBKR){
					this.bHasCastled = true;
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

		if(!enPassantEnabledThisTurn)
			enPassant = false;

		if((toPos & AttackSets.getPosition(0)) != 0)
			castleWQValid = false;
		else if((toPos & AttackSets.getPosition(7)) != 0)
			castleWKValid = false;
		else if((toPos & AttackSets.getPosition(56)) != 0)
			castleBQValid = false;
		else if((toPos & AttackSets.getPosition(63)) != 0)
			castleBKValid = false;

		//remove the old, duplicate piece
		clearPosition(fromPos);

		if(Debug.findDuplicate(this)){
			Util.draw(this.BP);
			Util.draw(this.BN);
			Util.draw(this.BB);
			Util.draw(this.BR);
			Util.draw(this.BQ);
			Util.draw(this.BK);
			this.draw();
			System.out.println("HERE");
		}
	}

	public char getPiece(int pos){
		long position = AttackSets.getPosition(pos);
		if((this.WP & position) != 0){
			return 'P';
		}else if((this.WN & position) != 0){
			return 'N';
		}else if((this.WB & position) != 0){
			return 'B';
		}else if((this.WR & position) != 0){
			return 'R';
		}else if((this.WQ & position) != 0){
			return 'Q';
		}else if((this.WK & position) != 0){
			return 'K';
		}else if((this.BP & position) != 0){
			return 'p';
		}else if((this.BN & position) != 0){
			return 'n';
		}else if((this.BB & position) != 0){
			return 'b';
		}else if((this.BR & position) != 0){
			return 'r';
		}else if((this.BQ & position) != 0){
			return 'q';
		}else if((this.BK & position) != 0){
			return 'k';
		}
		return '_';
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
        String[][] chessBoard =new String[8][8];
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
        System.out.println("-----------------------");
        System.out.println("WK: " + castleWKValid + " WQ: " + castleWQValid + " BK: " + castleBKValid + " BQ: " + castleBQValid);
		System.out.println("wHasCastled: " + wHasCastled + " bHasCastled" + bHasCastled);
    }

	public long empty() {
		long empty= ~(0L);
		empty = empty ^ WP;
		empty = empty ^ WR;
		empty = empty ^ WN;
		empty = empty ^ WB;
		empty = empty ^ WK;
		empty = empty ^ WQ;

		empty = empty ^ BP;
		empty = empty ^ BR;
		empty = empty ^ BN;
		empty = empty ^ BB;
		empty = empty ^ BK;
		empty = empty ^ BQ;

		return empty;
	}

	public long occupied() {
		return ~empty();
	}

	public long friends(String color){
		long friends = 0L;

		if(color.equals("WHITE")) {
			friends = friends ^ this.WP;
			friends = friends ^ this.WR;
			friends = friends ^ this.WN;
			friends = friends ^ this.WB;
			friends = friends ^ this.WK;
			friends = friends ^ this.WQ;

		}else if(color.equals("BLACK")) {
			friends = friends ^ this.BP;
			friends = friends ^ this.BR;
			friends = friends ^ this.BN;
			friends = friends ^ this.BB;
			friends = friends ^ this.BK;
			friends = friends ^ this.BQ;
		}
		return friends;
	}

	public long enemies(String color){
		long enemies = 0L;

		if(color.equals("WHITE")) {
			enemies = enemies ^ this.BP;
			enemies = enemies ^ this.BR;
			enemies = enemies ^ this.BN;
			enemies = enemies ^ this.BB;
			enemies = enemies ^ this.BK;
			enemies = enemies ^ this.BQ;

		}else if(color.equals("BLACK")) {
			enemies = enemies ^ this.WP;
			enemies = enemies ^ this.WR;
			enemies = enemies ^ this.WN;
			enemies = enemies ^ this.WB;
			enemies = enemies ^ this.WK;
			enemies = enemies ^ this.WQ;
		}
		return enemies;
	}
}
