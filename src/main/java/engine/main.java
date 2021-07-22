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


			ArrayList<Move> moves = Util.parseMoveString("b1c3 d7d5 e2e3 e7e5 g1f3 b8c6 f1b5 e5e4 b5c6 b7c6 f3g1 g8f6 g1e2 f8b4 a2a3 b4c5 d2d4 c5d6 c1d2 e8g8 h2h3 c8a6 e2c1 a8b8 b2b3 c6c5 f2f4 c5c4 d1e2 b8b6 b3b4 g7g6 e1g1 f6h5 e2f2 d6e7 f4f5 e7h4 g2g3 h4g3 f2g2 a6c8 f5g6 f7g6 f1f8 d8f8 c3d5 b6e6 c1e2 e6d6 d5c7 f8f5 e2g3 h5g3 g2g3 f5h3 g3d6 h3g4 g1f1 g4h3 f1g1 h3d7 d6d5 d7d5 c7d5 g8g7 d2c3 g6g5 d5c7 g7g6 g1h2 g5g4");
			//board.playLine(moves);
			ArrayList<Move> movesAvailable = engine.findMoveList(board, "WHITE");
			board.draw();
			ArrayList<Move> pv2 = new ArrayList<Move>();
			long time = System.currentTimeMillis();
			double result2 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1);
			System.out.println("Time: " + (System.currentTimeMillis()-time));
			System.out.println("bestmove " + Util.convertNumToCoord(pv2.get(pv2.size()-1).from) + Util.convertNumToCoord(pv2.get(pv2.size()-1).to));



			Thread thread;
			int i = 0;
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
						String move = st.nextToken();
						board.makeMove(Util.convertCoordToNum(move.substring(0, 2)), Util.convertCoordToNum(move.substring(2)));
					}

				}else if(input.startsWith("go")){
					ArrayList<Move> pv = new ArrayList<Move>();

					thread = new Thread(new Runnable()
					{
						@Override
						public void run()
						{
							try
							{
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
