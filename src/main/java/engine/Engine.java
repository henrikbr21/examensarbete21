package engine;

import javax.management.MXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.List;
import java.util.Random;

public class Engine {
	private final TPT tpt;
	private final Random rand;
	
	public Engine(TPT tpt){
		this.tpt = tpt;
		rand = new Random();
	}

	public class SearchThread extends Thread{
		private final Board board;
		private final String playerColor;
		private final int depthLeft;
		private final PrincipalVariation pv;
		private final boolean debug;
		private final boolean helper;
		private final PrincipalVariation lastResult = new PrincipalVariation();
		private double lastScore;
		private final Engine engine;
		private int lastDepth = 0;
		private MeasurementData data;
		private int searchNbr;

		public SearchThread(Board board, String playerColor, int depthLeft, boolean debug, boolean helper, Engine engine, MeasurementData data, int searchNbr){
			this.board = board;
			this.playerColor = playerColor;
			this.depthLeft = depthLeft;
			this.debug = debug;
			this.helper = helper;
			pv = new PrincipalVariation();
			this.engine = engine;
			this.data = data;
			this.searchNbr = searchNbr;
		}

		public void run(){
			try{
				ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
				//List<GarbageCollectorMXBean> gcMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
				GCStats stat = new GCStats(-1, -1);
				Runtime rt = Runtime.getRuntime();
				if(playerColor.equals("WHITE")){
					for(int i = 0; i <= depthLeft; i += 2){
						boolean lastIteration = false;
						if(i == depthLeft){
							lastIteration = true;
						}
						long time = mxBean.getCurrentThreadCpuTime();
						double result = alphaBetaMax(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), debug, helper, data, searchNbr, lastIteration);
						data.setExecutionTime(searchNbr, i, (mxBean.getCurrentThreadCpuTime() - time));
						long id = this.getId();
						if(!helper){
							if(i == depthLeft){
								stat = Util.dumpGCLogs();
								data.setGcCollectionTime(searchNbr, stat.time);
								data.setGcCollectionCount(searchNbr, stat.count);
								data.setMemoryUsage(searchNbr, (i/2)-1, rt.maxMemory()-rt.freeMemory());
							}
							lastResult.clear();
							lastResult.addAllMoves(pv);
							lastScore = result;
							lastDepth = i;
							if(i == depthLeft){
								synchronized (engine){
									engine.notifyAll();
								}
							}
						}
					}
				}else{
					for(int i = 0; i <= depthLeft; i += 2){
						boolean lastIteration = false;
						if(i == depthLeft){
							lastIteration = true;
						}
						long time = mxBean.getCurrentThreadCpuTime();
						double result = alphaBetaMin(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), debug, helper, data, searchNbr, lastIteration);
						data.setExecutionTime(searchNbr, i, (mxBean.getCurrentThreadCpuTime() - time));
						if(!helper){
							if(i == depthLeft){
								stat = Util.dumpGCLogs();
								data.setGcCollectionTime(searchNbr, stat.time);
								data.setGcCollectionCount(searchNbr, stat.count);
							}
							lastResult.clear();
							lastResult.addAllMoves(pv);
							lastScore = result;
							lastDepth = i;
							if(i == depthLeft){
								synchronized (engine){
									engine.notifyAll();
								}
							}
						}
					}
				}
			}catch(InterruptedException ignored){
			}
		}
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
				if(simBoard.BK == 0L || simBoard.WK == 0L)
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
 				if(simBoard.BK == 0L || simBoard.WK == 0L)
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
		long N;
		long B;
		long R;
		long Q;
		long K;

		if(playerColor.equals("WHITE")){
			N = board.WN;
			B = board.WB;
			R = board.WR;
			Q = board.WQ;
			K = board.WK;
		}else {
			N = board.BN;
			B = board.BB;
			R = board.BR;
			Q = board.BQ;
			K = board.BK;
		}
		generatePawnMoves(board, playerColor, moveList, empty, enemies);
		generateCastlingMoves(board, playerColor, moveList, occupied, friends);

		boolean white = playerColor.equals("WHITE");
		for(int i = 0; i < 64; i++){
			long pos = AttackSets.getPosition(i);

			if((N & pos) != 0){
				long knightMoves = generateKnightBitboard(i, empty, enemies);
				extractMoves(board, knightMoves, i, moveList, white?'N':'n');
			}else if((B & pos) != 0){
				long bishopMoves = generateBishopBitboard(i, occupied, empty, enemies);
				extractMoves(board, bishopMoves, i, moveList, white?'B':'b');
			}else if((R & pos) != 0){
				long rookMoves = generateRookBitboard(i, occupied, empty, enemies);
				extractMoves(board, rookMoves, i, moveList, white?'R':'r');
			}else if((Q & pos) != 0){
				long queenMoves = generateQueenBitboard(i, occupied, empty, enemies);
				extractMoves(board, queenMoves, i, moveList, white?'Q':'q');
			}else if((K & pos) != 0){
				long kingMoves = generateKingBitboard(i, empty, enemies);
				extractMoves(board, kingMoves, i, moveList, white?'K':'k');
			}
		}
		return moveList;
	}

	public void extractMoves(Board board, long moves, int pos, MoveArrayList moveList, char fromPiece){
		char takenPiece;
		for(int i = 0; i < 64; i++){
			if((AttackSets.getPosition(i) & moves) != 0){
				fromPiece = board.getPiece(pos);
				takenPiece = board.getPiece(i);
				moveList.add(pos, i, fromPiece, takenPiece, false, 0);
			}
		}
	}

	public void generatePawnMoves(Board board, String playerColor, MoveArrayList moveList, long empty, long enemies){
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
		}else{
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
		}
	}
	public long generatePawnAttackBoard(Board board, String playerColor, long enemies){
		if(playerColor.equals("WHITE")){
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

			return WPAttacksR | WPAttacksL;
		}else{
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

			return BPAttacksR | BPAttacksL;
		}
	}

	public long generateKnightBitboard(int pos, long empty, long enemies){
		long NAttacks = AttackSets.knightMoves(pos);
		long legalNMoves2 = NAttacks & empty;
		long legalNMoves3 = NAttacks & enemies;
		long legalNMoves = legalNMoves2 | legalNMoves3;

		return legalNMoves;
	}
	public long generateBishopBitboard(int pos, long occupied, long empty, long enemies){
		long URAttacks = occupied & AttackSets.diagRaysUR(pos);
		int closestPos = Long.numberOfLeadingZeros(URAttacks);
		URAttacks = AttackSets.diagRaysUR(pos);
		if(closestPos != 64)
			URAttacks &= ~(AttackSets.diagRaysUR(closestPos));


		long DRAttacks = occupied & AttackSets.diagRaysDR(pos);
		closestPos = Long.numberOfTrailingZeros(DRAttacks);
		closestPos = 63 - closestPos;
		DRAttacks = AttackSets.diagRaysDR(pos);
		if(closestPos > 0)
			DRAttacks &= ~(AttackSets.diagRaysDR(closestPos));

		long ULAttacks = occupied & AttackSets.diagRaysUL(pos);
		closestPos = Long.numberOfLeadingZeros(ULAttacks);
		ULAttacks = AttackSets.diagRaysUL(pos);
		if(closestPos != 64)
			ULAttacks &= ~(AttackSets.diagRaysUL(closestPos));

		long DLAttacks = occupied & AttackSets.diagRaysDL(pos);
		closestPos = Long.numberOfTrailingZeros(DLAttacks);
		closestPos = 63 - closestPos;
		DLAttacks = AttackSets.diagRaysDL(pos);
		if(closestPos > 0)
			DLAttacks &= ~(AttackSets.diagRaysDL(closestPos));


		long bishopAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
		bishopAttacks = bishopAttacks & (enemies ^ empty);
		return bishopAttacks;
	}
	public long generateRookBitboard(int pos, long occupied, long empty, long enemies){
		//up attacks
		long upAttacks = occupied & AttackSets.rookAttacksU(pos);
		int closestPos = Long.numberOfLeadingZeros(upAttacks);
		upAttacks = AttackSets.rookAttacksU(pos);
		if(closestPos != 64)
			upAttacks &= ~(AttackSets.rookAttacksU(closestPos));


		//down attacks
		long downAttacks = occupied & AttackSets.rookAttacksD(pos);
		closestPos = Long.numberOfTrailingZeros(downAttacks);
		closestPos = 63-closestPos;
		downAttacks = AttackSets.rookAttacksD(pos);
		if(closestPos > 0)
			downAttacks &= ~(AttackSets.rookAttacksD(closestPos));


		//left attacks
		long leftAttacks = occupied & AttackSets.rookAttacksL(pos);
		closestPos = Long.numberOfTrailingZeros(leftAttacks);
		closestPos = 63-closestPos;
		leftAttacks = AttackSets.rookAttacksL(pos);
		if(closestPos > 0)
			leftAttacks &= ~(AttackSets.rookAttacksL(closestPos));


		//right attacks
		long rightAttacks = occupied & AttackSets.rookAttacksR(pos);
		closestPos = Long.numberOfLeadingZeros(rightAttacks);
		rightAttacks = AttackSets.rookAttacksR(pos);
		if(closestPos != 64)
			rightAttacks &= ~(AttackSets.rookAttacksR(closestPos));

		long rookAttacks = upAttacks | downAttacks | leftAttacks | rightAttacks;
		rookAttacks = rookAttacks & (enemies ^ empty);

		return rookAttacks;
	}
	public long generateQueenBitboard(int pos, long occupied, long empty, long enemies){
		long singleQueen = AttackSets.getPosition(pos);

		long horizontalAttacks = (occupied - 2 * singleQueen) ^ Long.reverse(Long.reverse(occupied) - 2 * Long.reverse(singleQueen));
		horizontalAttacks = horizontalAttacks & AttackSets.rowMask(pos/8);

		long verticalAttacks = ((occupied&AttackSets.colMask(pos%8)) - (2 * singleQueen)) ^ Long.reverse(Long.reverse(occupied&AttackSets.colMask(pos%8)) - (2 * Long.reverse(singleQueen)));
		verticalAttacks = verticalAttacks & AttackSets.colMask(pos%8);
		long queenAttacks1 = verticalAttacks ^ horizontalAttacks;
		queenAttacks1 = queenAttacks1 & (enemies ^ empty);

		long URAttacks = occupied & AttackSets.diagRaysUR(pos);
		int closestPos = Long.numberOfLeadingZeros(URAttacks);
		URAttacks = AttackSets.diagRaysUR(pos);
		if(closestPos != 64)
			URAttacks &= ~(AttackSets.diagRaysUR(closestPos));


		long DRAttacks = occupied & AttackSets.diagRaysDR(pos);
		closestPos = Long.numberOfTrailingZeros(DRAttacks);
		closestPos = 63 - closestPos;
		DRAttacks = AttackSets.diagRaysDR(pos);
		if(closestPos > 0)
			DRAttacks &= ~(AttackSets.diagRaysDR(closestPos));

		long ULAttacks = occupied & AttackSets.diagRaysUL(pos);
		closestPos = Long.numberOfLeadingZeros(ULAttacks);
		ULAttacks = AttackSets.diagRaysUL(pos);
		if(closestPos != 64)
			ULAttacks &= ~(AttackSets.diagRaysUL(closestPos));


		long DLAttacks = occupied & AttackSets.diagRaysDL(pos);
		closestPos = Long.numberOfTrailingZeros(DLAttacks);
		closestPos = 63 - closestPos;
		DLAttacks = AttackSets.diagRaysDL(pos);
		if(closestPos > 0)
			DLAttacks &= ~(AttackSets.diagRaysDL(closestPos));

		long diagAttacks = URAttacks | DRAttacks | ULAttacks | DLAttacks;
		diagAttacks = diagAttacks & (enemies ^ empty);
		long queenAttacks = queenAttacks1 ^ diagAttacks;

		return queenAttacks;
	}
	public long generateKingBitboard(int pos, long empty, long enemies){
		long KMoves = AttackSets.kingMoves(pos);
		//Remove pseudo-legal moves
		long legalKMoves2 = KMoves & empty;
		long legalKMoves3 = KMoves & enemies;
		long legalKMoves = legalKMoves2 | legalKMoves3;

		return legalKMoves;
	}

	public void generateCastlingMoves(Board board, String playerColor, MoveArrayList moveList, long occupied, long friends){
		if(board.WK == AttackSets.WKStart && playerColor.equals("WHITE")){
			if(board.castleWKValid){
				if((occupied & AttackSets.WKRBlockers) == 0){
					if(board.checkColor("WHITE") == 0) {
						Board simBoard = new Board(board);
						simBoard.WK = simBoard.WK ^ AttackSets.WKStart;
						simBoard.WK = simBoard.WK ^ AttackSets.wRightPasses;

						if(simBoard.checkColor("WHITE") == 0){
							if((friends & AttackSets.wRightRookStart) != 0)
								moveList.add(4, 6, 'K', '_', false, 0);
						}
					}
				}
			}
			if(board.castleWQValid){
				if((occupied & AttackSets.WKLBlockers) == 0){
					if(board.checkColor("WHITE") == 0){
						Board simBoard = new Board(board);
						simBoard.WK = simBoard.WK ^ AttackSets.WKStart;
						simBoard.WK = simBoard.WK ^ AttackSets.wLeftPasses;

						if(simBoard.checkColor("WHITE") == 0){
							if((friends & AttackSets.wLeftRookStart) != 0){
								moveList.add(4, 2, 'K', '_', false, 0);
							}
						}
					}
				}
			}
		}else if(board.BK == AttackSets.BKStart && playerColor.equals("BLACK")){
			if(board.castleBKValid){
				if((occupied & AttackSets.BKRBlockers) == 0){
					if(board.checkColor("BLACK") == 0) {
						Board simBoard = new Board(board);
						simBoard.BK = simBoard.BK ^ AttackSets.BKStart;
						simBoard.BK = simBoard.BK ^ AttackSets.bRightPasses;

						if(simBoard.checkColor("BLACK") == 0){
							if((friends & AttackSets.bRightRookStart) != 0)
								moveList.add(60, 62, 'k', '_', false, 0);
						}
					}
				}
			}
			if(board.castleBQValid){
				if((occupied & AttackSets.BKLBlockers) == 0){
					if(board.check() == 0){
						Board simBoard = new Board(board);
						simBoard.BK = simBoard.BK ^ AttackSets.BKStart;
						simBoard.BK = simBoard.BK ^ AttackSets.bLeftPasses;

						if(simBoard.checkColor("BLACK") == 0){
							if((friends & AttackSets.bLeftRookStart) != 0){
								moveList.add(60, 58, 'k', '_', false, 0);
							}
						}
					}
				}
			}
		}
	}
	public boolean isAttackedByOpponent(Board board, String playerColor, long square){
		long occupied = board.occupied();
		long empty = board.empty();
		long enemies = board.enemies(playerColor);
		long N;
		long B;
		long R;
		long Q;
		long K;

		if(playerColor.equals("WHITE")){
			N = board.WN;
			B = board.WB;
			R = board.WR;
			Q = board.WQ;
			K = board.WK;
		}else {
			N = board.BN;
			B = board.BB;
			R = board.BR;
			Q = board.BQ;
			K = board.BK;
		}

		if((generatePawnAttackBoard(board, playerColor, enemies) & square) != 0)
			return true;

		for(int i = 0; i < 64; i++){
			long pos = AttackSets.getPosition(i);

			if((pos & N) != 0){
				if((generateKnightBitboard(i, empty, enemies) & square) != 0)
					return true;
			}else if((pos & B) != 0){
				if((generateBishopBitboard(i, occupied, empty, enemies) & square) != 0)
					return true;
			}else if((pos & R) != 0){
				if((generateRookBitboard(i, occupied, empty, enemies) & square) != 0)
					return true;
			}else if((pos & Q) != 0){
				if((generateQueenBitboard(i, occupied, empty, enemies) & square) != 0)
					return true;
			}else if((pos & K) != 0){
				if((generateKingBitboard(i, empty, enemies) & square) != 0)
					return true;
			}
		}
		return false;
	}

	public MoveArrayList getSortedMoves(String color, TPT.TPTEntry entry, Board board, boolean helper){
		MoveArrayList moves = this.findMoveList(board, color);

		if(helper){
			randomize(moves);
		}else if(entry != null)
			sort(moves, entry.bestMove, board);
		else{
			sort(moves);
		}
		return moves;
	}

	public synchronized double search(Board board, String playerColor, int depthLeft, PrincipalVariation pv, boolean debug, MeasurementData data, int searchNbr){
		SearchThread[] helpers = new SearchThread[3];
		for(int i = 0; i < helpers.length; i++){
			//helpers[i] = new SearchThread(new Board(board), playerColor, depthLeft, debug, true, this, searchNbr);
			//helpers[i].start();
		}
		SearchThread mainThread = new SearchThread(new Board(board), playerColor, depthLeft, debug, false, this, data, searchNbr);
		mainThread.start();
		try {
			wait(30000);
		} catch (InterruptedException ignored) {

		}
		for (SearchThread helper : helpers) {
			//helper.interrupt();
		}
		mainThread.interrupt();

		System.out.println("DEPTH: " + mainThread.lastDepth);
		pv.clear();
		pv.addAllMoves(mainThread.lastResult);
		return mainThread.lastScore;
	}
/*
		for(int i = 0; i < helpers.length; i++){
			try {
				helpers[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
 */
	public double alphaBetaMax(Board board, int depthLeft, double alpha, double beta, PrincipalVariation pv, int depth, long prevHash, boolean debug, boolean helper, MeasurementData data, int searchNbr, boolean lastIteration) throws InterruptedException {
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}

		TPT.TPTEntry entry = tpt.get(prevHash);
		if(entry != null && entry.depth >= depthLeft && entry.playerToMove == 1){
			if(lastIteration && data != null)
				data.incrementTPHits(searchNbr, depth);
			if(entry.nodeType == TPT.EntryType.PVNODE){
				if(lastIteration && data != null)
					data.incrementPVHits(searchNbr, depth);
				pv.addMove(entry.bestMove);
				return entry.score;
			}
			if(entry.nodeType == TPT.EntryType.CUTNODE && entry.score >= beta){
				if(lastIteration && data != null)
					data.incrementCUTHits(searchNbr, depth);
				pv.addMove(entry.bestMove);
				return beta;
			}
			if(entry.nodeType == TPT.EntryType.ALLNODE && entry.score <= alpha){
				if(lastIteration && data != null)
					data.incrementALLHits(searchNbr, depth);
				pv.addMove(entry.bestMove);
				return alpha;
			}
		}

		//End condition
		if(depthLeft == 0){
			double score = evalPosition(board);
			if(score == -20000)
				score = score+depth;
			pv.clear();
			return score;
		}
		PrincipalVariation localPV = new PrincipalVariation();

		MoveArrayList moves = getSortedMoves("WHITE", entry, board, helper);

		//Alternate end condition
		if(moves.size()==0){
			if(board.checkColor("WHITE") == 1)
				return -20000+depth;
			else if(board.checkColor("BLACK") == 2) {
				System.out.println("BLACK CHECKMATED HIMSELF?!");
				return 20000;
			}
			else return 0;
		}

		double score;
		boolean exceededAlpha = false;
		Move bestMove = null;
		double bestScore = Double.NEGATIVE_INFINITY;

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			if(debug){
				if(LineDebugger.getMove(depth+1).equals(move))
					LineDebugger.match(depth+1);
				else
					LineDebugger.unmatch(depth+1);
			}

			//long hash = tpt.hash(simBoard);
			long hash = tpt.updateHash(board, prevHash, move);
			score = alphaBetaMin(simBoard, depthLeft - 1, alpha, beta, localPV, depth+1, hash, debug, false, data, searchNbr, lastIteration);

			//Beta-cutoff
			if(score >= beta){
				if(debug){
					if(LineDebugger.onLine(depth))
						System.out.println("BETA CUTOFF AT DEPTH: " + depth);
				}
				if(lastIteration && data != null)
					data.setBestMoveIndex(searchNbr, depth, i);
				tpt.put(prevHash, score, depthLeft, new Move(move), board, TPT.EntryType.CUTNODE, 1);
				MoveArrayListManager.renounceMoveArrayList(moves);
				return beta;
			}
			//(ALL-NODE: all children have score < beta)
			//(CUT-NODE: at least one child has score >= beta)
			//(PV-NODE all children have alpha < score < beta)
			//Score updates alpha, possible PV-node
			if(score > alpha){
				pv.clear();
				pv.addAllMoves(localPV);
				pv.addMove(move);

				exceededAlpha = true;
				alpha = score;
			}
			if(score > bestScore){
				bestMove = new Move(move);
				bestScore = score;
				if(lastIteration && data != null)
					data.setBestMoveIndex(searchNbr, depth, i);
			}
		}
		if(!exceededAlpha)
				tpt.put(prevHash, bestScore, depthLeft, bestMove, board, TPT.EntryType.ALLNODE, 1);
		else{
				tpt.put(prevHash, alpha, depthLeft, bestMove, board, TPT.EntryType.PVNODE, 1);
		}
		MoveArrayListManager.renounceMoveArrayList(moves);
		return alpha;
	}

	public double alphaBetaMin(Board board, int depthLeft, double alpha, double beta, PrincipalVariation pv, int depth, long prevHash, boolean debug, boolean helper, MeasurementData data, int searchNbr, boolean lastIteration) throws InterruptedException {
		if (Thread.interrupted()) {
			throw new InterruptedException();
		}

		TPT.TPTEntry entry = tpt.get(prevHash);
		if(entry != null && entry.depth >= depthLeft && entry.playerToMove == 2){
			if(lastIteration && data != null)
				data.incrementTPHits(searchNbr, depth);
			if(entry.nodeType == TPT.EntryType.PVNODE){
				if(lastIteration && data != null)
					data.incrementPVHits(searchNbr, depth);
				//pv.clear();
				pv.addMove(entry.bestMove);
				return entry.score;
			}
			if(entry.nodeType == TPT.EntryType.CUTNODE && entry.score <= alpha){
				if(lastIteration && data != null)
					data.incrementCUTHits(searchNbr, depth);
				//pv.clear();
				pv.addMove(entry.bestMove);
				return alpha;
			}
			if(entry.nodeType == TPT.EntryType.ALLNODE && entry.score >= beta){
				if(lastIteration && data != null)
					data.incrementALLHits(searchNbr, depth);
				//pv.clear();
				pv.addMove(entry.bestMove);
				return beta;
			}
		}

		//End condition
		if(depthLeft == 0){
			double score = evalPosition(board);
			if(score == 20000)
				score = score-depth;
			pv.clear();
			return score;
		}
		PrincipalVariation localPV = new PrincipalVariation();

		MoveArrayList moves = getSortedMoves("BLACK", entry, board, helper);

			//Alternate end condition
		if(moves.size()==0){
			if(board.checkColor("WHITE") == 1){
				System.out.println("WHITE CHECKMATED HIMSELF?!");
				return -20000;
			}
			else if(board.checkColor("BLACK") == 2)
				return 20000-depth;
			else return 0;
		}

		double score;
		boolean betaUpdated = false;
		Move bestMove = null;
		double bestScore = Double.POSITIVE_INFINITY;

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			Board simBoard = new Board(board);
			simBoard.makeMove(move.from, move.to);

			if(debug){
				if(LineDebugger.getMove(depth+1).equals(move))
					LineDebugger.match(depth+1);
				else
					LineDebugger.unmatch(depth+1);
			}

			//long hash = tpt.hash(simBoard);
			long hash = tpt.updateHash(board, prevHash, move);
			score = alphaBetaMax(simBoard, depthLeft - 1, alpha, beta, localPV, depth+1, hash, debug, false, data, searchNbr, lastIteration);

			//Alpha-cutoff
			if(score <= alpha){
				if(debug){
					if(LineDebugger.onLine(depth))
						System.out.println("ALPHA CUTOFF AT DEPTH: " + depth);
				}
				if(lastIteration && data != null)
					data.setBestMoveIndex(searchNbr, depth, i);
				tpt.put(prevHash, score, depthLeft, new Move(move), board, TPT.EntryType.CUTNODE, 2);
				MoveArrayListManager.renounceMoveArrayList(moves);
				return alpha;
			}

			//Updating beta, new possible PV-node
			if(score < beta){
				pv.clear();
				pv.addAllMoves(localPV);
				pv.addMove(move);

				beta = score;
				betaUpdated = true;
			}
			if(score < bestScore){
				bestMove = new Move(move);
				bestScore = score;
				if(lastIteration && data != null)
					data.setBestMoveIndex(searchNbr, depth, i);
			}

		}
		if(!betaUpdated)
				tpt.put(prevHash, bestScore, depthLeft, bestMove, board, TPT.EntryType.ALLNODE, 2);
		else{
				tpt.put(prevHash, beta, depthLeft, bestMove, board, TPT.EntryType.PVNODE, 2);
		}
		MoveArrayListManager.renounceMoveArrayList(moves);
		return beta;
	}

	public void sort(MoveArrayList moves, Move bestMove, Board board){

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);

			if(move.fromPiece == '_')
				System.out.println("SORT: ILLEGAL MOVE");

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


		moves.sort((o1, o2) -> Integer.compare(o2.score, o1.score));
	}

	public void sort(MoveArrayList moves){
		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			if(move.fromPiece == '_')
				System.out.println("SORT: ILLEGAL MOVE");

			if(move.toPiece == '_')
				continue;

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
		}


		moves.sort((o1, o2) -> Integer.compare(o2.score, o1.score));
	}

	public void randomize(MoveArrayList moves){

		for(int i = 0; i < moves.size(); i++){
			Move move = moves.get(i);
			move.score = rand.nextInt();
		}
		moves.sort((o1, o2) -> Integer.compare(o2.score, o1.score));
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
			points = points + 150;
		}else {
			if(board.castleWKValid){
				points = points + 75;
			}
			if(board.castleWQValid){
				points = points + 25;
			}
		}
		if(board.bHasCastled){
			points = points - 150;
		}else {
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
