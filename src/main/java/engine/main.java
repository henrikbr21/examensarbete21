package engine;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.sql.Array;
import java.util.*;

public class main {

    public static void main(String[] args) {

        try {
            Scanner scan = new Scanner(System.in);
            TPT tpt = new TPT(1000000);
            final Board board = new Board();
            MoveArrayListManager.size();
            final Engine engine = new Engine("WHITE", board, false, tpt);
            final Engine engine2 = new Engine("BLACK", board, true, tpt);
            Random rand = new Random();

            /*
            long time = System.currentTimeMillis();
            ArrayList<Move> pv2 = new ArrayList<Move>();
            double result2 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1, 0L);
            System.out.println("pv2");
            System.out.println("TIME: " + (System.currentTimeMillis()-time));
*/
            /*
            long time = System.currentTimeMillis();
            long hash = 0L;
            for(int i = 0; i < 10; i++){
                tpt.put(hash, tpt.new TPTEntry(hash, 5, 5, 0, 1));
                hash++;
            }
            tpt.put(10L, tpt.new TPTEntry(10L, 10, 5, 0, 1));

            for(int i = 0; i < 10; i++){
                System.out.print("HASH: " + tpt.hashes[i] + " ");
                System.out.println("ENTRY: " + tpt.get(tpt.hashes[i]).hash);
            }

            System.out.println("TIME: " + (System.currentTimeMillis()-time));
*/


/*
			MoveArrayList moves = Util.parseMoveString("d2d4 e7e5 e2e4 b8c6 g1f3 e5d4 f3d4 f8b4 c1d2 b4d2 d1d2 g8e7 b1c3 e8g8 e1c1 d8e8 d4f5 e7f5 e4f5 e8e7 f1b5 c6d8 b5d7 e7d7 d2d7 c8d7 d1d7 a8c8 h1e1");
			//board.playLine(moves);

			ArrayList<Move> pv2 = new ArrayList<Move>();
/*
			long time = System.currentTimeMillis();
			for(int i = 0; i < 10000; i++){
				engine.generateMoves(board, "WHITE");
			}
			System.out.println("Pseudolegal: " + (System.currentTimeMillis()-time));

			time = System.currentTimeMillis();
			for(int i = 0; i < 10000; i++){
				engine.findMoveList(board, "WHITE");
			}
			System.out.println("Legal: " + (System.currentTimeMillis()-time));

			time = System.currentTimeMillis();
			double result2 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1);
			System.out.println("Alphabeta 4: " + (System.currentTimeMillis()-time));
			System.out.println("SCORE " + result2);
			System.out.println("bestmove " + Util.convertNumToCoord(pv2.get(pv2.size()-1).from) + Util.convertNumToCoord(pv2.get(pv2.size()-1).to));
*/
            /*
            int node = 0;
            long time = System.currentTimeMillis();
            MoveArrayList moves = engine.findMoveList(board, "WHITE");
            for(int i = 0; i < moves.size(); i++){
                Board simBoard = new Board(board);
                Move move = moves.get(i);
                simBoard.makeMove(move.from, move.to);
                MoveArrayList moves2 = engine.findMoveList(simBoard, "BLACK");
                for(int k = 0; k < moves2.size(); k++){
                    Board simBoard2 = new Board(simBoard);
                    Move move2 = moves2.get(i);
                    simBoard2.makeMove(move2.from, move2.to);
                    MoveArrayList moves3 = engine.findMoveList(simBoard2, "WHITE");

                    for(int j = 0; j < moves3.size(); j++){
                        Board simBoard3 = new Board(simBoard2);
                        Move move3 = moves3.get(i);
                        simBoard3.makeMove(move3.from, move3.to);
                        MoveArrayList moves4 = engine.findMoveList(simBoard3, "BLACK");
                        node++;
                    }
                }
            }
            System.out.println("NODES: " + node);
            System.out.println("TIME: " + (System.currentTimeMillis()-time));
*/
            Thread thread;
            Thread t2;
            Thread t3;
            Thread t4;
            int i = 0;
            int nbrTokens = 0;

            while (true) {
                i++;
                String input = scan.nextLine();
				/*
				String input = "";
				if(i==1){
					input = "position startpos";
				}else if(i==2) {
					input = "go wtime 1800000 btime 1800000 winc 0 binc 0";
				}else if(i==3){
					input = "position startpos moves b1c3 d7d5";
				}else if(i==4){
					input = "go wtime 1784630 btime 1797390 winc 0 binc 0";
				}
				*/

                if (input.equals("uci")) {
                    System.out.println("id name Klas 0.2");
                    System.out.println("id author Henrik");
                } else if (input.equals("isready")) {
                    for (int j = 0; j < 100; j++) {
                        ArrayList<Move> pv = new ArrayList<Move>();
                        double result = engine.alphaBetaMax(board, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
                    }
                    System.out.println("readyok");
                }else if(input.equals("stop")){
                    Util.dumpGCLogs();
                } else if (input.equals("ucinewgame")) {
                } else if (input.startsWith("position startpos ")) {
                    //board.draw();
                    nbrTokens = 0;
                    StringTokenizer st = new StringTokenizer(input, " ");
                    String a = st.nextToken();
                    System.out.println(a);
                    String b = st.nextToken();
                    System.out.println(b);
                    String c = st.nextToken();
                    System.out.println(c);
                    board.reset();
                    //board.draw();

                    while (st.hasMoreTokens()) {
                        nbrTokens++;
                        String move = st.nextToken();
                        board.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));
                    }
                    System.out.println("NUMBER OF TOKENS: " + nbrTokens);

                } else if (input.startsWith("go") && (nbrTokens % 2 == 0)) {
                    ArrayList<Move> pv = new ArrayList<Move>();

                    if (nbrTokens < 2) {
                        MoveArrayList startingMoves = engine.findMoveList(board, "WHITE");
                        Move move = startingMoves.get(rand.nextInt(20));
                        System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
                    } else {


                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {

                                    long time = System.currentTimeMillis();
                                    MoveArrayList moves = engine.findMoveList(board, "WHITE");
                                    for (int i = 0; i < moves.size(); i++) {
                                        Move move = moves.get(i);
                                        Board simBoard = new Board(board);
                                        simBoard.makeMove(move.from, move.to);
                                        if (simBoard.checkmate() == 2) {
                                            System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
                                            Util.dumpGCLogs();
                                        }
                                    }
                                    int depthLeft = 1;
                                    double result = 0;
                                    while ((System.currentTimeMillis() - time) < 3000 && depthLeft < 10) {
                                        System.out.println("Depth: " + depthLeft);
                                        result = engine.alphaBetaMax(board, depthLeft, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
                                        System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
                                        depthLeft++;
                                    }
                                    board.draw();
                                    Util.dumpGCLogs();
                                    System.out.println("bestmove " + Util.convertNumToCoord(pv.get(pv.size() - 1).from) + Util.convertNumToCoord(pv.get(pv.size() - 1).to));
                                    System.out.println("Score: " + result);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();

                    }

                } else if (input.startsWith("go") && ((nbrTokens % 2) != 0)) {
                    ArrayList<Move> pv = new ArrayList<Move>();
                    if (nbrTokens < 2) {
                        MoveArrayList startingMoves = engine.findMoveList(board, "BLACK");
                        Move move = startingMoves.get(rand.nextInt(20));
                        System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
                    } else {
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    MoveArrayList moves = engine.findMoveList(board, "BLACK");
                                    for (int i = 0; i < moves.size(); i++) {
                                        Move move = moves.get(i);
                                        Board simBoard = new Board(board);
                                        simBoard.makeMove(move.from, move.to);
                                        if (simBoard.checkmate() == 1) {
                                            System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
                                            Util.dumpGCLogs();
                                        }
                                    }
                                    board.draw();
                                    double result = engine.alphaBetaMin(board, 5, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
                                    Util.dumpGCLogs();
                                    System.out.println("bestmove " + Util.convertNumToCoord(pv.get(pv.size() - 1).from) + Util.convertNumToCoord(pv.get(pv.size() - 1).to));
                                    System.out.println("Score: " + result);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        thread.start();
                    }
                }
            }
/*
		Board board = new Board();
		Engine engine = new Engine("WHITE", board, false);
		long time = System.currentTimeMillis();
		ArrayList<Move> pv = new ArrayList<Move>();
		double result = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0, 0);
		System.out.println(System.currentTimeMillis()-time);
		for(Move move : pv){
			System.out.print(Util.convertNumToCoord(move.from));
			System.out.println(Util.convertNumToCoord(move.to));
		}
		System.out.println("RESULT: " + result);
*/


        } catch (RuntimeException e) {
            e.printStackTrace();
        }


    }

}
