package engine;

import java.sql.Array;
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
			AttackSets.initPSTs();
		}
		time = System.currentTimeMillis() - time;
		if(!initialized)
			System.out.println("Initialization took " + time + "ms.");
	}

	public ArrayList<Move> findMoveList(Board board, String playerColor){
		ArrayList<Move> legalMoves = new ArrayList<Move>();

 		if(playerColor == "WHITE"){
			if(board.checkmate() == 1)
				return new ArrayList<Move>();
			else{
				ArrayList<Move> moves = this.generateMoves(board,"WHITE");

				for(Move move : moves){
					Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
					simBoard.makeMove(move.from, move.to);
					if(simBoard.BK == 0L || simBoard.WK == 0L) //SKA DESSA VARA HÄR??
						continue;
					int simCheck = simBoard.check();
					if(simCheck == 2 || simCheck == 0)
						legalMoves.add(move);
				}
			}
		}else if(playerColor == "BLACK") {
 			if(board.checkmate() == 2)
				return new ArrayList<Move>();
			else{
				ArrayList<Move> moves = this.generateMoves(board,"BLACK");

				for(Move move : moves){
					Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
					simBoard.makeMove(move.from, move.to);
					if(simBoard.BK == 0L || simBoard.WK == 0L) //SKA DESSA VARA HÄR??
						continue;
					int simCheck = simBoard.check();
					if(simCheck == 1 || simCheck == 0)
						legalMoves.add(move);
				}
			}
		}
		return legalMoves;
	}

	public ArrayList<Move> generateMoves(Board board, String playerColor){
		ArrayList<Move> moveList = new ArrayList<Move>();

		long occupied = occupied(board);
		long empty = empty(board);

		long enemies = 0L;
		long friends = 0L;
		if((playerColor == "WHITE" && this.color == "WHITE") || (playerColor == "BLACK" && this.color == "BLACK")){
			enemies = enemies(board);
			friends = friends(board);
		}else if((playerColor == "BLACK" && this.color == "WHITE") || (playerColor == "WHITE" && this.color == "BLACK")){
			enemies = friends(board);
			friends = enemies(board);
		}

		long attackBoard = 0L;
		if(playerColor == "WHITE"){
			long WPMoves = board.WP >>> 8;
			long legalWPMoves = WPMoves & empty;

			long WPMoves2 = legalWPMoves >>> 8;
			long legalWPMoves2 = WPMoves2 & empty;
			long invalidateDoubleX2 = 9223372032559808512L; //Bitboard to invalidate pawns doing double moves again
			legalWPMoves2 = invalidateDoubleX2 & legalWPMoves2;

			//Attacks up and to the left
			long RightColumn0 = ~(72340172838076673L);

			long WPAttacksR = board.WP & RightColumn0;
			WPAttacksR = WPAttacksR >>> 9;

			WPAttacksR = WPAttacksR & enemies; //only possible if enemy present

			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;

			long WPAttacksL = board.WP & leftColumn0;
			WPAttacksL = WPAttacksL >>> 7;

			attackBoard = WPAttacksL | WPAttacksR;
			WPAttacksL = WPAttacksL & enemies; //ISSUE?!?!!


			for(int i = 0; i<64; i++) {
				long pos = AttackSets.getPosition(i);
				if((pos & legalWPMoves) != 0) {
					Move move = new Move(i-8, i);
					moveList.add(move);
				}
				if((pos & legalWPMoves2) != 0) {
					Move move = new Move(i-16, i);
					moveList.add(move);
				}
				if((pos & WPAttacksL) != 0) {
					Move move = new Move(i-7, i);
					moveList.add(move);
				}
				if((pos & WPAttacksR) != 0) {
					Move move = new Move(i-9, i);
					moveList.add(move);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP) != 0 && board.enPassantPlayer == 2){
						Move enPassantAttack = new Move(board.enPassantPos+1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
				}else if(board.enPassantPos % 8 == 7 && board.enPassantPlayer == 2) {
					if((AttackSets.getPosition(board.enPassantPos)-1 & board.WP) != 0){
						Move enPassantAttack = new Move(board.enPassantPos-1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP ) != 0 && board.enPassantPlayer == 2){
						Move enPassantAttack = new Move(board.enPassantPos+1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.WP) != 0 && board.enPassantPlayer == 2){
						Move enPassantAttack = new Move(board.enPassantPos-1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
				}
			}

			for(int i = 0; i < 64; i++){
				if((board.WN & AttackSets.getPosition(i)) != 0){
					long WNAttacks = AttackSets.knightMoves(i);
					long legalWNMoves2 = WNAttacks & empty;
					long legalWNMoves3 = WNAttacks & enemies;

					long legalWNMoves = legalWNMoves2 | legalWNMoves3;
					attackBoard = attackBoard | legalWNMoves;

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8)
							break;

						if((legalWNMoves & AttackSets.getPosition(j)) != 0) {
							movesFound++;

							Move move = new Move(i, j);
							moveList.add(move);
						}
					}
				}else if((board.WB & AttackSets.getPosition(i)) != 0){
					long bishopAttacks = 0L;
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

					bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies ^ empty);
					attackBoard = attackBoard | bishopAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((bishopAttacks & AttackSets.getPosition(j)) != 0){
							Move move = new Move(i, j); // 63-j???
							moveList.add(move);
						}
					}
				}else if((board.WR & AttackSets.getPosition(i)) != 0){
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
					attackBoard = attackBoard | rookAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((rookAttacks & AttackSets.getPosition(j)) != 0){
							Move move = new Move(i, j);  // 63-j???
							moveList.add(move);
						}
					}
				}else if((board.WQ & AttackSets.getPosition(i)) != 0){
					long singleQueen = AttackSets.getPosition(i);

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
					attackBoard = attackBoard | queenAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((queenAttacks & AttackSets.getPosition(j)) != 0){ //((queenAttacks>>j)&1)==1
							Move move = new Move(i, j); //63-j??
							moveList.add(move);
						}
					}
				}else if((board.WK & AttackSets.getPosition(i)) != 0){
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(5);

										if(simCheck == 0 && ((friends & AttackSets.wRightRookStart) != 0))
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(3);
										if(simCheck == 0 && ((friends & AttackSets.wLeftRookStart) != 0)){
											legalWKMoves = legalWKMoves | AttackSets.castleWKL;
										}
									}
								}
							}
						}
					}

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8)
							break;

						if((legalWKMoves & AttackSets.getPosition(j)) != 0) {
							Move move = new Move(i, j);
							moveList.add(move);
						}
					}
				}
			}
		}else if(playerColor == "BLACK"){
			long BPMoves = board.BP << 8;
			long legalBPMoves = BPMoves & empty;

			long BPMoves2 = legalBPMoves << 8;
			long legalBPMoves2 = BPMoves2 & empty;
			long invalidateDoubleX2 = 4294967295L; //Bitboard to invalidate pawns doing double moves again
			legalBPMoves2 = invalidateDoubleX2 & legalBPMoves2;

			//Attacks up and to the left
			long RightColumn0 = ~(72340172838076673L);

			long BPAttacksR = board.BP & RightColumn0;
			BPAttacksR = BPAttacksR << 7;

			BPAttacksR = BPAttacksR & enemies; //only possible if enemy present

			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;

			long BPAttacksL = board.BP & leftColumn0;
			BPAttacksL = BPAttacksL << 9;

			attackBoard = BPAttacksL | BPAttacksR;
			BPAttacksL = BPAttacksL & enemies; //only possible if enemy present

			//Converting bitboards to move string:
			for(int i = 0; i<64; i++) {
				long pos = AttackSets.getPosition(i);

				if((legalBPMoves & pos) != 0) {
					Move move = new Move(i+8, i);
					moveList.add(move);
				}
				if((legalBPMoves2 & pos) != 0) {
					Move move = new Move(i+16, i);
					moveList.add(move);
				}
				if((BPAttacksL & pos) != 0) {
					Move move = new Move(i+9, i);
					moveList.add(move);
				}

				if((BPAttacksR & pos) != 0) {
					Move move = new Move(i+7, i);
					moveList.add(move);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0 && board.enPassantPlayer == 1){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0){
						moveList.add(new Move(board.enPassantPos+1, board.enPassantPos-8));
					}
				}else if(board.enPassantPos % 8 == 7 && board.enPassantPlayer == 1) {
					if((AttackSets.getPosition(board.enPassantPos)-1 & board.BP) != 0){
						moveList.add(new Move(board.enPassantPos-1, board.enPassantPos-8));
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0 && board.enPassantPlayer == 1){
						moveList.add(new Move(board.enPassantPos+1, board.enPassantPos-8));
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.BP) != 0 && board.enPassantPlayer == 1){
						moveList.add(new Move(board.enPassantPos-1, board.enPassantPos-8));
					}
				}
			}

			for(int i = 0; i < 64; i++){
				if((board.BN & AttackSets.getPosition(i)) != 0){
					long BNAttacks = AttackSets.knightMoves(i);
					long legalBNMoves2 = BNAttacks & empty;
					long legalBNMoves3 = BNAttacks & enemies;

					long legalBNMoves = legalBNMoves2 | legalBNMoves3;
					attackBoard = attackBoard | legalBNMoves;

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8)
							break;

						if((legalBNMoves & AttackSets.getPosition(j)) != 0) {
							movesFound++;
							moveList.add(new Move(i, j));
						}
					}
				}else if((board.BB & AttackSets.getPosition(i)) != 0){
					long bishopAttacks = 0L;
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

					bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies ^ empty);
					attackBoard = attackBoard | bishopAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((bishopAttacks & AttackSets.getPosition(j)) != 0){
							moveList.add(new Move(i, j)); //63-j????
						}
					}
				}else if((board.BR & AttackSets.getPosition(i)) != 0){
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
					attackBoard = attackBoard | rookAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((rookAttacks & AttackSets.getPosition(j)) != 0){
							moveList.add(new Move(i, j)); //63-j???
						}
					}
				}else if((board.BQ & AttackSets.getPosition(i)) != 0){
					long singleQueen = AttackSets.getPosition(i);

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
					attackBoard = attackBoard | queenAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((queenAttacks & AttackSets.getPosition(j)) != 0){
							moveList.add(new Move(i, j)); //63-j???+?
						}
					}
				}else if((board.BK & AttackSets.getPosition(i)) != 0){
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(61);

										if(simCheck == 0 && ((friends & AttackSets.bRightRookStart) != 0))
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(3);
										if(simCheck == 0 && ((friends & AttackSets.wLeftRookStart) != 0)){
											legalBKMoves = legalBKMoves | AttackSets.castleBKL;
										}
									}
								}
							}
						}
					}

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8)
							break;

						if((legalBKMoves & AttackSets.getPosition(j)) != 0) {
							moveList.add(new Move(i, j));
						}
					}
				}
			}
		}
		AttackSets.currentAttackBoard = attackBoard;
		return moveList;
	}

	public ArrayList<Move> generateMoves2(Board board, String playerColor) {

		//String moveList = "";
		ArrayList<Move> moveList = new ArrayList<Move>();

		long occupied = occupied(board);
		long empty = empty(board);

		long enemies = 0L;
		long friends = 0L;
		if((playerColor == "WHITE" && this.color == "WHITE") || (playerColor == "BLACK" && this.color == "BLACK")){
			enemies = enemies(board);
			friends = friends(board);
		}else if((playerColor == "BLACK" && this.color == "WHITE") || (playerColor == "WHITE" && this.color == "BLACK")){
			enemies = friends(board);
 			friends = enemies(board);
		}

		long attackBoard = 0L;
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

			attackBoard = WPAttacksL | WPAttacksR;
			WPAttacksL = WPAttacksL & enemies; //ISSUE?!?!!


			for(int i = 0; i<64; i++) {
				if((AttackSets.getPosition(i) & legalWPMoves) != 0) {
					Move move = new Move(i-8, i);
					moveList.add(move);
				}

			}


			for(int i = 0; i<64; i++) {
				if((AttackSets.getPosition(i) & legalWPMoves2) != 0) {
					Move move = new Move(i-16, i);
					moveList.add(move);
				}
			}

			for(int i = 0; i < 64; i++) {
				if((AttackSets.getPosition(i) & WPAttacksL) != 0) {
					Move move = new Move(i-7, i);
					moveList.add(move);
				}
				if((AttackSets.getPosition(i) & WPAttacksR) != 0) {
					Move move = new Move(i-9, i);
					moveList.add(move);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP) != 0 && board.enPassantPlayer == 2){
						Move enPassantAttack = new Move(board.enPassantPos+1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
				}else if(board.enPassantPos % 8 == 7 && board.enPassantPlayer == 2) {
					if((AttackSets.getPosition(board.enPassantPos)-1 & board.WP) != 0){
						Move enPassantAttack = new Move(board.enPassantPos-1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP ) != 0 && board.enPassantPlayer == 2){
						Move enPassantAttack = new Move(board.enPassantPos+1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.WP) != 0 && board.enPassantPlayer == 2){
						Move enPassantAttack = new Move(board.enPassantPos-1, board.enPassantPos+8);
						moveList.add(enPassantAttack);
					}
				}
			}
			
			//KNIGHTS
			
			int knightsFound = 0;
			long legalWNMoves = 0L;
			for(int i = 0; i < 64; i++) {
				if(knightsFound == 2)
					break;
				if((board.WN & AttackSets.getPosition(i)) != 0) {
					knightsFound++;
					
					long WNAttacks = AttackSets.knightMoves(i);
					
					long legalWNMoves2 = WNAttacks & empty;
					long legalWNMoves3 = WNAttacks & enemies;
					
					legalWNMoves = legalWNMoves2 | legalWNMoves3;
					attackBoard = attackBoard | legalWNMoves;

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8)
							break;

						if((legalWNMoves & AttackSets.getPosition(j)) != 0) {
							movesFound++;

							Move move = new Move(i, j);
							moveList.add(move);
						}
					}
				}
			}
			
			//King
			
			for(int i = 0; i < 64; i++) {
				if((board.WK & AttackSets.getPosition(i)) != 0) {
					
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(5);

										if(simCheck == 0 && ((friends & AttackSets.wRightRookStart) != 0))
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(3);
										if(simCheck == 0 && ((friends & AttackSets.wLeftRookStart) != 0)){
											legalWKMoves = legalWKMoves | AttackSets.castleWKL;
										}
									}
								}
							}
						}
					}

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8) 
							break;
						
						if((legalWKMoves & AttackSets.getPosition(j)) != 0) {
							Move move = new Move(i, j);
							moveList.add(move);
						}
					}
					break;
				}
			}
			
			//Rook
			int rooksFound = 0;
			long rookAttacks = 0L;
			for(int i = 0; i < 64; i++) {
				if(rooksFound == 2)
					break;
				
				if((board.WR & AttackSets.getPosition(i)) != 0) {
					rooksFound++;

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


					rookAttacks = upAttacks | downAttacks | leftAttacks | rightAttacks;
					rookAttacks = rookAttacks & (enemies ^ empty);
					attackBoard = attackBoard | rookAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((rookAttacks & AttackSets.getPosition(j)) != 0){
							Move move = new Move(i, j);  // 63-j???
							moveList.add(move);
						}
					}
				}
			}

			//Bishops
			long bishopAttacks = 0L;
			int bishopsFound = 0;
			for(int i = 0; i < 64; i++) {
				if(bishopsFound == 2)
					break;
				
				if((board.WB & AttackSets.getPosition(i)) != 0) {
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

					bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies ^ empty);
					attackBoard = attackBoard | bishopAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((bishopAttacks & AttackSets.getPosition(j)) != 0){
							Move move = new Move(i, j); // 63-j???
							moveList.add(move);
						}
					}
				}
			}

			//Queen
			long queenAttacks = 0L;
			for(int i = 0; i < 64; i++) {
				if((board.WQ & AttackSets.getPosition(i)) != 0) {

					long singleQueen = AttackSets.getPosition(i);

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
					queenAttacks = queenAttacks1 ^ diagAttacks;
					attackBoard = attackBoard | queenAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((queenAttacks & AttackSets.getPosition(j)) != 0){ //((queenAttacks>>j)&1)==1
							Move move = new Move(i, j); //63-j??
							moveList.add(move);
						}
					}
				}
			}
			AttackSets.currentAttackBoard = attackBoard;
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

			attackBoard = BPAttacksL | BPAttacksR;
			BPAttacksL = BPAttacksL & enemies; //only possible if enemy present

			//Converting bitboards to move string:
			for(int i = 0; i<64; i++) {
				if((legalBPMoves & AttackSets.getPosition(i)) != 0) {
					Move move = new Move(i+8, i);
					moveList.add(move);
				}
			}

			for(int i = 0; i<64; i++) {
				if((legalBPMoves2 & AttackSets.getPosition(i)) != 0) {
					Move move = new Move(i+16, i);
					moveList.add(move);
				}
			}

			for(int i = 0; i<64; i++) {
				if((BPAttacksL & AttackSets.getPosition(i)) != 0) {
					Move move = new Move(i+9, i);
					moveList.add(move);
				}

				if((BPAttacksR & AttackSets.getPosition(i)) != 0) {
					Move move = new Move(i+7, i);
					moveList.add(move);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0 && board.enPassantPlayer == 1){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0){
						moveList.add(new Move(board.enPassantPos+1, board.enPassantPos-8));
					}
				}else if(board.enPassantPos % 8 == 7 && board.enPassantPlayer == 1) {
					if((AttackSets.getPosition(board.enPassantPos)-1 & board.BP) != 0){
						moveList.add(new Move(board.enPassantPos-1, board.enPassantPos-8));
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0 && board.enPassantPlayer == 1){
						moveList.add(new Move(board.enPassantPos+1, board.enPassantPos-8));
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.BP) != 0 && board.enPassantPlayer == 1){
						moveList.add(new Move(board.enPassantPos-1, board.enPassantPos-8));
					}
				}
			}
			
			//KNIGHTS
			long legalBNMoves = 0L;
			int knightsFound = 0;
			for(int i = 0; i < 64; i++) {
				if(knightsFound == 2)
					break;
				if((board.BN & AttackSets.getPosition(i)) != 0) {
					knightsFound++;
					
					long BNAttacks = AttackSets.knightMoves(i);
					long legalBNMoves2 = BNAttacks & empty;
					long legalBNMoves3 = BNAttacks & enemies;
					
					legalBNMoves = legalBNMoves2 | legalBNMoves3;
					attackBoard = attackBoard | legalBNMoves;

					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8)
							break;
						
						if((legalBNMoves & AttackSets.getPosition(j)) != 0) {
							movesFound++;
							moveList.add(new Move(i, j));
						}
					}
				}
			}
			
			//King
			for(int i = 0; i < 64; i++) {
				if((board.BK & AttackSets.getPosition(i)) != 0) {
					
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(61);

										if(simCheck == 0 && ((friends & AttackSets.bRightRookStart) != 0))
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
									int simCheck = simBoard.check();
									if(simCheck == 0){
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(3);
										if(simCheck == 0 && ((friends & AttackSets.wLeftRookStart) != 0)){
											legalBKMoves = legalBKMoves | AttackSets.castleBKL;
										}
									}
								}
							}
						}
					}

					
					int movesFound = 0;
					for(int j = 0; j < 64; j++) {
						if(movesFound == 8) 
							break;
						
						if((legalBKMoves & AttackSets.getPosition(j)) != 0) {
							moveList.add(new Move(i, j));
						}
					}
					break;
				}
			}
			
			//Rook
			long rookAttacks = 0L;
			int rooksFound = 0;
			for(int i = 0; i < 64; i++) {
				if(rooksFound == 2)
					break;

				if((board.BR & AttackSets.getPosition(i)) != 0) {
					rooksFound++;

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


					rookAttacks = upAttacks | downAttacks | leftAttacks | rightAttacks;
					rookAttacks = rookAttacks & (enemies ^ empty);
					attackBoard = attackBoard | rookAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((rookAttacks & AttackSets.getPosition(j)) != 0){
							moveList.add(new Move(i, j)); //63-j???
						}
					}
				}
			}

			//Bishops
			int bishopsFound = 0;
			long bishopAttacks = 0L;
			for(int i = 0; i < 64; i++) {
				if(bishopsFound == 2)
					break;

				if((board.BB & AttackSets.getPosition(i)) != 0) {
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

					bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
					bishopAttacks = bishopAttacks & (enemies ^ empty);
					attackBoard = attackBoard | bishopAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((bishopAttacks & AttackSets.getPosition(j)) != 0){
							moveList.add(new Move(i, j)); //63-j????
						}
					}

				}
			}

			//Queen
			long queenAttacks = 0L;
			for(int i = 0; i < 64; i++) {

				if((board.BQ & AttackSets.getPosition(i)) != 0) {

					long singleQueen = AttackSets.getPosition(i);

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
					queenAttacks = queenAttacks1 ^ diagAttacks;
					attackBoard = attackBoard | queenAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((queenAttacks & AttackSets.getPosition(j)) != 0){
							moveList.add(new Move(i, j)); //63-j???+?
						}
					}
				}
			}
			AttackSets.currentAttackBoard = attackBoard;
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
	
	private long friends(Board board) {
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
	
	private long occupied(Board board) {
		return ~empty(board);
	}



	public double alphaBetaMax(Board board, int depthLeft, double alpha, double beta, ArrayList<Move> pv, int depth, int whiteMoves, int blackMoves){
		if(depthLeft == 0){
			pv.clear();
			return evalPosition(board);
		}
		depth++;
		ArrayList<Move> moves = this.findMoveList(board, "WHITE");
		whiteMoves = moves.size();
		boolean first = true;
		ArrayList<Move> localPV = new ArrayList<Move>();

		for(Move move : moves){
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			double score = alphaBetaMin(simBoard, depthLeft - 1, alpha, beta, localPV, depth, whiteMoves, blackMoves);
			if(score >= beta){
				return beta;
			}
			if(score > alpha){
				pv.clear();
				pv.addAll(localPV);
				pv.add(move);

				alpha = score;
			}
		}
		return alpha;
	}

	double alphaBetaMin(Board board, int depthLeft, double alpha, double beta, ArrayList<Move> pv, int depth, int whiteMoves, int blackMoves){
		if(depthLeft == 0){
			pv.clear();
			return evalPosition(board);
		}
		depth++;
		ArrayList<Move> moves = this.findMoveList(board, "BLACK");
		blackMoves = moves.size();
		boolean first = true;
		ArrayList<Move> localPV = new ArrayList<Move>();

		for(Move move : moves){
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			double score = alphaBetaMax(simBoard, depthLeft - 1, alpha, beta, localPV, depth, whiteMoves, blackMoves);

			if(score <= alpha){
				return alpha;
			}
			if(score < beta){
				pv.clear();
				pv.addAll(localPV);
				pv.add(move);

				beta = score;
			}
		}
		return beta;
	}
	
	public double evalPosition(Board board) {
		double points = 0;

		int checkmate = board.checkmate();

		if(checkmate == 1){
			return -Double.MAX_VALUE;
		}else if(checkmate == 2){
			return Double.MAX_VALUE;
		}

		for(int i = 0; i < 64; i++){
			long pos = AttackSets.getPosition(i);

			if((board.WP & pos) != 0) {
				points = points + 100;
				if((board.WP & AttackSets.getPosition(i+8)) != 0)
					points = points - 50; //blocked or doubled pawn

				points = points + AttackSets.wPawnsPST[i];
			}else if((board.WR & pos) != 0){
				points = points + 500;

				points = points + AttackSets.wRookPST[i];
			}else if((board.WN & pos) != 0){
				points = points + 320;

				points = points + AttackSets.wKnightPST[i];
			}else if((board.WB & pos) != 0){
				points = points + 330;

				points = points + AttackSets.wBishopPST[i];
			}else if((board.WQ & pos) != 0){
				points = points + 900;

				points = points + AttackSets.wQueenPST[i];
			}else if((board.WK & pos) != 0){
				points = points + AttackSets.wKingPST[i];

			}else if((board.BP & pos) != 0){
				points = points - 100;
				if((board.BP & AttackSets.getPosition(i-8)) != 0)
					points = points + 50; //blocked or doubled pawn

				points = points + AttackSets.bPawnsPST[i];
			}else if((board.BR & pos) != 0){
				points = points - 500;

				points = points + AttackSets.bRookPST[i];
			}else if((board.BN & pos) != 0){
				points = points - 320;

				points = points + AttackSets.bKnightPST[i];
			}else if((board.BB & pos) != 0){
				points = points - 330;

				points = points + AttackSets.bBishopPST[i];
			}else if((board.BQ & pos) != 0){
				points = points - 900;

				points = points + AttackSets.bQueenPST[i];
			}else if((board.BK & pos) != 0){
				points = points + AttackSets.bKingPST[i];
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
