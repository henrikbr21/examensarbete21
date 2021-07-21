package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) {

		try{
			Scanner scan = new Scanner(System.in);
			final Board board = new Board();
			final Engine engine = new Engine("WHITE", board, false);
			TPT tpt = new TPT();


			long time = System.currentTimeMillis();
			ArrayList<Move> moves = Util.parseMoveString("b1c3 g7g6 g1f3 f8g7 e2e4 b8c6 f1b5 c6e5 c3d5 c7c6 f3e5 g7e5 f2f4 e5f4 d5f4 c6b5 e1g1 d7d6 d1e2 c8d7 c2c4 a7a6 d2d4 g8f6 f4d5 f6d5 c4d5 e8g8 c1h6 f8e8 e2f3 d8c8 a1c1 d7g4 f3f2 c8d7 h2h3 g4h5 g2g4 h5g4 h3g4 d7g4 f2g2 g4g2 g1g2 e8c8 c1c8 a8c8 f1c1 c8d8 c1c7 f7f5 e4f5 g6f5 c7e7 b5b4 e7b7 a6a5 b7a7 g8h8 a7a5 d8b8 b2b3 h8g8 h6f4 b8d8 a5a4 h7h5 g2f1 g8g7 a4b4 h5h4 f1e1 h4h3 e1d1 g7g6 d1c1 g6h5 c1b1 h5g4 f4h2 g4f3 b4b5 f3g2 h2f4 h3h2 f4h2 g2h2 b5b6 f5f4 a2a3 f4f3 b6c6 f3f2 c6c1 h2g2 b1a2 f2f1q c1f1 g2f1 a3a4 f1e2 a4a5 e2d3 a5a6 d3d4 a2a1 d4d5 a6a7 d5d4 a1b1 d4c3 b1a2 d6d5 a2a3 d5d4 b3b4 d4d3 a3a2 c3b4 a2a1 d3d2 a7a8q d8a8");
			//board.playLine(moves);
			board.draw();
			ArrayList<Move> pv2 = new ArrayList<Move>();
			double result2 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1);
			System.out.println("Time:" + (System.currentTimeMillis()-time));
			for(Move move: pv2){
				System.out.print(Util.convertNumToCoord(move.from));
				System.out.println(Util.convertNumToCoord(move.to));
			}
			System.out.println(result2);


			Thread thread;
			int i = 0;
			while(true){
			i++;
			String input = scan.nextLine();

				if(input.equals("uci")){
					System.out.println("id name Klas 0.1");
					System.out.println("id author Henrik");
				}else if(input.equals("isready")){
					for(int j = 0; j < 100; j++){
						ArrayList<Move> pv = new ArrayList<Move>();
						double result = engine.alphaBetaMax(board, 2, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1);
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

					board.draw();

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
