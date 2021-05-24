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
			long WPMoves2 = WPMoves >>> 8;
			long legalWPMoves2 = WPMoves2 & empty();
			long invalidateDoubleX2 = 9223372032559808512L; //Bitboard to invalidate pawns doing double moves again
			legalWPMoves2 = invalidateDoubleX2 & legalWPMoves2;
			
			//ATTACKS FOR INNER COLUMN PAWNS------------------------------------------------
			
			//Attacks up and to the left
			long leftColumn0 = ~(72340172838076673L);
			
			long innerColumns = 9114861777597660798L;
			//draw(leftColumn0);
			
			long WPAttacksL = board.WP & leftColumn0;
			WPAttacksL = WPAttacksL >>> 9;
			//draw(WPAttacksL);
		
			
			WPAttacksL = WPAttacksL & enemies(); //only possible if enemy present
			
			//Attacks up and to the right
			long rightColumn0 = 9187201950435737471L;
			
			long WPAttacksR = board.WP & rightColumn0;
			WPAttacksR = WPAttacksR >>> 7;
			
			WPAttacksR = WPAttacksR & enemies(); //only possible if enemy present
			
			long WPAttacks = WPAttacksL | WPAttacksR;
			draw(WPAttacks);
			
			String WPMovesString = Long.toBinaryString(WPMoves);
			
			/*
			for(int i = 0; i<64; i++) {
				if(WPMovesString.charAt(i) == 1) {
					moveList = moveList + "e" + i + 
				}
			}
			*/
			System.out.println(10%8);

		}else if(playerColor.equals("BLACK")){
			
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
	
    public void draw(long bitBoard) {
        String chessBoard[][]=new String[8][8];
        for (int i=0;i<64;i++) {
            chessBoard[i/8][i%8]=" ";
        }
        for (int i=0;i<64;i++) {
            if (((bitBoard>>i)&1)==1) {chessBoard[i/8][i%8] = "1";}
            /*
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
            */
        }
        System.out.println("------------------------");
        for (int i=0;i<8;i++) {
            System.out.println(Arrays.toString(chessBoard[i]));
        }
    }
    
    public void makeMove(String move) {
    	
    }
}
