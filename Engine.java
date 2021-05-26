import java.util.Arrays;

public class Engine {
	private String color; //"BLACK" for black pieces, "WHITE" for white pieces
	private Board board;
	private long full = ~0L;

	
	public Engine(String color, Board board){
		this.board = board;
		this.color = color;
	}
	
	public void generateMoves(String playerColor) {
		String moveList = "";
		
		if(playerColor.equals("WHITE")) {
			
			//PUSHES-----------------------------------------------------------------------
			//Push one
			long WPMoves = board.WP >>> 8;
			long legalWPMoves = WPMoves & empty();
			
			//Push two
			long WPMoves2 = legalWPMoves >>> 8;
			long legalWPMoves2 = WPMoves2 & empty();
			long invalidateDoubleX2 = 9223372032559808512L; //Bitboard to invalidate pawns doing double moves again
			legalWPMoves2 = invalidateDoubleX2 & legalWPMoves2;
			
			//Attacks up and to the left
			//long innerColumns = 9114861777597660798L; behövs ej
			long RightColumn0 = ~(72340172838076673L);

			long WPAttacksR = board.WP & RightColumn0;
			WPAttacksR = WPAttacksR >>> 9;
			
			WPAttacksR = WPAttacksR & enemies(); //only possible if enemy present
			
			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;
			
			long WPAttacksL = board.WP & leftColumn0;
			WPAttacksL = WPAttacksL >>> 7;
			
			WPAttacksL = WPAttacksL & enemies(); //only possible if enemy present
			
			long WPAttacks = WPAttacksL | WPAttacksR; //Probably not needed
			
			String WPMovesString = Long.toBinaryString(legalWPMoves);
			WPMovesString = Util.padBinaryString(WPMovesString); //Padding needed for 64-bits
			
			//Converting bitboards to move string:
			for(int i = 0; i<64; i++) {
				if(WPMovesString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8));
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
			}
			
			String WPMoves2String = Long.toBinaryString(legalWPMoves2);
			WPMoves2String = Util.padBinaryString(WPMoves2String);
			
			for(int i = 0; i<64; i++) {
				if(WPMoves2String.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)-1);
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
			}
			
			String WPAttacksLString = Long.toBinaryString(WPAttacksL);
			WPAttacksLString =  Util.padBinaryString(WPAttacksLString);
			
			String WPAttacksRString = Long.toBinaryString(WPAttacksR);
			WPAttacksRString = Util.padBinaryString(WPAttacksRString);
			
			String WPString = Long.toBinaryString(board.WP); //need to check the actual board
			WPString = Util.padBinaryString(WPString);
			
			for(int i = 0; i<64; i++) {
				if(WPAttacksLString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i-7) + (((i-7)/8)+1);
					
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
				
				if(WPAttacksRString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i-9) + (((i-9)/8)+1);
					
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
			}
			
			
			System.out.println(moveList);

		}else if(playerColor.equals("BLACK")){
			//PUSHES-----------------------------------------------------------------------
			//Push one
			long BPMoves = board.BP << 8;
			long legalBPMoves = BPMoves & empty();
			
			//Push two
			long BPMoves2 = legalBPMoves << 8;
			long legalBPMoves2 = BPMoves2 & empty();
			long invalidateDoubleX2 = 4294967295L; //Bitboard to invalidate pawns doing double moves again
			legalBPMoves2 = invalidateDoubleX2 & legalBPMoves2;
			
			//Attacks up and to the left
			//long innerColumns = 9114861777597660798L; behövs ej FUNKAR HITTILLS
			long RightColumn0 = ~(72340172838076673L);

			long BPAttacksR = board.BP & RightColumn0;
			BPAttacksR = BPAttacksR << 7;
			
			BPAttacksR = BPAttacksR & enemies(); //only possible if enemy present
			
			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;
			
			long BPAttacksL = board.BP & leftColumn0;
			BPAttacksL = BPAttacksL << 9;
			BPAttacksL = BPAttacksL & enemies(); //only possible if enemy present
			
			long BPAttacks = BPAttacksL | BPAttacksR; //Probably not needed
			
			String BPMovesString = Long.toBinaryString(legalBPMoves);
			BPMovesString = Util.padBinaryString(BPMovesString); //Padding needed for 64-bits
			
			//Converting bitboards to move string:
			for(int i = 0; i<64; i++) {
				if(BPMovesString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+2);
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
			}
			
			String BPMoves2String = Long.toBinaryString(legalBPMoves2);
			BPMoves2String = Util.padBinaryString(BPMoves2String);
			
			for(int i = 0; i<64; i++) {
				if(BPMoves2String.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+3);
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
			}
			
			String BPAttacksLString = Long.toBinaryString(BPAttacksL);
			BPAttacksLString =  Util.padBinaryString(BPAttacksLString);
			
			String BPAttacksRString = Long.toBinaryString(BPAttacksR);
			BPAttacksRString = Util.padBinaryString(BPAttacksRString);
			
			String BPString = Long.toBinaryString(board.BP); //need to check the actual board
			BPString = Util.padBinaryString(BPString);
			
			for(int i = 0; i<64; i++) {
				if(BPAttacksLString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i-9) + (((i-9)/8)+3);
					
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
				
				if(BPAttacksRString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i-7) + (((i-7)/8)+3);
					
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
			}
			
			System.out.println(moveList);
			
		}
	}
	
	private long enemies() {
		long enemies = 0L;
		
		if(color.equals("WHITE")) {
			enemies = enemies ^ board.BP;
			enemies = enemies ^ board.BR;
			enemies = enemies ^ board.BN;
			enemies = enemies ^ board.BB;
			enemies = enemies ^ board.BK;
			enemies = enemies ^ board.BQ;
			
		}else if(color.equals("BLACK")) {
			enemies = enemies ^ board.WP;
			enemies = enemies ^ board.WR;
			enemies = enemies ^ board.WN;
			enemies = enemies ^ board.WB;
			enemies = enemies ^ board.WK;
			enemies = enemies ^ board.WQ;
		}
		
		return enemies;
	}
	
	private long empty() {
		long empty = 0l;
		empty = ~(empty & 0); //flip all bits to 1 
		empty = empty ^ board.WP;
		empty = empty ^ board.WR;
		empty = empty ^ board.WN;
		empty = empty ^ board.WB;
		empty = empty ^ board.WK;
		empty = empty ^ board.WQ;
		
		empty = empty ^ board.BP;
		empty = empty ^ board.BR;
		empty = empty ^ board.BN;
		empty = empty ^ board.BB;
		empty = empty ^ board.BK;
		empty = empty ^ board.BQ;
		
		return empty;
	}
	
	private String search() { //return type?
		
	}
	
	private double evalPosition() {
		
	}
	
	//returns 0 if no players are checked, 1 if the white player is checked, 2 if the black player is checked
	// and 3 if both players are checked.
	public int check(Board board) {
		
	}
	
	
	//returns 0 if no players are checkmated, 1 if the white player is checkmated, 2 if the black player is checkmated
	public int checkmate(Board board) {
		
	}
	
	private String rookMoves(int xPos, int yPos, String COLOR) {
		String moves = "";
		
		
		return moves;
	}
	
	private String knightMoves(int xPos, int yPos, String COLOR) {
		String moves = "";
		
		
		return moves;
	}
	
	private String bishopMoves(int xPos, int yPos, String COLOR) {
		String moves = "";
		
		
		return moves;
	}
	
	private String Moves(int xPos, int yPos, String COLOR) {
		String moves = "";
		
		
		return moves;
	}
	
	/*
    public void draw(long bitBoard) {
        String chessBoard[][]=new String[8][8];
        for (int i=0;i<64;i++) {
            chessBoard[i/8][i%8]=" ";
        }
        for (int i=0;i<64;i++) {
            if (((bitBoard>>i)&1)==1) {chessBoard[i/8][i%8] = "1";}
            
            if (((WN>>i)&1)==1) {chessBoard[i/8][i%8] = "N";}
            if (((WB>>i)&1)==1) {chessBoard[i/8][i%8] = "B";}
            if (((WR>>i)&1)==1) {chessBoard[i/8][i%8] = "R";}
            if (((WQ>>i)&1)==1) {chessBoard[i/8][i%8] = "Q";}
            if (((WK>>i)&1)==1) {chessBoard[i/8][i%8] = "K";}
            if (((BP>>i)&1)==1) {chessBoard[i/8][i%8] = "p";}
            if (((BN>>i)&1)==1) {chessBoard[i/8][i%8] = "n";}
            if (((BB>>i)&1)==1) {chessBoard[i/8][i%8] = "b";}
            if (((BR>>i)&1)==1) {chessBoard[i/8][i%8] = "r";}
            if (((BQ>>i)&1)==1) {chessBoard[i/8][i%8] = "q";}
            if (((BK>>i)&1)==1) {chessBoard[i/8][i%8] = "k";}
            
        }
        System.out.println("------------------------");
        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }
    
    */

	public void draw(long bitBoard) {
		String bitBoardString = Long.toBinaryString(bitBoard);
		bitBoardString = Util.padBinaryString(bitBoardString);
		int q = 64;
		
        for (int i=0;i<8;i++) {
        	System.out.print("[");
        	int k = -1;
        	q = q - 8;
        			
        	for(int j = 0; j < 8; j++) {
        		k++;
        		
            	
                System.out.print(bitBoardString.charAt(q+k));
                if(k != 7) 
                	System.out.print(",");
        	}
        	System.out.println("]");
        }
    }
    
    public void makeMove(String move) {
    	
    }
}
