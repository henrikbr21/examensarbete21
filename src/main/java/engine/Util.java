package engine;

import java.util.ArrayList;

public abstract class Util {

	public static String convertNumToCoord(int i){
		String coord = "";
		int k = i%8;
		int j = (i/8)+1;

		switch(k) {
			case 0:
				return coord = coord + "a" + j;
			case 1:
				return coord = coord + "b" + j;
			case 2:
				return coord = coord + "c" + j;
			case 3:
				return coord = coord + "d" + j;
			case 4:
				return coord = coord + "e" + j;
			case 5:
				return coord = coord + "f" + j;
			case 6:
				return coord = coord + "g" + j;
			case 7:
				return coord = coord + "h" + j;
		}
		return coord;
	}

	public static String convertNumToAlph(int i) {
		int k = i%8;
		
		switch(k) {
			case 0:
				return "a";
			case 1:
				return "b";
			case 2:
				return "c";
			case 3:
				return "d";
			case 4:
				return "e";
			case 5:
				return "f";
			case 6:
				return "g";
			case 7: 
				return "h";
		}
		return "ERROR";
	}

	public static int convertCoordToNum(String coord){
		char coordLetter = coord.charAt(0);
		char coordNum = coord.charAt(1);

		int result = 0;
		switch(coordLetter) {
			case 'a':
				result = 0;
				break;
			case 'b':
				result = 1;
				break;
			case 'c':
				result = 2;
				break;
			case 'd':
				result = 3;
				break;
			case 'e':
				result = 4;
				break;
			case 'f':
				result = 5;
				break;
			case 'g':
				result = 6;
				break;
			case 'h':
				result = 7;
				break;
		}

		switch(coordNum) {
			case '1':
				result += (0*8);
				break;
			case '2':
				result += (1*8);
				break;
			case '3':
				result += (2*8);
				break;
			case '4':
				result += (3*8);
				break;
			case '5':
				result += (4*8);
				break;
			case '6':
				result += (5*8);
				break;
			case '7':
				result += (6*8);
				break;
			case '8':
				result += (7*8);
				break;
		}
		return result;
	}
	
	public static String padBinaryString(String string) {
		while(string.length() < 64) {
			string = "0" + string;
		}
		
		return string;
	}
	
	public static long boardFromArray(char[][] board) {
		long moves = 0L;
		
		int k = -1;
		for(int i = 0; i < 8; i++) {
			for(int j = 7; j >= 0; j--) {
			k++;
			
			String boardString = "0000000000000000000000000000000000000000000000000000000000000000";
			boardString = boardString.substring(k+1)  + "1" + boardString.substring(0, k);
			
			if(board[i][j] == '1')
				moves += Util.stringToLong(boardString);
			}
		}
		return moves;
	}

	public static double[] PSTFromArray(double[][] array){
		double[] PSTs = new double[64];

		int k = -1;
		for(int i = 0; i < 8; i++) {
			for(int j = 7; j >= 0; j--) {
				k++;
				PSTs[k] = array[j][i];
			}
		}
		return PSTs;
	}
	
	public static long stringToLong(String s) {
		if(s.charAt(0) == '0') {
			return Long.parseLong(s, 2);
		}else {
			return Long.parseLong("1" + s.substring(2), 2)*2;
		}
	}
	
	public static void draw(long bitBoard) {
		String bitBoardString = Long.toBinaryString(bitBoard);
		bitBoardString = Util.padBinaryString(bitBoardString);
		int q = 64;
		
        for (int i=0;i<8;i++) {
        	System.out.print("[");
        	int k = -1;
        	q = q - 8;
        			
        	for(int j = 0; j < 8; j++) {
        		k++;
        		
            	
                System.out.print(bitBoardString.charAt(q+k));
                if(k != 7) 
                	System.out.print(",");
        	}
        	System.out.println("]");
        }
        System.out.println("-----------------");
    }

    public static ArrayList<String> cloneArrayList(ArrayList<String> pv){
		ArrayList<String> copy = new ArrayList<String>();
		for(String move : pv){
			copy.add(new String(move));
		}
		return copy;
	}
}
