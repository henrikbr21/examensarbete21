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

            //long time = System.currentTimeMillis();

            //MoveArrayList line = Util.parseMoveString("e2e4 d7d6 b1c3 g8f6 d1f3 c8g4 f3d3 g4e6 d3b5 e6d7 b5b7");
            //board.playLine(line);
            //long time = System.currentTimeMillis();
            //engine.search(board, "WHITE", 6, new PrincipalVariation(), false);
            //System.out.println("Time: " + (System.currentTimeMillis()-time));

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
                                    /*
                                    while ((System.currentTimeMillis() - time) < 1000 && depthLeft < 11) {
                                        System.out.println("Depth: " + depthLeft);
                                        result = engine.alphaBetaMax(board, depthLeft, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), false);
                                        System.out.println("Time taken: " + (System.currentTimeMillis() - time) + "ms");
                                        depthLeft += 1;
                                    }
                                     */
                                    double result = engine.search(board, "WHITE", depthLeft, pv, false);
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
                                    double result = engine.search(board,"BLACK", 10, pv, false);
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
