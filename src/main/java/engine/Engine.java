package engine;

import java.util.Comparator;

public class Engine {
	private String color; //"BLACK" for black pieces, "WHITE" for white pieces
	private Board board;
	private TPT tpt;
	
	public Engine(String color, Board board, TPT tpt){
		this.tpt = tpt;
		this.board = board;
		this.color = color;
	}

	public MoveArrayList findMoveList(Board board, String playerColor){
		MoveArrayList legalMoves = MoveArrayListManager.obtainMoveArrayList();

 		if(playerColor.equals("WHITE")){
			if(board.checkColor("WHITE") == 1){
				if(board.checkmate() == 1){
					return legalMoves;
				}
			}
			MoveArrayList moves = this.generateMoves(board,"WHITE");
			//only use moves that don't leave the player's king in check
			for(int i = 0; i < moves.size(); i++){
				Move move = moves.get(i);
				Board simBoard = new Board(board);
				simBoard.makeMove(move.from, move.to);
				if(simBoard.BK == 0L || simBoard.WK == 0L) //SKA DESSA VARA HÄR??
					continue;
				int simCheck = simBoard.checkColor("WHITE");
				if(simCheck == 2 || simCheck == 0)
					legalMoves.add(move.from, move.to, move.fromPiece, move.toPiece, move.checking, move.score);
			}
			MoveArrayListManager.renounceMoveArrayList(moves);

		}else if(playerColor.equals("BLACK")) {
 			if(board.checkColor("BLACK") == 2){
 				if(board.checkmate() == 2){
					return legalMoves;
				}
			}
			MoveArrayList moves = this.generateMoves(board,"BLACK");
 			//only use moves that don't leave the player's king in check
 			for(int i = 0; i < moves.size(); i++){
 				Move move = moves.get(i);
 				Board simBoard = new Board(board);
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

		long occupied = board.occupied();
		long empty = board.empty();
		long friends = board.friends(playerColor);
		long enemies = board.enemies(playerColor);

		long attackBoard = 0L;
		if(playerColor.equals("WHITE")){
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
			WPAttacksL = WPAttacksL & enemies;

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
				long position = AttackSets.getPosition(i);
				if((board.WN & position) != 0){
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
							moveList.add(i, j, 'N', takenPiece, false, 0);
						}
					}
				}else if((board.WB & position) != 0){
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
					attackBoard = attackBoard | bishopAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((bishopAttacks & AttackSets.getPosition(j)) != 0){
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'B', takenPiece, false, 0);
						}
					}
				}else if((board.WR & position) != 0){
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
				}else if((board.WQ & position) != 0){
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
				}else if((board.WK & position) != 0){
					long WKMoves = AttackSets.kingMoves(i);

					//Remove pseudo-legal moves
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
										if((friends & AttackSets.wRightRookStart) != 0)
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
										if((friends & AttackSets.wLeftRookStart) != 0){
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
							movesFound++;
							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'K', takenPiece, false, 0);
						}
					}
				}
			}
		}else if(playerColor.equals("BLACK")){
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
				long position = AttackSets.getPosition(i);
				if((board.BN & position) != 0){
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
				}else if((board.BB & position) != 0){
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
					attackBoard = attackBoard | bishopAttacks;

					//generate moveList
					for(int j = 0; j < 64; j++){
						if((bishopAttacks & AttackSets.getPosition(j)) != 0){

							char takenPiece = board.getPiece(j);
							moveList.add(i, j, 'b', takenPiece, false, 0);
						}
					}
				}else if((board.BR & position) != 0){
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
				}else if((board.BQ & position) != 0){
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
				}else if((board.BK & position) != 0){
					long BKMoves = AttackSets.kingMoves(i);

					//Remove pseudo-legal moves
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
										if((friends & AttackSets.bRightRookStart) != 0)
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
										if((friends & AttackSets.bLeftRookStart) != 0){
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
							movesFound++;
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
	public boolean isAttackedByOpponent(Board board, String playerColor, long square){
		long occupied = board.occupied();
		long empty = board.empty();
		long enemies = board.friends(playerColor);

		if(playerColor.equals("BLACK")){

			//Attacks up and to the left
			long RightColumn0 = ~(72340172838076673L);

			long WPAttacksR = board.WP & RightColumn0;

			WPAttacksR = WPAttacksR >>> 9;

			WPAttacksR = WPAttacksR & enemies; //only possible if enemy present

			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;

			long WPAttacksL = board.WP & leftColumn0;
			WPAttacksL = WPAttacksL >>> 7;

			WPAttacksL = WPAttacksL & enemies;
			if((WPAttacksR & square) != 0)
				return true;
			if((WPAttacksL & square) != 0)
				return true;

			for(int i = 0; i < 64; i++){
				long position = AttackSets.getPosition(i);
				if((board.WN & position) != 0){
					long WNAttacks = AttackSets.knightMoves(i);
					long legalWNMoves2 = WNAttacks & empty;
					long legalWNMoves3 = WNAttacks & enemies;

					long legalWNMoves = legalWNMoves2 | legalWNMoves3;
					if((legalWNMoves & square) != 0)
						return true;

				}else if((board.WB & position) != 0){
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

					if((bishopAttacks & square) != 0)
						return true;
				}else if((board.WR & position) != 0){
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
					if((rookAttacks & square) != 0)
						return true;

				}else if((board.WQ & position) != 0){
					long singleQueen = AttackSets.getPosition(i);


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

					if((queenAttacks & square) != 0)
						return true;

				}else if((board.WK & position) != 0){
					long WKMoves = AttackSets.kingMoves(i);

					//Remove pseudolegal moves
					long legalWKMoves2 = WKMoves & empty;
					long legalWKMoves3 = WKMoves & enemies;
					long legalWKMoves = legalWKMoves2 | legalWKMoves3;
					if((legalWKMoves & square) != 0)
						return true;
				}
			}
		}else if(playerColor.equals("WHITE")){
			//Attacks up and to the left
			long RightColumn0 = ~(72340172838076673L);

			long BPAttacksR = board.BP & RightColumn0;
			BPAttacksR = BPAttacksR << 7;

			BPAttacksR = BPAttacksR & enemies; //only possible if enemy present

			//Attacks up and to the right
			long leftColumn0 = 9187201950435737471L;

			long BPAttacksL = board.BP & leftColumn0;
			BPAttacksL = BPAttacksL << 9;

			BPAttacksL = BPAttacksL & enemies; //only possible if enemy present

			if((BPAttacksL & square) != 0)
				return true;
			if((BPAttacksR & square) != 0)
				return true;

			for(int i = 0; i < 64; i++){
				long position = AttackSets.getPosition(i);
				if((board.BN & position) != 0){
					long BNAttacks = AttackSets.knightMoves(i);
					long legalBNMoves2 = BNAttacks & empty;
					long legalBNMoves3 = BNAttacks & enemies;

					long legalBNMoves = legalBNMoves2 | legalBNMoves3;
					if((legalBNMoves & square) != 0)
						return true;

				}else if((board.BB & position) != 0){
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
					if((bishopAttacks & square) != 0)
						return true;

				}else if((board.BR & position) != 0){
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
					if((rookAttacks & square) != 0)
						return true;

				}else if((board.BQ & position) != 0){
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
					if((queenAttacks & square) != 0)
					return true;

				}else if((board.BK & position) != 0){
					long BKMoves = AttackSets.kingMoves(i);

					//Remove pseudolegal moves
					long legalBKMoves2 = BKMoves & empty;
					long legalBKMoves3 = BKMoves & enemies;
					long legalBKMoves = legalBKMoves2 | legalBKMoves3;
					if((legalBKMoves & square) != 0)
						return true;
				}
			}
		}
		return false;
	}

	public double alphaBetaMax(Board board, int depthLeft, double alpha, double beta, PrincipalVariation pv, int depth, long prevHash, boolean debug){
		if(depthLeft == 0){
			depth++;
			//long hash = tpt.hash(board);
			/*
			if(tpt.containsKey(hash)){
				Debug.TPFound(depth);
				return tpt.get(hash).score;
			}
			 */
			//TPT.TPTEntry entry = tpt.get(hash);
			double score = evalPosition(board);
			/*
			if(entry != null && entry.depth == 0 && entry.score != score)
				System.out.println("ENTRY INCORRECT");

			 */
			//tpt.put(hash, score, 0);
			pv.clear();
			return score;
		}
		depth++;
		MoveArrayList moves = this.findMoveList(board, "WHITE");

		PrincipalVariation localPV = new PrincipalVariation();

		if(prevHash == 0L)
			prevHash = tpt.hash(board);
		TPT.TPTEntry entry = tpt.get(prevHash);

		if(entry != null)
			sort(moves, entry.bestMove);
		else{
			sort(moves);
			entry = tpt.new TPTEntry(prevHash, Double.NEGATIVE_INFINITY, depthLeft);
		}


		if(moves.size()==0){
			if(board.checkColor("WHITE") == 1)
				return -20000+depth;
			else if(board.checkColor("BLACK") == 2)
				return 20000;
			else return 0;
		}

		double score = -Double.MAX_VALUE;
		Move bestMove = null;

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			if(debug){
				if(LineDebugger.getMove(depth).equals(move))
					LineDebugger.match(depth);
				else
					LineDebugger.unmatch(depth);
			}
/*
			StringBuilder str = new StringBuilder();
			if(depth == 0)
				str.append("ROOT");
			for(int k = 0; k < depth; k++){
				str.append(k);
				str.append("  ");
			}
			str.append("Considering move: ");
			str.append(Util.convertNumToCoord(move.from));
			str.append(Util.convertNumToCoord(move.to));
			System.out.println(str.toString());
*/

/*
			long hash;
			if(prevHash == 0L)
				hash = tpt.hash(simBoard);
			else{
				hash = tpt.updateHash(board, prevHash, move.from, move.to);
			}
			 */
			long hash = tpt.hash(simBoard);

			boolean TPFound = false;
			TPT.TPTEntry transposition = null;
			if(tpt.containsKey(hash)){
				 transposition = tpt.get(hash);
				if(transposition.depth >= depthLeft-1){
					Debug.TPFound(depth);
					TPFound = true;
				}
			}

			if(!TPFound || TPFound){
				score = alphaBetaMin(simBoard, depthLeft - 1, alpha, beta, localPV, depth, hash, debug);
				//hash = tpt.hash(simBoard); //unnecessary, already have the hash
				if(TPFound) {
					if(score != transposition.score && (transposition.depth == depthLeft-1) && transposition != null){
						System.out.println("INCORRECT Entry score: " + transposition.score + " Search score: " + score);
					}
				}

				if(depthLeft == 1)
					tpt.put(hash, score, depthLeft-1, move, simBoard, 0);

			}
			if(score == Double.MAX_VALUE){ //checkmate is best possible, no other moves need be considered
				pv.clear();
				pv.addAllMoves(localPV);
				pv.addMove(move);
				System.out.println("MAXVALUE " + "DepthLeft: " + depthLeft);
				return score-depth; //prefer earlier checkmate
			}

			if(score >= beta){
				if(debug){
					if(LineDebugger.onLine(depth))
						System.out.println("BETA CUTOFF AT DEPTH: " + depth);
				}
				//tpt.put(prevHash, score, depthLeft, new Move(move));
				return beta;
			}
			if(score > alpha){
				if(entry != null){
					bestMove = new Move(move);
				}
				pv.clear();
				pv.addAllMoves(localPV);
				pv.addMove(move);

				alpha = score;
			}
		}
		//tpt.put(prevHash, score, depthLeft, bestMove);
		return alpha;
	}

	public double alphaBetaMin(Board board, int depthLeft, double alpha, double beta, PrincipalVariation pv, int depth, long prevHash, boolean debug){
		if(depthLeft == 0){
			depth++;
			//long hash = tpt.hash(board);
			pv.clear();
			/*
			if(tpt.containsKey(hash)){
				Debug.TPFound(depth);
				return tpt.get(hash).score;
			}
			 */
			double score = evalPosition(board);

			/*
			TPT.TPTEntry entry = tpt.get(hash);
			if(entry != null && entry.depth == 0 && entry.score != score)
				System.out.println("ENTRY INCORRECT");
*/
			//tpt.put(hash, score, 0);
			pv.clear();
			return score;
		}
		depth++;
		MoveArrayList moves = this.findMoveList(board, "BLACK");
		PrincipalVariation localPV = new PrincipalVariation();


		if(prevHash == 0L)
			prevHash = tpt.hash(board);
		TPT.TPTEntry entry = tpt.get(prevHash);

		if(entry != null)
			sort(moves, entry.bestMove);
		else{
			sort(moves);
			entry = tpt.new TPTEntry(prevHash, Double.NEGATIVE_INFINITY, depthLeft);
		}

		if(moves.size()==0){
			if(board.checkColor("WHITE") == 1)
				return -20000;
			else if(board.checkColor("BLACK") == 2)
				return 20000+depth;
			else return 0;
		}

		double score = Double.MAX_VALUE;
		Move bestMove = null;
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			if(debug){
				if(LineDebugger.getMove(depth).equals(move))
					LineDebugger.match(depth);
				else
					LineDebugger.unmatch(depth);
			}
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

/*
			long hash;
			if(prevHash == 0L){
				hash = tpt.hash(simBoard);
			}else{
				hash = tpt.updateHash(board, prevHash, move.from, move.to);
			}
*/
			long hash = tpt.hash(simBoard);
			boolean TPFound = false;
			TPT.TPTEntry transposition = null;
			if(tpt.containsKey(hash)){
				transposition = tpt.get(hash);
				if(transposition.depth >= depthLeft-1){
					Debug.TPFound(depth);
					TPFound = true;
				}
			}

			if(!TPFound || TPFound){
				score = alphaBetaMax(simBoard, depthLeft - 1, alpha, beta, localPV, depth, hash, debug);
				//hash = tpt.hash(simBoard); //unnecessary, already have the hash
				if(TPFound) {
					if(score != transposition.score && (transposition.depth == depthLeft-1)  && transposition != null) {
						System.out.println("INCORRECT Entry score: " + transposition.score + " Search score: " + score);
					}
				}
				if(depthLeft == 1)
					tpt.put(hash, score, depthLeft-1, move, simBoard, 0);


			}
			if(score == -Double.MAX_VALUE){ //checkmate is best possible, no other moves need be considered
				pv.clear();
				pv.addAllMoves(localPV);
				pv.addMove(move);
				return score+depth; //prefer earlier checkmate
			}

			if(score <= alpha){
				//tpt.put(prevHash, score, depthLeft, new Move(move));
				if(debug){
					if(LineDebugger.onLine(depth))
						System.out.println("ALPHA CUTOFF AT DEPTH: " + depth);
				}
				return alpha;
			}

			//ändrat ordning på ifsatser
			if(score < beta){
				if(entry != null){
					bestMove = new Move(move);
				}
				pv.clear();
				pv.addAllMoves(localPV);
				pv.addMove(move);
				beta = score;
			}

		}
		//tpt.put(prevHash, score, depthLeft, bestMove);
		return beta;
	}



	public void sort(MoveArrayList moves, Move bestMove){

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
			move.score = tempScore;

			if(move.to == board.lastMovedPos){
				move.score += 100;
			}

			if(bestMove != null && move.equals(bestMove)){
				move.score = 10000;
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
	public void sort(MoveArrayList moves){

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			int tempScore = 0;
			if(move.toPiece == '_')
				continue;

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
			move.score = tempScore;

			if(move.to == board.lastMovedPos){
				move.score += 100;
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
			return -20000;
		}else if(checkmate == 2){
			return 20000;
		}

		int nbrWhiteBishops = 0;
		int nbrBlackBishops = 0;
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
				nbrWhiteBishops++;

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
				nbrBlackBishops++;

				points = points - AttackSets.wBishopPST[i];
			}else if((board.BQ & pos) != 0){
				points = points - 900;

				points = points - AttackSets.wQueenPST[i];
			}else if((board.BK & pos) != 0){
				points = points - AttackSets.wKingPST[i];
			}
		}
		if(nbrWhiteBishops == 2)
			points = points + 30;
		else if(nbrBlackBishops == 2)
			points = points - 30;

		if(board.wHasCastled){
			points = points + 125;
		}else if(!board.wHasCastled){
			if(board.castleWKValid){
				points = points + 75;
			}
			if(board.castleWQValid){
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
}
