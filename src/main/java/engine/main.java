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

            TestBoards.initBoards100();

            char[][] randomBoard = {
                    {'r', ' ', ' ', 'q', ' ', 'r', 'k', ' '},
                    {'p', 'p', ' ', ' ', 'p', 'p', 'b', 'p'},
                    {' ', ' ', 'b', ' ', ' ', ' ', 'p', ' '},
                    {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                    {' ', ' ', ' ', 'P', 'P', ' ', 'n', ' '},
                    {' ', ' ', ' ', 'B', 'Q', 'N', ' ', ' '},
                    {'P', ' ', ' ', ' ', ' ', 'P', 'P', 'P'},
                    {'R', ' ', 'B', ' ', ' ', 'R', 'K', ' '}
            };
            Board board2 = new Board(randomBoard);
            BoardGenerator.setBoard(board2);
            BoardGenerator.generateKlasBoards("D:\\Boards\\KLASBoards\\KLASBoards19.txt", 19, 190);


            /*
            TestBoards.initBoards100();
            MeasurementData data = new MeasurementData();
            for(ArrayList<Board> testBoards : TestBoards.boards){
                for(int i = 0; i < testBoards.size(); i++){
                    PrincipalVariation pv3 = new PrincipalVariation();
                    Board testBoard = testBoards.get(i);
                    double result = 0;
                    if(i % 2 == 0)
                        result = engine.search(testBoard, "WHITE", 6, pv3, false, data, i);
                    else
                        result = engine.search(testBoard, "BLACK", 6, pv3, false, data, i);
                    data.setScore(i, result);
                }
                break;
            }

            data.printToFile();

            //long time = System.currentTimeMillis();

            /*
            MoveArrayList line = Util.parseMoveString("e2e4 f7f6 d2d4 h7h5 f1e2 g7g6 g1f3 e7e6 e4e5 f8b4 c1d2 b4d2 b1d2 b8c6 c2c3 f6e5 f3e5 c6e5 d4e5 d8g5 d2f3 g5g2 h1g1");
            board.playLine(line);
            board.draw();
            long time = System.currentTimeMillis();
            PrincipalVariation pv3 = new PrincipalVariation();

            double result2 = 0;
            result2 = engine.search(board, "BLACK", 6, pv3, false);
            System.out.println("Time: " + (System.currentTimeMillis()-time));

            System.out.println(result2);
            for(int i = 0; i < pv3.size(); i++){
                Move move2 = pv3.get(i);
                System.out.println(Util.convertNumToCoord(move2.from) + Util.convertNumToCoord(move2.to));
            }
            System.out.println(result2);
            Debug.printTPHits();
            Debug.clearTPHits();
*/

            Thread thread;
            int nbrTokens = 0;
            if(false) {
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
                        double result = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, tpt.hash(board), false, false, new MeasurementData(), 0, false);
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
                                    double result = engine.search(board, "WHITE", 12, pv, false, new MeasurementData(), 0);
                                    board.draw();
                                    GCStats stat = Util.dumpGCLogs();
                                    System.out.println("Collection count: " + stat.count + " Collection time: " + stat.time);
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
                                    double result = engine.search(board,"BLACK", 12, pv, false, new MeasurementData(), 0);
                                    GCStats stat = Util.dumpGCLogs();
                                    System.out.println("Collection count: " + stat.count + " Collection time: " + stat.time);
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
