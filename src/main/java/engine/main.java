package engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

public class main {

	public static void main(String[] args) {
		/*
		try{

			Scanner scan = new Scanner(System.in);
			Board board = new Board();
			final Engine engine = new Engine("WHITE", board, false);
			Thread thread;
			while(true){
				String input = scan.nextLine();

				if(input.equals("uci")){
					System.out.println("id name Klas 0.1");
					System.out.println("id author Henrik");
				}else if(input.equals("isready")){
					System.out.println("readyok");
				}else if(input.equals("ucinewgame")){
				}else if(input.startsWith("position startpos ")){

					StringTokenizer st = new StringTokenizer(input, " ");
					String a = st.nextToken();
					System.out.println(a);
					String b = st.nextToken();
					System.out.println(b);
					String c = st.nextToken();
					System.out.println(c);

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
								double result = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0, 0);
								System.out.println("bestmove " + Util.convertNumToCoord(pv.get(pv.size()-1).from) + Util.convertNumToCoord(pv.get(pv.size()-1).to));
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
*/
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



		/*
		}catch(RuntimeException e){
			e.printStackTrace();
		}

		 */
	}

}
