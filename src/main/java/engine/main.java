package engine;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.sql.Array;
import java.util.*;

public class main {

    public static void main(String[] args) {

        try {
            Scanner scan = new Scanner(System.in);
            TPT tpt = new TPT(3000000);
            final Board board = new Board();
            MoveArrayListManager.size();
            final Engine engine = new Engine("WHITE", board, false, tpt);
            final Engine engine2 = new Engine("BLACK", board, true, tpt);
            Random rand = new Random();



			MoveArrayList moves = Util.parseMoveString("b2b4 e7e6 c1b2 g8f6 b4b5 c7c6 g1f3 d8a5 b2c3 a5b5 e2e3 b5c5 c3e5 b8a6 f1a6 b7a6 b1c3 c5b6 a1b1 f8b4 e5f6 g7f6 a2a3 b4a3 b1b6 a7b6 c3e4 a3e7 d1a1 e6e5 a1c3 h8g8 c3b3 g8g2 f3h4 g2g4 h4f5 g4e4 f5e7 e8e7 f2f3 e4h4");
            board.playLine(moves);
            board.draw();

            /*
            double result3 = 0;
            ArrayList<Move> pv3 = new ArrayList<Move>();;;
            for(int i = 0; i < 5; i++){
                if(i % 2 == 0){
                    pv3 = new ArrayList<Move>();
                    result3 = engine.alphaBetaMin(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv3, 0, 0L);
                }
            }

            double result2 = 0;
            ArrayList<Move> pv2 = new ArrayList<Move>();;
            long time = System.currentTimeMillis();
            for(int i = 0; i < 7; i++){
                if(i % 2 == 0){
                    pv2 = new ArrayList<Move>();
                    result2 = engine.alphaBetaMax(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, 0L);
                    System.out.println("depth: " + i);
                }
            }

            System.out.println(System.currentTimeMillis() - time);
            for(int i = 0; i < pv2.size(); i++){
                Move move2 = pv2.get(i);
                System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
            }
            System.out.println(result2);
*/
/*
			MoveArrayList moves2 = engine.generateMoves(board, "BLACK");
			for(int i = 0; i < moves2.size(); i++){
			    Move move2 = moves2.get(i);
			    System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
            }
*/

            /*
            ArrayList<Move> pv2 = new ArrayList<Move>();
            double result2 = engine.alphaBetaMax(board, 5, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1, 0L);
            for(Move move : pv2){
                System.out.println(Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
            }
            System.out.println(result2);
            System.out.println("bestmove " + Util.convertNumToCoord(pv2.get(pv2.size()-1).from) + Util.convertNumToCoord(pv2.get(pv2.size()-1).to));
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
            MoveArrayList moves2 = engine.findMoveList(board, "WHITE");
            for(int i = 0; i < moves2.size(); i++){
                Move move = moves2.get(i);
                System.out.println(Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
            }
*W
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
                                    int depthLeft = 2;
                                    double result = 0;
                                    while ((System.currentTimeMillis() - time) < 1000 && depthLeft < 10) {
                                        System.out.println("Depth: " + depthLeft);
                                        result = engine.alphaBetaMax(board, depthLeft, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
                                        System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
                                        depthLeft += 2;
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
                                    double result = engine.alphaBetaMin(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
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
