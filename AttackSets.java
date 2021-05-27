
public abstract class AttackSets {
	private static long moves[] = new long[64];
	
	public static void initKnightMoves() {
		long bottomLeft1 = 9223372036854775807L;
		bottomLeft1 = ~bottomLeft1;
		
		moves[0] = 9077567998918656L;
		moves[1] = 4679521487814656L;
		moves[2] = 38368557762871296L;
		moves[3] = 19184278881435648L;
		moves[4] = 9592139440717824L;
		moves[5] = 4796069720358912L;
		moves[6] = 2257297371824128L;
		moves[7] = 1128098930098176L;
		moves[8] = 2305878468463689728L;
		moves[9] = 1152939783987658752L;
		moves[10] = 576610629482184704L;
		moves[10] = moves[10] ^ bottomLeft1;
		moves[11] = 4899991333168480256L;
		moves[12] = 2449995666584240128L;
		moves[13] = 1224997833292120064L;
		moves[14] = 576469569871282176L;
		moves[15] = 288234782788157440L;
		moves[16] = 4620693356194824192L;
		moves[17] = 2310346680244895744L;
		moves[17] = moves[17] ^ bottomLeft1;
		moves[18] = 5802888705324613632L;
		moves[19] = 2901444352662306816L;
		moves[20] = 1450722176331153408L;
		moves[21] = 725361088165576704L;
		moves[22] = 362539804446949376L;
		moves[23] = 145241105196122112L;
		moves[24] = 18049583422636032L;
		moves[25] = 45053588738670592L;
		moves[26] = 22667534005174272L;
		moves[27] = 11333767002587136L;
		moves[28] = 5666883501293568L;
		moves[29] = 2833441750646784L;
		moves[30] = 1416171111120896L;
		moves[31] = 567348067172352L;
		moves[32] = 70506185244672L;
		moves[33] = 175990581010432L;
		moves[34] = 88545054707712L;
		moves[35] = 44272527353856L;
		moves[36] = 22136263676928L;
		moves[37] = 11068131838464L;
		moves[38] = 5531918402816L;
		moves[39] = 2216203387392L;
		moves[40] = 275414786112L;
		moves[41] = 687463207072L;
		moves[42] = 345879119952L;
		moves[43] = 172939559976L;
		moves[44] = 86469779988L;
		moves[45] = 43234889994L;
		moves[46] = 21609056261L;
		moves[47] = 8657044482L;
		moves[48] = 1075839008L;
		moves[49] = 2685403152L;
		moves[50] = 1351090312L;
		moves[51] = 675545156L;
		moves[52] = 337772578L;
		moves[53] = 168886289L;
		moves[54] = 84410376L;
		moves[55] = 33816580L;
		moves[56] = 4202496L;
		moves[57] = 10489856L;
		moves[58] = 5277696L;
		moves[59] = 2638848L;
		moves[60] = 1319424L;
		moves[61] = 659712L;
		moves[62] = 329728L;
		moves[63] = 132096L;
		
	}

	//returns attacking bitboard for a knight given a little-endian position.
	public static long knightMoves(int pos) {
		return moves[pos];
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
    }
}
