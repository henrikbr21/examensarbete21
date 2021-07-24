package engine;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) {

		try{
			Scanner scan = new Scanner(System.in);
			TPT tpt = new TPT();
			final Board board = new Board();
			final Engine engine = new Engine("WHITE", board, false, tpt);
			final Engine engine2 = new Engine("BLACK", board, true, tpt);

/*
			ArrayList<Move> moves = Util.parseMoveString("e2e4 b8c6 d1f3 a7a6 f3c3 g8f6 d2d3 a6a5 c1f4 a8b8 b1d2 d7d6 f4g5 f6g4 f2f3 g4e5 e1c1 f7f6 g5f4 e5g6 f4g3 e7e5 c1b1 c8e6 a2a3 b8a8 h2h4 h7h5 g1e2 c6e7 d3d4 c7c6 d4e5 f6e5 c3e3 e7g8 e3g5 d8g5 h4g5 g8e7 h1h2 h5h4 g3f2 c6c5 e2c3 e6d7 c3b5 d7b5 f1b5 e8d8 d2c4 d8c7 d1d6 a8d8 f2c5 d8d6 c5d6 c7c8 c4b6 c8d8 b6d7 e7c8 d7f8 g6f8 d6e5 f8e6 e5c3 c8d6 b5d3 e6g5 c3g7 h8g8 g7f6 d8e8 e4e5 g5f3 g2f3 g8g1 b1a2 d6c8 h2h4 c8b6 e5e6 b6d7 d3b5 e8f8 e6d7 g1d1 b5d3 f8f7 d7d8q d1d3 c2d3 a5a4 f3f4 b7b5 f4f5 b5b4 a3b4 a4a3 d3d4 a3b2");
			board.playLine(moves);
			ArrayList<Move> movesAvailable = engine.findMoveList(board, "BLACK");
			System.out.println("Checkmate: " + board.checkmate());
			System.out.println("Available" + movesAvailable.size());
			board.draw();
			ArrayList<Move> pv2 = new ArrayList<Move>();
			long time = System.currentTimeMillis();

			double result2 = engine.alphaBetaMin(board, 1, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1);
			System.out.println("Time: " + (System.currentTimeMillis()-time));
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

								double result = engine.alphaBetaMax(board, 5, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1);
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

				}else if(input.startsWith("go")  && ((nbrTokens%2) != 0)){
					ArrayList<Move> pv = new ArrayList<Move>();
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
