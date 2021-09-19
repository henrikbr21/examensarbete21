package engine;

import java.util.*;

public class main {

    public static void main(String[] args) {

        try {
            AttackSets.init();
            Scanner scan = new Scanner(System.in);
            TPT tpt = new TPT(1000000);
            final Board board = new Board();
            MoveArrayListManager.size();
            final Engine engine = new Engine(tpt);
            final Engine engine2 = new Engine(tpt);
            Random rand = new Random();
/*
            MoveArrayList line = Util.parseMoveString("c2c3 a7a6 c3c4 b8c6 b1c3 c6e5 e2e3 e7e6 d2d4 e5c6 d4d5 c6e7 e3e4 c7c5 d5e6 d7e6 d1d8 e8d8 c1g5 d8c7 g1f3 e7c6 e1c1 g8f6 c1b1 c7b8 g5h4 h7h6 h4f6 g7f6 d1d2 b8a7 a2a3 h8g8 g2g3 f8e7 h2h3 g8g7 f1e2 b7b6 a3a4 h6h5 h1g1 c6b4 h3h4 f6f5 e4f5 e6f5 c3d5 b4d5 d2d5 c8e6 d5d3 f5f4 g3f4 g7g1 f3g1 e6f5 g1f3 e7d6 f3e5 d6e5 f4e5 a8d8 b1a2 d8d3 e2d3 f5d3 b2b3 a6a5 a2a1 d3c2 a1b2 c2d1 b2c3 d1f3 c3c2 a7b7 c2d2 f3e4 d2c1 e4d3 c1b2 d3e4 b2c3 e4f5 c3d2 f5h3 d2e2 h3e6 e2d1 e6f5 d1d2 f5d7 d2c2 d7h3 c2b1 h3g4 b1b2 g4f5 b2c3 f5g4 c3d2 g4d7 d2c2 b7a7 c2c1 d7c6 c1b1 c6h1 b1b2 h1f3 b2c2 f3g4 c2b2 g4d7 f2f4 d7c8 b2c2 c8f5 c2d1 f5c8 d1e2 c8g4 e2e1 g4e6 e1e2 e6h3 e2d1 h3g4 d1e1 g4h3 e1d1 h3g4 d1d2 g4f3 d2c1 a7b7 c1c2 f3e2 f4f5 e2f1 e5e6 f7e6 f5f6 b6b5 a4b5 a5a4 f6f7 a4b3 c2b3 f1e2 f7f8q e2d1 b3c3 e6e5 f8d6 d1e2 d6c6 b7b8 b5b6 e2c4 c3c4 e5e4 c6e4 b8c8 e4d5 c8b8 d5h1 b8c8 h1a1 c8b7 c4c5 b7b8 b6b7 b8b7 a1b1 b7c7");
            board.playLine(line);
            board.draw();

            PrincipalVariation pv2 = new PrincipalVariation();
            //double result3 = engine.alphaBetaMin(board, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, tpt.hash(board), false);

            long time = System.currentTimeMillis();
            double result2 = 0;
            for(int i = 0; i < 11; i++){
                pv2.clear();
                if(i == 5)
                    System.out.println("HEJ");
                result2 = engine.alphaBetaMax(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, tpt.hash(board), false);
                System.out.println("DEPTH: " + i);
                System.out.println("Time: " + (System.currentTimeMillis() - time));
                System.out.println(result2);
                for(int j = 0; j < pv2.size(); j++){
                    Move move2 = pv2.get(j);
                    System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
                }

            }



/*
            PrincipalVariation pv3 = new PrincipalVariation();
            time = System.currentTimeMillis();
            //result2 = engine.alphaBetaMax(board, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv3, 0, 0L, false, false);
            System.out.println(result2);
            System.out.println("Time: " + (System.currentTimeMillis() - time));
            for(int i = 0; i < pv3.size(); i++){
                Move move2 = pv3.get(i);
                System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
            }
            System.out.println(result2);
            Debug.printTPHits();
            Debug.clearTPHits();


 */
            /*
            board.makeMove(Util.convertCoordToNum("d1"), Util.convertCoordToNum("c1"));
            result2 = engine.alphaBetaMin(board, 5, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, 0L, true);
            System.out.println("Time: " + (System.currentTimeMillis() - time));
            for(int i = 0; i < pv2.size(); i++){
                Move move2 = pv2.get(i);
                System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
            }
            System.out.println(result2);
            Debug.printTPHits();
            Debug.clearTPHits();
            /*
            time = System.currentTimeMillis();
            result2 = engine.alphaBetaMax(board, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, 0L, true);
            System.out.println("Time: " + (System.currentTimeMillis() - time));
            Debug.printTPHits();
            for(int i = 0; i < pv2.size(); i++){
                Move move2 = pv2.get(i);
                System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
            }
            /*
            long hash = tpt.hash(board);
            TPT.TPTEntry entry = tpt.get(hash);
            if(entry == null)
                System.out.println("NO ENTRY1");
            else{
                System.out.println("ENTRY1");
                System.out.println(entry.hash);
                System.out.println(entry.score);
                if(entry.bestMove != null)
                    System.out.println(Util.convertNumToCoord(entry.bestMove.from) + Util.convertNumToCoord(entry.bestMove.to));
            }
            board.makeMove(Util.convertCoordToNum("b1"), Util.convertCoordToNum("a3"));
            hash = tpt.hash(board);
            entry = tpt.get(hash);
            if(entry == null)
                System.out.println("NO ENTRY2");
            else{
                System.out.println("ENTRY2");
                System.out.println(entry.hash);
                System.out.println(entry.score);
                if(entry.bestMove != null)
                    System.out.println(Util.convertNumToCoord(entry.bestMove.from) + Util.convertNumToCoord(entry.bestMove.to));
                else
                    System.out.println("No best move");
            }
            board.makeMove(Util.convertCoordToNum("d7"), Util.convertCoordToNum("d5"));
            hash = tpt.hash(board);
            entry = tpt.get(hash);
            if(entry == null)
                System.out.println("NO ENTRY3");
            else{
                System.out.println("ENTRY3");
                System.out.println(entry.hash);
                System.out.println(entry.score);
                if(entry.bestMove != null)
                    System.out.println(Util.convertNumToCoord(entry.bestMove.from) + Util.convertNumToCoord(entry.bestMove.to));
                else
                    System.out.println("No best move");
            }
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
            int nbrTokens = 0;
            while(true) {
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
                        PrincipalVariation pv = new PrincipalVariation();
                        double result = engine.alphaBetaMax(board, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), false, false);
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
                    PrincipalVariation pv = new PrincipalVariation();

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
                                    int depthLeft = 30;
                                    double result = 0;
                                    /*
                                    while ((System.currentTimeMillis() - time) < 1000 && depthLeft < 11) {
                                        System.out.println("Depth: " + depthLeft);
                                        result = engine.alphaBetaMax(board, depthLeft, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), false);
                                        System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
                                        depthLeft += 1;
                                    }
                                     */
                                    engine.search(board, "WHITE", depthLeft, pv, false);
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
                    PrincipalVariation pv = new PrincipalVariation();
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
                                    board.draw();
                                    //double result = engine.alphaBetaMin(board, 6, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), false, false);
                                    double result = engine.search(board,"BLACK", 6, pv, false);
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


        } catch (RuntimeException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}
