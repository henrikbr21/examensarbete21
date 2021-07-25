package engine;

import java.sql.Array;
import java.util.*;

public class main {

	public static void main(String[] args) {

		try{
			Scanner scan = new Scanner(System.in);
			TPT tpt = new TPT();
			final Board board = new Board();
			final Engine engine = new Engine("WHITE", board, false, tpt);
			final Engine engine2 = new Engine("BLACK", board, true, tpt);
			Random rand = new Random();

/*
			ArrayList<Move> moves = Util.parseMoveString("c2c3 d7d6 g1f3 c8d7 f3d4 e7e5 d4c2 b8c6 c2b4 g8f6 f2f3 c6b4 c3b4 f6d5 h2h4 d5b4 a2a3 b4d5 d1b3 d5b6 b1c3 d7c6 e2e4 a7a5 f1b5 b6c8 b5c6 b7c6 b3a4 c8e7 d2d4 f7f6 d4e5 d6e5 a4c4 d8d6 c1e3 f6f5");
			board.playLine(moves);
			board.draw();
			Util.draw(board.WK);
			ArrayList<Move> movesAvailable = engine.generateMoves(board, "WHITE");
			for(Move move : movesAvailable){
				System.out.println(Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
			}
			System.out.println("Checkmate: " + board.checkmate());
			System.out.println("Available" + movesAvailable.size());
			board.draw();
			ArrayList<Move> pv2 = new ArrayList<Move>();
			long time = System.currentTimeMillis();

			double result2 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1);
			System.out.println("SCORE " + result2);
			System.out.println("bestmove " + Util.convertNumToCoord(pv2.get(pv2.size()-1).from) + Util.convertNumToCoord(pv2.get(pv2.size()-1).to));
*/
			Thread thread;
			int i = 0;
			int nbrTokens = 0;
			while(true){
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

				if(input.equals("uci")){
					System.out.println("id name Klas 0.2");
					System.out.println("id author Henrik");
				}else if(input.equals("isready")){
					for(int j = 0; j < 100; j++){
						ArrayList<Move> pv = new ArrayList<Move>();
						double result = engine.alphaBetaMax(board, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1);
					}
					System.out.println("readyok");
				}else if(input.equals("ucinewgame")){
				}else if(input.startsWith("position startpos ")){
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

					while(st.hasMoreTokens()){
						nbrTokens++;
						String move = st.nextToken();
						board.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));
					}
					System.out.println("NUMBER OF TOKENS: " + nbrTokens);

				}else if(input.startsWith("go")  && (nbrTokens%2==0)){
					ArrayList<Move> pv = new ArrayList<Move>();

					if(nbrTokens < 2){
						ArrayList<Move> startingMoves = new ArrayList<Move>();
						startingMoves = engine.findMoveList(board, "WHITE");
						Move move = startingMoves.get(rand.nextInt(20));
						System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
					}else{
						thread = new Thread(new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									ArrayList<Move> moves = engine.findMoveList(board, "WHITE");
									for(Move move : moves){
										Board simBoard = new Board(board);
										simBoard.makeMove(move.from, move.to);
										if(simBoard.checkmate()==2){
											System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
										}
									}

									double result = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1);
									System.out.println("bestmove " + Util.convertNumToCoord(pv.get(pv.size()-1).from) + Util.convertNumToCoord(pv.get(pv.size()-1).to));
									System.out.println("Score: " + result);


								}
								catch(Exception e)
								{
									e.printStackTrace();
								}
							}
						});
						thread.start();
					}

				}else if(input.startsWith("go")  && ((nbrTokens%2) != 0)){
					ArrayList<Move> pv = new ArrayList<Move>();
					if(nbrTokens < 2){
						ArrayList<Move> startingMoves = new ArrayList<Move>();
						startingMoves = engine.findMoveList(board, "BLACK");
						Move move = startingMoves.get(rand.nextInt(20));
						System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
					}else {
						thread = new Thread(new Runnable()
						{
							@Override
							public void run()
							{
								try
								{
									ArrayList<Move> moves = engine.findMoveList(board, "BLACK");
									for(Move move : moves){
										Board simBoard = new Board(board);
										simBoard.makeMove(move.from, move.to);
										if(simBoard.checkmate()==1){
											System.out.println("bestmove " + Util.convertNumToCoord(move.from) + Util.convertNumToCoord(move.to));
										}
									}

									double result = engine.alphaBetaMin(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1);
									System.out.println("bestmove " + Util.convertNumToCoord(pv.get(pv.size()-1).from) + Util.convertNumToCoord(pv.get(pv.size()-1).to));
									System.out.println("Score: " + result);

								}
								catch(Exception e)
								{
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



		}catch(RuntimeException e){
			e.printStackTrace();
		}


	}

}
