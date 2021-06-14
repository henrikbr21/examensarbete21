package engine;

import java.util.Arrays;

public class Engine {
	private String color; //"BLACK" for black pieces, "WHITE" for white pieces
	private Board board;
	private long full = ~0L;

	
	public Engine(String color, Board board){
		long time = System.currentTimeMillis();
		this.board = board;
		this.color = color;
		AttackSets.initKnightMoves();
		AttackSets.initKingMoves();
		AttackSets.initRookMoves();
		AttackSets.initLineMasks();
		AttackSets.initLeftRays();
		AttackSets.initRightRays();
		time = System.currentTimeMillis() - time;
		System.out.println("Initialization took " + time + "ms.");
	}
	
	public String generateMoves(String playerColor) {

		String moveList = "";


		if(playerColor.equals("WHITE")) {

			//PAWNS-----------------------------------------------------------------------
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
			
			for(int i = 0; i < 64; i++) {
				if(WPAttacksLString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i-7) + (((i-7)/8)+1);
					
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
				}
				
				if(WPAttacksRString.charAt(i) == '1') {
					moveList = moveList + Util.convertNumToAlph(i-9) + (((i-9)/8)+1);
					
					moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1) + " ";
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
					
					long legalWNMoves2 = WNAttacks & empty();
					long legalWNMoves3 = WNAttacks & enemies();
					
					long legalWNMoves = legalWNMoves2 | legalWNMoves3;
					String legalWNMovesString = Long.toBinaryString(legalWNMoves);
					legalWNMovesString = Util.padBinaryString(legalWNMovesString);
					
					System.out.println(legalWNMovesString);
					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 6)
							break;
						
						if(legalWNMovesString.charAt(j) == '1') {
							movesFound++;
							
							moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1);
							moveList = moveList + Util.convertNumToAlph(j) + ((j/8)+1) + " ";
						}
					}
				}
			}
			
			//King
			String WKString = Long.toBinaryString(board.WK);
			WKString = Util.padBinaryString(WKString);
			System.out.println(WKString);
			
			for(int i = 0; i < 64; i++) {
				if(WKString.charAt(i) == '1') {
					
					long WKMoves = AttackSets.kingMoves(i);
					
					//Remove pseudolegal moves
					long legalWKMoves2 = WKMoves & empty();
					long legalWKMoves3 = WKMoves & enemies();
					long legalWKMoves = legalWKMoves2 | legalWKMoves3;

					if(board.castleWKValid){
						if((occupied() & AttackSets.WKRblockers) == 0){
							legalWKMoves = legalWKMoves | AttackSets.castleWKR;
						}
					}
					if(board.castleWQValid){
						if((occupied() & AttackSets.WKLblockers) == 0){
							legalWKMoves = legalWKMoves | AttackSets.castleWKL;
						}
					}

					String legalMovesString = Long.toBinaryString(legalWKMoves);
					legalMovesString = Util.padBinaryString(legalMovesString);

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8) 
							break;
						
						if(legalMovesString.charAt(j) == '1') {
							movesFound++;
							moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1);
							moveList = moveList + Util.convertNumToAlph(j) + ((j/8)+1) + " ";
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
					
					//long occupied = occupied() & AttackSets.rowMask(i/8);
					long occupied = occupied();
					long horizontalAttacks = (occupied - 2 * singleRook) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleRook));
					horizontalAttacks = horizontalAttacks & AttackSets.rowMask(i/8);

					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleRook)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleRook)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long rookAttacks = verticalAttacks ^ horizontalAttacks;
					rookAttacks = rookAttacks & (enemies() ^ empty());

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((rookAttacks>>j)&1)==1){
							moveList = moveList + Util.convertNumToCoord(i);
							moveList = moveList + Util.convertNumToCoord(63-j) + " ";
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
					long URAttacks = occupied() & AttackSets.diagRaysUR(i);
					
					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied() & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied() & AttackSets.diagRaysUL(i);
					
					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied() & AttackSets.diagRaysDL(i);
					
					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies() ^ empty());

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((bishopAttacks>>j)&1)==1){
							moveList = moveList + Util.convertNumToCoord(i);
							moveList = moveList + Util.convertNumToCoord(63-j) + " ";
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
					long occupied = occupied();
					long horizontalAttacks = (occupied - 2 * singleQueen) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleQueen));
					horizontalAttacks = horizontalAttacks & AttackSets.rowMask(i/8);

					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleQueen)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleQueen)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long queenAttacks1 = verticalAttacks ^ horizontalAttacks;
					queenAttacks1 = queenAttacks1 & (enemies() ^ empty());

					long URAttacks = occupied() & AttackSets.diagRaysUR(i);

					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied() & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied() & AttackSets.diagRaysUL(i);

					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied() & AttackSets.diagRaysDL(i);

					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long diagAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					diagAttacks = diagAttacks & (enemies() ^ empty());
					long queenAttacks = queenAttacks1 ^ diagAttacks;



					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((queenAttacks>>j)&1)==1){
							moveList = moveList + Util.convertNumToCoord(i);
							moveList = moveList + Util.convertNumToCoord(63-j) + " ";
						}
					}
				}
			}

			int count = 0;
			for (int i = 0; i < moveList.length(); i++) {
				if (moveList.charAt(i) == ' ') {
					count++;
				}
			}
			System.out.println("Number of moves:" + count);

			System.out.println(moveList);
			return moveList;

		}else if(playerColor.equals("BLACK")){
			//PAWNS-----------------------------------------------------------------------
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
					
					long legalBNMoves2 = BNAttacks & empty();
					long legalBNMoves3 = BNAttacks & enemies();
					
					long legalBNMoves = legalBNMoves2 | legalBNMoves3;
					String legalBNMovesString = Long.toBinaryString(legalBNMoves);
					legalBNMovesString = Util.padBinaryString(legalBNMovesString);
					
					
					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 2)
							break;
						
						if(legalBNMovesString.charAt(j) == '1') {
							movesFound++;
							
							moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1);
							moveList = moveList + Util.convertNumToAlph(j) + ((j/8)+1) + " ";
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
					long legalBKMoves2 = BKMoves & empty();
					long legalBKMoves3 = BKMoves & enemies();
					long legalBKMoves = legalBKMoves2 | legalBKMoves3;

					if(board.castleBKValid){
						if((occupied() & AttackSets.BKRblockers) == 0){
							legalBKMoves = legalBKMoves | AttackSets.castleBKR;
						}
					}
					if(board.castleBQValid){
						if((occupied() & AttackSets.BKLblockers) == 0){
							legalBKMoves = legalBKMoves | AttackSets.castleBKL;
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
							moveList = moveList + Util.convertNumToAlph(i) + ((i/8)+1);
							moveList = moveList + Util.convertNumToAlph(j) + ((j/8)+1) + " ";
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
					long occupied = occupied();
					long horizontalAttacks = (occupied - 2 * singleRook) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleRook));
					
					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleRook)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleRook)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long rookAttacks = verticalAttacks ^ horizontalAttacks;
					rookAttacks = rookAttacks & (enemies() ^ empty());

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((rookAttacks>>j)&1)==1){
							moveList = moveList + Util.convertNumToCoord(i);
							moveList = moveList + Util.convertNumToCoord(63-j) + " ";
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
					long URAttacks = occupied() & AttackSets.diagRaysUR(i);

					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied() & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied() & AttackSets.diagRaysUL(i);

					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied() & AttackSets.diagRaysDL(i);

					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies() ^ empty());

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((bishopAttacks>>j)&1)==1){
							moveList = moveList + Util.convertNumToCoord(i);
							moveList = moveList + Util.convertNumToCoord(63-j) + " ";
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
					long occupied = occupied();
					long horizontalAttacks = (occupied - 2 * singleQueen) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleQueen));
					horizontalAttacks = horizontalAttacks & AttackSets.rowMask(i/8);

					long verticalAttacks = ((occupied&AttackSets.colMask(i%8)) - (2 * singleQueen)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(i%8)) - (2 * Long.reverse(singleQueen)));
					verticalAttacks = verticalAttacks & AttackSets.colMask(i%8);
					long queenAttacks1 = verticalAttacks ^ horizontalAttacks;
					queenAttacks1 = queenAttacks1 & (enemies() ^ empty());

					long URAttacks = occupied() & AttackSets.diagRaysUR(i);

					int closestPos = Long.numberOfLeadingZeros(URAttacks);
					if(closestPos != 64){
						URAttacks = AttackSets.diagRaysUR(i) & ~(AttackSets.diagRaysUR(closestPos));
					}else{
						URAttacks = AttackSets.diagRaysUR(i);
					}

					long DRAttacks = occupied() & AttackSets.diagRaysDR(i);
					closestPos = Long.numberOfTrailingZeros(DRAttacks);
					closestPos = 63 - closestPos;
					if(closestPos > 0){
						DRAttacks = AttackSets.diagRaysDR(i) & ~(AttackSets.diagRaysDR(closestPos));
					}else{
						DRAttacks = AttackSets.diagRaysDR(i);
					}

					long ULAttacks = occupied() & AttackSets.diagRaysUL(i);

					closestPos = Long.numberOfLeadingZeros(ULAttacks);
					if(closestPos != 64){
						ULAttacks = AttackSets.diagRaysUL(i) & ~(AttackSets.diagRaysUL(closestPos));
					}else{
						ULAttacks = AttackSets.diagRaysUL(i);
					}

					long DLAttacks = occupied() & AttackSets.diagRaysDL(i);

					closestPos = Long.numberOfTrailingZeros(DLAttacks);
					closestPos = 63 - closestPos;

					if(closestPos > 0){
						DLAttacks = AttackSets.diagRaysDL(i) & ~(AttackSets.diagRaysDL(closestPos));
					}else{
						DLAttacks = AttackSets.diagRaysDL(i);
					}

					long diagAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					diagAttacks = diagAttacks & (enemies() ^ empty());
					long queenAttacks = queenAttacks1 ^ diagAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if(((queenAttacks>>j)&1)==1){
							moveList = moveList + Util.convertNumToCoord(i);
							moveList = moveList + Util.convertNumToCoord(63-j) + " ";
						}
					}
				}
			}
			System.out.println(moveList);
			return moveList;
		}
		return "";
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
	
	private long occupied() {
		return ~empty();
	}
	
	
	
	private String search() { //return type?
		return "";
	}
	
	private double evalPosition() {
		return 0;
	}
	
	//returns 0 if no players are checked, 1 if the white player is checked, 2 if the black player is checked
	// and 3 if both players are checked.
	public int check(Board board) {
		return 0;
	}
	
	
	//returns 0 if no players are checkmated, 1 if the white player is checkmated, 2 if the black player is checkmated
	public int checkmate(Board board) {
		return 0;
	}
	
	private String rookMoves(int xPos, int yPos, String COLOR) {
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
        System.out.println("-----------------");
    }
    
    
    public void makeMove(String move) {
    	
    }
}
