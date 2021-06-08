package engine;

public abstract class Util {

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
}
