package engine;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Engine {
	private String color; //"BLACK" for black pieces, "WHITE" for white pieces
	private Board board;
	private TPT tpt;
	
	public Engine(String color, Board board, boolean initialized, TPT tpt){
		this.tpt = tpt;
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
			AttackSets.initZobrist();
		}
		time = System.currentTimeMillis() - time;
		if(!initialized)
			System.out.println("Initialization took " + time + "ms.");
	}

	public MoveArrayList findMoveList(Board board, String playerColor){
		MoveArrayList legalMoves = MoveArrayListManager.obtainMoveArrayList();

 		if(playerColor == "WHITE"){
			if(board.checkColor("WHITE") == 1){
				if(board.checkmate() == 1){
					return MoveArrayListManager.obtainMoveArrayList();
				}
			}
			MoveArrayList moves = this.generateMoves(board,"WHITE");

			for(int i = 0; i < moves.size(); i++){
				Move move = moves.get(i);
				Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
				simBoard.makeMove(move.from, move.to);
				if(simBoard.BK == 0L || simBoard.WK == 0L) //SKA DESSA VARA HÄR??
					continue;
				int simCheck = simBoard.checkColor("WHITE");
				if(simCheck == 2 || simCheck == 0)
					legalMoves.add(move.from, move.to, move.fromPiece, move.toPiece, move.checking, move.score);
			}
			MoveArrayListManager.renounceMoveArrayList(moves);

		}else if(playerColor == "BLACK") {
 			if(board.checkColor("BLACK") == 2){
 				if(board.checkmate() == 2){
					return MoveArrayListManager.obtainMoveArrayList();
				}
			}
			MoveArrayList moves = this.generateMoves(board,"BLACK");

 			for(int i = 0; i < moves.size(); i++){
 				Move move = moves.get(i);
 				Board simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
 				simBoard.makeMove(move.from, move.to);
 				if(simBoard.BK == 0L || simBoard.WK == 0L) //SKA DESSA VARA HÄR??
 					continue;
 				int simCheck = simBoard.checkColor("BLACK");
 				if(simCheck == 1 || simCheck == 0)
 					legalMoves.add(move.from, move.to, move.fromPiece, move.toPiece, move.checking, move.score);
 			}
 			MoveArrayListManager.renounceMoveArrayList(moves);
		}
		return legalMoves;
	}

	public MoveArrayList generateMoves(Board board, String playerColor){
		MoveArrayList moveList = MoveArrayListManager.obtainMoveArrayList();

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
			int a = 0;

			for(int i = 0; i<64; i++) {
				long pos = AttackSets.getPosition(i);
				if((pos & legalWPMoves) != 0) {
					moveList.add(i-8, i, 'P', '_', false, 0);
				}
				if((pos & legalWPMoves2) != 0) {
					moveList.add(i-16, i, 'P', '_', false, 0);
				}
				if((pos & WPAttacksL) != 0) {
					char takenPiece = board.getPiece(i);
					moveList.add(i-7, i, 'P', takenPiece, false, 0);
				}
				if((pos & WPAttacksR) != 0) {
					char takenPiece = board.getPiece(i);
					moveList.add(i-9, i, 'P', takenPiece, false, 0);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP) != 0 && board.enPassantPlayer == 2){
						moveList.add(board.enPassantPos+1, board.enPassantPos+8, 'P', 'p', false, 0);
					}
				}else if(board.enPassantPos % 8 == 7 && board.enPassantPlayer == 2) {
					if((AttackSets.getPosition(board.enPassantPos-1) & board.WP) != 0){
						moveList.add(board.enPassantPos-1, board.enPassantPos+8, 'P', 'p', false, 0);
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.WP ) != 0 && board.enPassantPlayer == 2){
						moveList.add(board.enPassantPos+1, board.enPassantPos+8, 'P', 'p', false, 0);
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.WP) != 0 && board.enPassantPlayer == 2){
						moveList.add(board.enPassantPos-1, board.enPassantPos+8, 'P', 'p', false, 0);
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

							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'K', takenPiece, false, 0);
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
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'B', takenPiece, false, 0);
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
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'R', takenPiece, false, 0);
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
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'Q', takenPiece, false, 0);
						}
					}
				}else if((board.WK & AttackSets.getPosition(i)) != 0){
					long WKMoves = AttackSets.kingMoves(i);

					//Remove pseudolegal moves
					long legalWKMoves2 = WKMoves & empty;
					long legalWKMoves3 = WKMoves & enemies;
					long legalWKMoves = legalWKMoves2 | legalWKMoves3;
					attackBoard = attackBoard | legalWKMoves;

					if(board.WK == AttackSets.WKStart){
						if(board.castleWKValid){
							if((occupied & AttackSets.WKRblockers) == 0){
								if(board.checkColor("WHITE") == 0) {
									Board simBoard = new Board(board);
									simBoard.WK = simBoard.WK ^ AttackSets.WKStart;
									simBoard.WK = simBoard.WK ^ AttackSets.wRightPasses;

									int simCheck = simBoard.checkColor("WHITE");
									if(simCheck == 0){
										/* Behövs detta?
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(5);
										*/
										if(simCheck == 0 && ((friends & AttackSets.wRightRookStart) != 0))
											legalWKMoves = legalWKMoves | AttackSets.castleWKR;
									}

								}
							}
						}
						if(board.castleWQValid){
							if((occupied & AttackSets.WKLblockers) == 0){
								if(board.checkColor("WHITE") == 0){
									Board simBoard = new Board(board);
									simBoard.WK = simBoard.WK ^ AttackSets.WKStart;
									simBoard.WK = simBoard.WK ^ AttackSets.wLeftPasses;

									int simCheck = simBoard.checkColor("WHITE");
									if(simCheck == 0){
										/* Behövs detta?
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, false, false, false, false);
										simBoard.WK = simBoard.WK ^ AttackSets.getPosition(3);
										*/
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

							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'K', takenPiece, false, 0);
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
					moveList.add(i+8, i, 'p', '_', false, 0);
				}
				if((legalBPMoves2 & pos) != 0) {
					moveList.add(i+16, i, 'p', '_', false, 0);
				}
				if((BPAttacksL & pos) != 0) {
					char takenPiece = board.getPiece(i);
					moveList.add(i+9, i, 'p', takenPiece, false, 0);
				}

				if((BPAttacksR & pos) != 0) {
					char takenPiece = board.getPiece(i);
					moveList.add(i+7, i, 'p', takenPiece, false, 0);
				}
			}

			//en passant
			long enPassantAttacks = 0L;
			if(board.enPassant){
				if(board.enPassantPos % 8 == 0 && board.enPassantPlayer == 1){
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0){
						moveList.add(board.enPassantPos+1, board.enPassantPos-8, 'p', 'P', false, 0);
					}
				}else if(board.enPassantPos % 8 == 7 && board.enPassantPlayer == 1) {
					if((AttackSets.getPosition(board.enPassantPos-1) & board.BP) != 0){
						moveList.add(board.enPassantPos-1, board.enPassantPos-8, 'p', 'P', false, 0);
					}
				}else{
					if((AttackSets.getPosition(board.enPassantPos+1) & board.BP) != 0 && board.enPassantPlayer == 1){
						moveList.add(board.enPassantPos+1, board.enPassantPos-8, 'p', 'P', false, 0);
					}
					if((AttackSets.getPosition(board.enPassantPos-1) & board.BP) != 0 && board.enPassantPlayer == 1){
						moveList.add(board.enPassantPos-1, board.enPassantPos-8, 'p', 'P', false, 0);
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

							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'n', takenPiece, false, 0);
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

							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'b', takenPiece, false, 0);
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
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'r', takenPiece, false, 0);
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
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'q', takenPiece, false, 0);
						}
					}
				}else if((board.BK & AttackSets.getPosition(i)) != 0){
					long BKMoves = AttackSets.kingMoves(i);

					//Remove pseudolegal moves
					long legalBKMoves2 = BKMoves & empty;
					long legalBKMoves3 = BKMoves & enemies;
					long legalBKMoves = legalBKMoves2 | legalBKMoves3;
					attackBoard = attackBoard | legalBKMoves;

					if(board.BK == AttackSets.BKStart){
						if(board.castleBKValid){
							if((occupied & AttackSets.BKRblockers) == 0){
								if(board.checkColor("BLACK") == 0) {
									Board simBoard = new Board(board);
									simBoard.BK = simBoard.BK ^ AttackSets.BKStart;
									simBoard.BK = simBoard.BK ^ AttackSets.bRightPasses;
									int simCheck = simBoard.checkColor("BLACK");
									if(simCheck == 0){
										/*Behövs detta?
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(61);
										*/
										if(simCheck == 0 && ((friends & AttackSets.bRightRookStart) != 0))
											legalBKMoves = legalBKMoves | AttackSets.castleBKR;
									}

								}
							}
						}
						if(board.castleBQValid){
							if((occupied & AttackSets.BKLblockers) == 0){
								if(board.check() == 0){
									Board simBoard = new Board(board);
									simBoard.BK = simBoard.BK ^ AttackSets.BKStart;
									simBoard.BK = simBoard.BK ^ AttackSets.bLeftPasses;
									int simCheck = simBoard.check();
									if(simCheck == 0){
										/* Behövs detta?
										simBoard = new Board(board.WP, board.WR,board.WN, board.WB, board.WK, board.WQ, board.BP, board.BR, board.BN, board.BB, board.BK, board.BQ, board.castleWKValid, board.castleWQValid, board.castleBKValid, board.castleWQValid);
										simBoard.BK = simBoard.BK ^ AttackSets.getPosition(3);
										*/
										if(simCheck == 0 && ((friends & AttackSets.bLeftRookStart) != 0)){
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
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'k', takenPiece, false, 0);
						}
					}
				}
			}
		}
		AttackSets.currentAttackBoard = attackBoard;
		return moveList;
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


	public double alphaBetaMax(Board board, int depthLeft, double alpha, double beta, ArrayList<Move> pv, int depth, long prevHash){
		if(depthLeft == 0){
			long hash = tpt.hash(board);
			if(tpt.containsKey(hash)){
				return tpt.get(hash).score;
			}

			double score = evalPosition(board);
			tpt.put(hash, tpt.new TPTEntry(hash, score, 0, 0, 1));
			pv.clear();
			return score;
		}
		depth++;
		MoveArrayList moves = this.findMoveList(board, "WHITE");
		sort(moves);
		ArrayList<Move> localPV = new ArrayList<Move>();
		
		if(moves.size()==0){
			if(board.checkColor("WHITE") == 1)
				return -Double.MAX_VALUE;
			else if(board.checkColor("BLACK") == 2)
				return Double.MAX_VALUE;
			else return 0;
		}

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			/*
			StringBuilder str = new StringBuilder();
			for(int k = 0; k < depth; k++){
				str.append("  ");
			}
			str.append("Considering move: ");
			str.append(Util.convertNumToCoord(move.from));
			str.append(Util.convertNumToCoord(move.to));
			System.out.println(str.toString());
*/

			long hash;
			if(prevHash == 0L)
				hash = tpt.hash(simBoard);
			else{
				hash = tpt.updateHash(board, prevHash, move.from, move.to);
			}
			double score = -Double.MAX_VALUE;
			boolean TPFound = false;
			if(tpt.containsKey(hash)){
				TPT.TPTEntry transposition = tpt.get(hash);
				if(transposition.depth >= depthLeft&& (transposition.turnToMove == 2)){
					score = tpt.get(hash).score;
					TPFound = true;
				}else{
					tpt.remove(hash);
				}
			}

			if(!TPFound){
				score = alphaBetaMin(simBoard, depthLeft - 1, alpha, beta, localPV, depth, hash);
				hash = tpt.hash(simBoard);
				tpt.put(hash, tpt.new TPTEntry(hash, score, depthLeft, 0, 1));
			}
			if(score == Double.MAX_VALUE){ //checkmate is best possible, no other moves need be considered
				pv.clear();
				pv.addAll(localPV);
				pv.add(move);
				return score-depth; //prefer earlier checkmate
			}

			if(score > alpha){
				pv.clear();
				pv.addAll(localPV);
				pv.add(move);

				alpha = score;
			}

			if(score >= beta){
				return beta;
			}

		}
		return alpha;
	}

	double alphaBetaMin(Board board, int depthLeft, double alpha, double beta, ArrayList<Move> pv, int depth, long prevHash){
		if(depthLeft == 0){
			long hash = tpt.hash(board);

			if(tpt.containsKey(hash)){
				return tpt.get(hash).score;
			}
			double score = evalPosition(board);
			tpt.put(hash, tpt.new TPTEntry(hash, score, 0, 0, 2));
			pv.clear();
			return score;
		}
		depth++;
		MoveArrayList moves = this.findMoveList(board, "BLACK");
		sort(moves);
		ArrayList<Move> localPV = new ArrayList<Move>();

		if(moves.size()==0){
			if(board.checkColor("WHITE") == 1)
				return -Double.MAX_VALUE;
			else if(board.checkColor("BLACK") == 2)
				return Double.MAX_VALUE;
			else return 0;
		}

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);
			/*
			StringBuilder str = new StringBuilder();
			for(int k = 0; k < depth; k++){
				str.append("  ");
			}
			str.append("Considering move: ");
			str.append(Util.convertNumToCoord(move.from));
			str.append(Util.convertNumToCoord(move.to));
			System.out.println(str.toString());
			*/
			long hash;

			if(prevHash == 0L){
				hash = tpt.hash(simBoard);
			}else{
				hash = tpt.updateHash(board, prevHash, move.from, move.to);
			}

			double score = Double.MAX_VALUE;
			boolean TPFound = false;
			if(tpt.containsKey(hash)){
				TPT.TPTEntry transposition = tpt.get(hash);
				if(transposition.depth >= depthLeft && (transposition.turnToMove == 2)){
					score = tpt.get(hash).score;
					TPFound = true;
				}else{
					tpt.remove(hash);
				}

			}

			if(!TPFound){
				score = alphaBetaMax(simBoard, depthLeft - 1, alpha, beta, localPV, depth, hash);
				hash = tpt.hash(simBoard);
				tpt.put(hash, tpt.new TPTEntry(hash, score, depthLeft, 0, 2));
			}
			if(score == -Double.MAX_VALUE){ //checkmate is best possible, no other moves need be considered
				pv.clear();
				pv.addAll(localPV);
				pv.add(move);
				return score+depth; //prefer earlier checkmate
			}

			//ändrat ordning på ifsatser
			if(score < beta){
				pv.clear();
				pv.addAll(localPV);
				pv.add(move);

				beta = score;
			}

			if(score <= alpha){
				return alpha;
			}

		}
		return beta;
	}



	public void sort(MoveArrayList moves){

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			int tempScore = 0;
			if(move.fromPiece == 'P' || move.fromPiece == 'p'){
				tempScore += -1;
			}else if(move.fromPiece == 'N' || move.fromPiece == 'n'){
				tempScore += -3;
			}else if(move.fromPiece == 'B' || move.fromPiece == 'b'){
				tempScore += -3;
			}else if(move.fromPiece == 'R' || move.fromPiece == 'r'){
				tempScore += -5;
			}else if(move.fromPiece == 'Q' || move.fromPiece == 'q'){
				tempScore += -9;
			}else if(move.fromPiece == 'K' || move.fromPiece == 'k'){
				tempScore += -10;
			}

			if(move.toPiece == 'P' || move.toPiece == 'p'){
				tempScore += 10;
			}else if(move.toPiece == 'N' || move.toPiece == 'n'){
				tempScore += 30;
			}else if(move.toPiece == 'B' || move.toPiece == 'b'){
				tempScore += 30;
			}else if(move.toPiece == 'R' || move.toPiece == 'r'){
				tempScore += 50;
			}else if(move.toPiece == 'Q' || move.toPiece == 'q'){
				tempScore += 90;
			}
			if(move.to == board.lastMovedPos){
				move.score = 100;
			}

		}

		moves.sort(new Comparator<Move>() {
			@Override
			public int compare(Move o1, Move o2) {
				if(o1.score > o2.score){
					return -1;
				}else if(o1.score < o2.score){
					return 1;
				}else {
					return 0;
				}

			}
		});
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

				points = points - AttackSets.wPawnsPST[i];
			}else if((board.BR & pos) != 0){
					points = points - 500;

				points = points - AttackSets.wRookPST[i];
			}else if((board.BN & pos) != 0){
				points = points - 320;

				points = points - AttackSets.wKnightPST[i];
			}else if((board.BB & pos) != 0){
				points = points - 330;

				points = points - AttackSets.wBishopPST[i];
			}else if((board.BQ & pos) != 0){
				points = points - 900;

				points = points - AttackSets.wQueenPST[i];
			}else if((board.BK & pos) != 0){
				points = points - AttackSets.wKingPST[i];
			}
		}

		if(board.wHasCastled){
			points = points + 125;
		}else if(!board.wHasCastled){
			if(board.castleWKValid){
				points = points + 75;
			}
			if(board.castleBQValid){
				points = points + 25;
			}
		}
		if(board.bHasCastled){
			points = points - 125;
		}else if(!board.bHasCastled){
			if(board.castleBKValid){
				points = points - 75;
			}
			if(board.castleBQValid){
				points = points - 25;
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
