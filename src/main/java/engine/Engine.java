package engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Engine {
	private String color; //"BLACK" for black pieces, "WHITE" for white pieces
	private Board board;
	private long full = ~0L;

	
	public Engine(String color, Board board, boolean initialized){
		long time = System.currentTimeMillis();
		this.board = board;
		this.color = color;
		if(!initialized){
			AttackSets.initKnightMoves();
			AttackSets.initKingMoves();
			AttackSets.initRookMoves();
			AttackSets.initLineMasks();
			AttackSets.initLeftRays();
			AttackSets.initRightRays();
			AttackSets.initPositions();
			AttackSets.initEnPassantMoves();
			AttackSets.initRookAttacksLR();
			AttackSets.initRookAttacksUD();
		}
		time = System.currentTimeMillis() - time;
		if(!initialized)
			System.out.println("Initialization took " + time + "ms.");
	}

	public ArrayList<String> findMoveList(Board board, String playerColor){
		ArrayList<String> legalMoves = new ArrayList<String>();

		if(playerColor == "WHITE"){
			if(board.checkmate() == 1)
				return new ArrayList<String>();
			else{
				ArrayList<String> moves = this.generateMoves(board,"WHITE");

				for(String move : moves){
					Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
					int startPos = Util.convertCoordToNum(move.substring(0, 2));
					int endPos = Util.convertCoordToNum(move.substring(2));
					simBoard.makeMove(startPos, endPos);
					if(simBoard.check() == 2 || simBoard.check() == 0)
						legalMoves.add(move);
				}
			}
		}else if(playerColor == "BLACK") {
			if(board.checkmate() == 2)
				return new ArrayList<String>();
			else{
				ArrayList<String> moves = this.generateMoves(board,"BLACK");

				for(String move : moves){
					Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
					int startPos = Util.convertCoordToNum(move.substring(0, 2));
					int endPos = Util.convertCoordToNum(move.substring(2));
					simBoard.makeMove(startPos, endPos);
					if(simBoard.check() == 1 || simBoard.check() == 0)
						legalMoves.add(move);
				}
			}
		}
		return legalMoves;
	}

	public ArrayList<String> generateMoves(Board board, String playerColor) {

		//String moveList = "";
		ArrayList<String> moveList = new ArrayList<String>();

		long occupied = occupied();
		long empty = empty(board);

		long enemies = 0L;
		long friends = 0L;
		if((playerColor == "WHITE" && this.color == "WHITE") || (playerColor == "BLACK" && this.color == "BLACK")){
			enemies = enemies(board);
			friends = friends();
		}else if((playerColor == "BLACK" && this.color == "WHITE") || (playerColor == "WHITE" && this.color == "BLACK")){
			enemies = friends();
			friends = enemies(board);
		}

		if(playerColor.equals("WHITE")) {

			//PAWNS-----------------------------------------------------------------------
			//Push one
			long WPMoves = board.WP >>> 8;
			long legalWPMoves = WPMoves & empty;
			
			//Push two
			long WPMoves2 = legalWPMoves >>> 8;
			long legalWPMoves2 = WPMoves2 & empty;
			long invalidateDoubleX2 = 9223372032559808512L; //Bitboard to invalidate pawns doing double moves again
			legalWPMoves2 = invalidateDoubleX2 & legalWPMoves2;
			
			//Attacks up and to the left
			//long innerColumns = 9114861777597660798L; behövs ej
			long RightColumn0 = ~(72340172838076673L);

			long WPAttacksR = board.WP & RightColumn0;
			WPAttacksR = WPAttacksR >>> 9;
			
			WPAttacksR = WPAttacksR & enemies; //only possible if enemy present
			
			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;

			long WPAttacksL = board.WP & leftColumn0;
			WPAttacksL = WPAttacksL >>> 7;

			WPAttacksL = WPAttacksL & enemies; //ISSUE?!?!!

			long WPAttacks = WPAttacksL | WPAttacksR; //Probably not needed
			
			String WPMovesString = Long.toBinaryString(legalWPMoves);
			WPMovesString = Util.padBinaryString(WPMovesString); //Padding needed for 64-bits
			
			//Converting bitboards to move string:

			for(int i = 0; i<64; i++) {
				String move = "";
				if(WPMovesString.charAt(i) == '1') {
					move = Util.convertNumToAlph(i) + ((i/8));
					move = move + Util.convertNumToAlph(i) + ((i/8)+1);
					moveList.add(move);
				}
			}
			
			String WPMoves2String = Long.toBinaryString(legalWPMoves2);
			WPMoves2String = Util.padBinaryString(WPMoves2String);
			
			for(int i = 0; i<64; i++) {
				String move = "";
				if(WPMoves2String.charAt(i) == '1') {
					move = Util.convertNumToAlph(i) + ((i/8)-1);
					move = move + Util.convertNumToAlph(i) + ((i/8)+1);
					moveList.add(move);
				}
			}
			
			String WPAttacksLString = Long.toBinaryString(WPAttacksL);
			WPAttacksLString =  Util.padBinaryString(WPAttacksLString);
			
			String WPAttacksRString = Long.toBinaryString(WPAttacksR);
			WPAttacksRString = Util.padBinaryString(WPAttacksRString);
			
			String WPString = Long.toBinaryString(board.WP); //need to check the actual board
			WPString = Util.padBinaryString(WPString);
			
			for(int i = 0; i < 64; i++) {
				String move = "";
				if(WPAttacksLString.charAt(i) == '1') {
					move = move + Util.convertNumToAlph(i-7) + (((i-7)/8)+1);
					
					move = move + Util.convertNumToAlph(i) + ((i/8)+1);
					moveList.add(move);
				}
				move = "";
				if(WPAttacksRString.charAt(i) == '1') {
					move = move + Util.convertNumToAlph(i-9) + (((i-9)/8)+1);
					
					move = move + Util.convertNumToAlph(i) + ((i/8)+1);
					moveList.add(move);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos+1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos+8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
				}else if(board.enPassantPos % 8 == 7) {
					if((AttackSets.getPosition(board.enPassantPos)-1 & board.WP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos-1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos+8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos+1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos+8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.WP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos-1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos+8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
				}
			}
			
			//KNIGHTS
			String WNString = Long.toBinaryString(board.WN);
			WNString = Util.padBinaryString(WNString);
			
			int knightsFound = 0;
			for(int i = 0; i < 64; i++) {
				if(knightsFound == 2)
					break;
				if(WNString.charAt(i) == '1') {
					knightsFound++;
					
					long WNAttacks = AttackSets.knightMoves(i);
					String WNAttacksString = Long.toBinaryString(WNAttacks);
					WNAttacksString = Util.padBinaryString(WNAttacksString);
					
					long legalWNMoves2 = WNAttacks & empty;
					long legalWNMoves3 = WNAttacks & enemies;
					
					long legalWNMoves = legalWNMoves2 | legalWNMoves3;
					String legalWNMovesString = Long.toBinaryString(legalWNMoves);
					legalWNMovesString = Util.padBinaryString(legalWNMovesString);

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 6)
							break;

						if(legalWNMovesString.charAt(j) == '1') {
							String move = "";
							movesFound++;
							
							move = move + Util.convertNumToAlph(i) + ((i/8)+1);
							move = move + Util.convertNumToAlph(j) + ((j/8)+1);
							moveList.add(move);
						}
					}
				}
			}
			
			//King
			String WKString = Long.toBinaryString(board.WK);
			WKString = Util.padBinaryString(WKString);
			
			for(int i = 0; i < 64; i++) {
				if(WKString.charAt(i) == '1') {
					
					long WKMoves = AttackSets.kingMoves(i);
					
					//Remove pseudolegal moves
					long legalWKMoves2 = WKMoves & empty;
					long legalWKMoves3 = WKMoves & enemies;
					long legalWKMoves = legalWKMoves2 | legalWKMoves3;

					if(board.WK == AttackSets.WKStart){
						if(board.castleWKValid){
							if((occupied & AttackSets.WKRblockers) == 0){
								if(board.check() == 0) {
									Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
									simBoard.WK = simBoard.WK ^ AttackSets.WKStart;
									simBoard.WK = simBoard.WK ^ AttackSets.castleWKR;
									if(simBoard.check() == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(5);

										if(simBoard.check() == 0)
											legalWKMoves = legalWKMoves | AttackSets.castleWKR;
									}

								}
							}
						}
						if(board.castleWQValid){
							if((occupied & AttackSets.WKLblockers) == 0){
								if(board.check() == 0){
									Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
									simBoard.WK = simBoard.WK ^ AttackSets.WKStart;
									simBoard.WK = simBoard.WK ^ AttackSets.castleWKL;
									if(simBoard.check() == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(3);
										if(simBoard.check() == 0){
											legalWKMoves = legalWKMoves | AttackSets.castleWKL;
										}
									}
								}
							}
						}
					}

					String legalMovesString = Long.toBinaryString(legalWKMoves);
					legalMovesString = Util.padBinaryString(legalMovesString);

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8) 
							break;
						
						if(legalMovesString.charAt(j) == '1') {
							String move = "";

							movesFound++;
							move = move + Util.convertNumToAlph(i) + ((i/8)+1);
							move = move + Util.convertNumToAlph(j) + ((j/8)+1);

							moveList.add(move);
						}
					}
					break;
				}
			}
			
			//Rook
			String WRString = Long.toBinaryString(board.WR);
			WRString = Util.padBinaryString(WRString);
			
			int rooksFound = 0;
			for(int i = 0; i < 64; i++) {
				if(rooksFound == 2)
					break;
				
				if(WRString.charAt(i) == '1') {
					rooksFound++;
					
					//WRString = WRString.substring(i/8, i/8+8);
					//System.out.println(WRString);
					
					String singleRookString = "0000000000000000000000000000000000000000000000000000000000000000";
					singleRookString = singleRookString.substring(0, i) + "1" + singleRookString.substring(i+1);
					long singleRook = Util.stringToLong(singleRookString);

					//up attacks
					long upAttacks = occupied & AttackSets.rookAttacksU(i);
					int closestPos = Long.numberOfLeadingZeros(upAttacks);
					if(closestPos != 64){
						upAttacks = AttackSets.rookAttacksU(i) & ~(AttackSets.rookAttacksU(closestPos));
					}else{
						upAttacks = AttackSets.rookAttacksU(i);
					}

					//down attacks
					long downAttacks = occupied & AttackSets.rookAttacksD(i);
					closestPos = Long.numberOfTrailingZeros(downAttacks);
					closestPos = 63-closestPos;
					if(closestPos > 0){
						downAttacks = AttackSets.rookAttacksD(i) & ~(AttackSets.rookAttacksD(closestPos));
					}else{
						downAttacks = AttackSets.rookAttacksD(i);
					}

					//left attacks
					long leftAttacks = occupied & AttackSets.rookAttacksL(i);
					closestPos = Long.numberOfTrailingZeros(leftAttacks);
					closestPos = 63-closestPos;
					if(closestPos > 0){
						leftAttacks = AttackSets.rookAttacksL(i) & ~(AttackSets.rookAttacksL(closestPos));
					}else{
						leftAttacks = AttackSets.rookAttacksL(i);
					}

					//right attacks
					long rightAttacks = occupied & AttackSets.rookAttacksR(i);
					closestPos = Long.numberOfLeadingZeros(rightAttacks);
					if(closestPos != 64){
						rightAttacks = AttackSets.rookAttacksR(i) & ~(AttackSets.rookAttacksR(closestPos));
					}else{
						rightAttacks = AttackSets.rookAttacksR(i);
					}


					long rookAttacks = upAttacks | downAttacks | leftAttacks | rightAttacks;
					rookAttacks = rookAttacks & (enemies ^ empty);

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((rookAttacks>>j)&1)==1){
							String move = "";
							move = move + Util.convertNumToCoord(i);
							move = move + Util.convertNumToCoord(63-j);
							moveList.add(move);
						}
					}
				}
			}

			//Bishops
			
			String WBString = Long.toBinaryString(board.WB);
			WBString = Util.padBinaryString(WBString);
			
			int bishopsFound = 0;
			for(int i = 0; i < 64; i++) {
				if(bishopsFound == 2)
					break;
				
				if(WBString.charAt(i) == '1') {
					bishopsFound++;
					long URAttacks = occupied & AttackSets.diagRaysUR(i);
					
					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied & AttackSets.diagRaysUL(i);
					
					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied & AttackSets.diagRaysDL(i);
					
					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies ^ empty);

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((bishopAttacks>>j)&1)==1){
							String move = "";
							move = move + Util.convertNumToCoord(i);
							move = move + Util.convertNumToCoord(63-j);
							moveList.add(move);
						}
					}
				}
			}

			//Queen
			String WQString = Long.toBinaryString(board.WQ);
			WQString = Util.padBinaryString(WQString);

			for(int i = 0; i < 64; i++) {

				if(WQString.charAt(i) == '1') {

					String singleQueenString = "0000000000000000000000000000000000000000000000000000000000000000";
					singleQueenString = singleQueenString.substring(0, i) + "1" + singleQueenString.substring(i+1);
					long singleQueen = Util.stringToLong(singleQueenString);

					//long occupied = occupied() & AttackSets.rowMask(i/8);
					long horizontalAttacks = (occupied - 2 * singleQueen) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleQueen));
					horizontalAttacks = horizontalAttacks & AttackSets.rowMask(i/8);

					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleQueen)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleQueen)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long queenAttacks1 = verticalAttacks ^ horizontalAttacks;
					queenAttacks1 = queenAttacks1 & (enemies ^ empty);

					long URAttacks = occupied & AttackSets.diagRaysUR(i);

					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied & AttackSets.diagRaysUL(i);

					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied & AttackSets.diagRaysDL(i);

					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long diagAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					diagAttacks = diagAttacks & (enemies ^ empty);
					long queenAttacks = queenAttacks1 ^ diagAttacks;



					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((queenAttacks>>j)&1)==1){
							String move = "";
							move = move + Util.convertNumToCoord(i);
							move = move + Util.convertNumToCoord(63-j);
							moveList.add(move);
						}
					}
				}
			}

			//System.out.println(moveList);
			return moveList;

		}else if(playerColor.equals("BLACK")){
			//PAWNS-----------------------------------------------------------------------
			//Push one
			long BPMoves = board.BP << 8;
			long legalBPMoves = BPMoves & empty;
			
			//Push two
			long BPMoves2 = legalBPMoves << 8;
			long legalBPMoves2 = BPMoves2 & empty;
			long invalidateDoubleX2 = 4294967295L; //Bitboard to invalidate pawns doing double moves again
			legalBPMoves2 = invalidateDoubleX2 & legalBPMoves2;
			
			//Attacks up and to the left
			//long innerColumns = 9114861777597660798L; behövs ej FUNKAR HITTILLS
			long RightColumn0 = ~(72340172838076673L);

			long BPAttacksR = board.BP & RightColumn0;
			BPAttacksR = BPAttacksR << 7;
			
			BPAttacksR = BPAttacksR & enemies; //only possible if enemy present
			
			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;
			
			long BPAttacksL = board.BP & leftColumn0;
			BPAttacksL = BPAttacksL << 9;
			BPAttacksL = BPAttacksL & enemies; //only possible if enemy present
			
			long BPAttacks = BPAttacksL | BPAttacksR; //Probably not needed
			
			String BPMovesString = Long.toBinaryString(legalBPMoves);
			BPMovesString = Util.padBinaryString(BPMovesString); //Padding needed for 64-bits
			
			//Converting bitboards to move string:
			for(int i = 0; i<64; i++) {
				if(BPMovesString.charAt(i) == '1') {
					String move = "";
					move = move + Util.convertNumToAlph(i) + ((i/8)+2);
					move = move + Util.convertNumToAlph(i) + ((i/8)+1);
					moveList.add(move);
				}
			}
			
			String BPMoves2String = Long.toBinaryString(legalBPMoves2);
			BPMoves2String = Util.padBinaryString(BPMoves2String);
			
			for(int i = 0; i<64; i++) {
				if(BPMoves2String.charAt(i) == '1') {
					String move = "";
					move = move + Util.convertNumToAlph(i) + ((i/8)+3);
					move = move + Util.convertNumToAlph(i) + ((i/8)+1);
					moveList.add(move);
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
					String move = "";
					move = move + Util.convertNumToCoord(i+9);
					
					move = move + Util.convertNumToCoord(i);
					moveList.add(move);
				}

				if(BPAttacksRString.charAt(i) == '1') {
					String move = "";
					move = move + Util.convertNumToCoord(i+7);

					move = move + Util.convertNumToCoord(i);
					moveList.add(move);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos+1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos-8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
				}else if(board.enPassantPos % 8 == 7) {
					if((AttackSets.getPosition(board.enPassantPos)-1 & board.BP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos-1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos-8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos+1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos-8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.BP) != 0){
						String enPassantAttackStart = Util.convertNumToCoord(board.enPassantPos-1);
						String enPassantAttackEnd = Util.convertNumToCoord(board.enPassantPos-8);
						String enPassantAttack = enPassantAttackStart + enPassantAttackEnd;
						moveList.add(enPassantAttack);
					}
				}
			}
			
			//KNIGHTS
			String BNString = Long.toBinaryString(board.BN);
			BNString = Util.padBinaryString(BNString);
			
			int knightsFound = 0;
			for(int i = 0; i < 64; i++) {
				if(knightsFound == 2)
					break;
				if(BNString.charAt(i) == '1') {
					knightsFound++;
					
					long BNAttacks = AttackSets.knightMoves(i);
					String BNAttacksString = Long.toBinaryString(BNAttacks);
					BNAttacksString = Util.padBinaryString(BNAttacksString);
					
					long legalBNMoves2 = BNAttacks & empty;
					long legalBNMoves3 = BNAttacks & enemies;
					
					long legalBNMoves = legalBNMoves2 | legalBNMoves3;
					String legalBNMovesString = Long.toBinaryString(legalBNMoves);
					legalBNMovesString = Util.padBinaryString(legalBNMovesString);
					
					
					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 6)
							break;
						
						if(legalBNMovesString.charAt(j) == '1') {
							movesFound++;
							String move = "";
							move = move + Util.convertNumToAlph(i) + ((i/8)+1);
							move = move + Util.convertNumToAlph(j) + ((j/8)+1);
							moveList.add(move);
						}
					}
				}
			}
			
			//King
			String BKString = Long.toBinaryString(board.BK);
			BKString = Util.padBinaryString(BKString);
			for(int i = 0; i < 64; i++) {
				if(BKString.charAt(i) == '1') {
					
					long BKMoves = AttackSets.kingMoves(i);
					
					//Remove pseudolegal moves
					long legalBKMoves2 = BKMoves & empty;
					long legalBKMoves3 = BKMoves & enemies;
					long legalBKMoves = legalBKMoves2 | legalBKMoves3;

					if(board.BK == AttackSets.BKStart){
						if(board.castleBKValid){
							if((occupied & AttackSets.BKRblockers) == 0){
								if(board.check() == 0) {
									Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
									simBoard.BK = simBoard.BK ^ AttackSets.BKStart;
									simBoard.BK = simBoard.BK ^ AttackSets.castleBKR;
									if(simBoard.check() == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(61);

										if(simBoard.check() == 0)
											legalBKMoves = legalBKMoves | AttackSets.castleBKR;
									}

								}
							}
						}
						if(board.castleBQValid){
							if((occupied & AttackSets.BKLblockers) == 0){
								if(board.check() == 0){
									Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
									simBoard.BK = simBoard.BK ^ AttackSets.BKStart;
									simBoard.BK = simBoard.BK ^ AttackSets.castleBKL;
									if(simBoard.check() == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(3);
										if(simBoard.check() == 0){
											legalBKMoves = legalBKMoves | AttackSets.castleBKL;
										}
									}
								}
							}
						}
					}

					String legalMovesString = Long.toBinaryString(legalBKMoves);
					legalMovesString = Util.padBinaryString(legalMovesString);
					
					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8) 
							break;
						
						if(legalMovesString.charAt(j) == '1') {
							movesFound++;

							String move = "";
							move = move + Util.convertNumToAlph(i) + ((i/8)+1);
							move = move + Util.convertNumToAlph(j) + ((j/8)+1);
							moveList.add(move);
						}
					}
					break;
				}
			}
			
			//Rook
			String BRString = Long.toBinaryString(board.BR);
			BRString = Util.padBinaryString(BRString);

			int rooksFound = 0;
			for(int i = 0; i < 64; i++) {
				if(rooksFound == 2)
					break;
				
				if(BRString.charAt(i) == '1') {
					rooksFound++;
					
					//WRString = WRString.substring(i/8, i/8+8);
					//System.out.println(WRString);
					
					String singleRookString = "0000000000000000000000000000000000000000000000000000000000000000";
					singleRookString = singleRookString.substring(0, i) + "1" + singleRookString.substring(i+1);
					long singleRook = Util.stringToLong(singleRookString);
					
					//long occupied = occupied() & AttackSets.rowMask(i/8);
					long horizontalAttacks = (occupied - 2 * singleRook) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleRook));
					
					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleRook)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleRook)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long rookAttacks = verticalAttacks ^ horizontalAttacks;
					rookAttacks = rookAttacks & (enemies ^ empty);

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((rookAttacks>>j)&1)==1){
							String move = "";
							move = move + Util.convertNumToCoord(i);
							move = move + Util.convertNumToCoord(63-j);
							moveList.add(move);
						}
					}
				}
			}

			//Bishops

			String BBString = Long.toBinaryString(board.BB);
			BBString = Util.padBinaryString(BBString);

			int bishopsFound = 0;
			for(int i = 0; i < 64; i++) {
				if(bishopsFound == 2)
					break;

				if(BBString.charAt(i) == '1') {
					bishopsFound++;
					long URAttacks = occupied & AttackSets.diagRaysUR(i);

					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied & AttackSets.diagRaysUL(i);

					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied & AttackSets.diagRaysDL(i);

					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies ^ empty);

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((bishopAttacks>>j)&1)==1){
							String move = "";
							move = move + Util.convertNumToCoord(i);
							move = move + Util.convertNumToCoord(63-j);
							moveList.add(move);
						}
					}

				}
			}

			//Queen
			String BQString = Long.toBinaryString(board.BQ);
			BQString = Util.padBinaryString(BQString);

			for(int i = 0; i < 64; i++) {

				if(BQString.charAt(i) == '1') {

					String singleQueenString = "0000000000000000000000000000000000000000000000000000000000000000";
					singleQueenString = singleQueenString.substring(0, i) + "1" + singleQueenString.substring(i+1);
					long singleQueen = Util.stringToLong(singleQueenString);

					//long occupied = occupied() & AttackSets.rowMask(i/8);
					long horizontalAttacks = (occupied - 2 * singleQueen) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleQueen));
					horizontalAttacks = horizontalAttacks & AttackSets.rowMask(i/8);

					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleQueen)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleQueen)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long queenAttacks1 = verticalAttacks ^ horizontalAttacks;
					queenAttacks1 = queenAttacks1 & (enemies ^ empty);

					long URAttacks = occupied & AttackSets.diagRaysUR(i);

					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied & AttackSets.diagRaysUL(i);

					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied & AttackSets.diagRaysDL(i);

					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long diagAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					diagAttacks = diagAttacks & (enemies ^ empty);
					long queenAttacks = queenAttacks1 ^ diagAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((queenAttacks>>j)&1)==1){
							String move = "";
							move = move + Util.convertNumToCoord(i);
							move = move + Util.convertNumToCoord(63-j);
							moveList.add(move);
						}
					}
				}
			}
			//System.out.println(moveList);
			return moveList;
		}
		return null;
	}
	
	private long enemies(Board board) {
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
	
	private long friends() {
		long friends = 0L;
		
		if(color.equals("WHITE")) {
			friends = friends ^ board.WP;
			friends = friends ^ board.WR;
			friends = friends ^ board.WN;
			friends = friends ^ board.WB;
			friends = friends ^ board.WK;
			friends = friends ^ board.WQ;
			
		}else if(color.equals("BLACK")) {
			friends = friends ^ board.BP;
			friends = friends ^ board.BR;
			friends = friends ^ board.BN;
			friends = friends ^ board.BB;
			friends = friends ^ board.BK;
			friends = friends ^ board.BQ;
		}
		
		return friends;
	}
	
	private long empty(Board board) {
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
	
	private long occupied() {
		return ~empty(board);
	}



	public double alphaBetaMax(Board board, int depthLeft, double alpha, double beta, Stack<String> pv){
		if(depthLeft == 0){
			pv.clear();
			return evalPosition(board);
		}

		ArrayList<String> moves = this.findMoveList(board, "WHITE");

		for(String move : moves){
			Board simBoard = new Board(board);
			simBoard.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));

			double score = alphaBetaMin(simBoard, depthLeft - 1, alpha, beta, pv,move.equals("f2e3") || move.equals("d2e3"));
			if(move.equals("f2e3") || move.equals("d2e3")){
				System.out.println("HEJ " + score);
			}
			if(score >= beta){
				return beta;
			}
			if(score > alpha){
				pv.push(move);
				alpha = score;
			}
		}
		return alpha;
	}

	double alphaBetaMin(Board board, int depthLeft, double alpha, double beta, Stack<String> pv, boolean debug){
		if(depthLeft == 0){
			pv.clear();
			return evalPosition(board);
		}

		ArrayList<String> moves = this.findMoveList(board, "BLACK");

		for(String move : moves){
			if(debug){
				System.out.println(move);
			}
			Board simBoard = new Board(board);
			simBoard.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));

			double score = alphaBetaMax(simBoard, depthLeft - 1, alpha, beta, pv);
			if(debug){
				System.out.println(score);
			}
			if(score <= alpha){
				return alpha;
			}
			if(score < beta){
				pv.push(move);
				beta = score;
			}
		}
		return beta;
	}
	
	private double evalPosition(Board board) {
		double points = 0;

		if(board.checkmate() == 1){
			return -Double.MAX_VALUE;
		}else if(board.checkmate() == 2){
			return Double.MAX_VALUE;
		}

		for(int i = 0; i < 64; i++){
			long pos = AttackSets.getPosition(i);

			if((board.WP & pos) != 0) {
				points += 1;
			}else if((board.WR & pos) != 0){
				points += 5;
			}else if((board.WN & pos) != 0){
				points += 3;
			}else if((board.WB & pos) != 0){
				points += 3;
			}else if((board.WQ & pos) != 0){
				points += 9;
			}else if((board.BP & pos) != 0){
				points -= 1;
			}else if((board.BR & pos) != 0){
				points -= 5;
			}else if((board.BN & pos) != 0){
				points -= 3;
			}else if((board.BB & pos) != 0){
				points -= 3;
			}else if((board.BQ & pos) != 0){
				points -= 9;
			}
		}
		return points;
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
        System.out.println("-----------------");
    }
}
