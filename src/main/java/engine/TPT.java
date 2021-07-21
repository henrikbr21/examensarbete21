package engine;

import java.util.HashMap;
import java.util.Random;

public class TPT extends HashMap<Long, TPT.TPTEntry>{
    long[][] randomNumbers;
    final int WP = 0;
    final int WN = 1;
    final int WB = 2;
    final int WR = 3;
    final int WQ = 4;
    final int WK = 5;
    final int BP = 6;
    final int BN = 7;
    final int BB = 8;
    final int BR = 9;
    final int BQ = 10;
    final int BK = 11;
    private int modern = 0;

    public class TPTEntry{
        long hash;
        int score;
        int depth;
        int generation;

        public TPTEntry(long hash, int score, int depth, int generation){
            this.hash = hash;
            this.score = score;
            this.depth = depth;
            this.generation = generation;
        }
    }

    public TPT(){
        randomNumbers = new long[64][12];
        initZobrist();
    }

    private void initZobrist(){
        Random rand = new Random();
        for(int i = 0; i < 64; i++){
            for(int j = 0; j < 12; j++){
                randomNumbers[i][j] = rand.nextLong();
            }
        }
    }

    public long hash(Board board){
        long hash = 0L;

        for(int i = 0; i < 64; i++){
            char piece = board.getPiece(i);
            if(piece != '_'){
                switch(piece){
                    case 'P':
                        hash = hash ^ randomNumbers[i][0];
                        break;
                    case 'N':
                        hash = hash ^ randomNumbers[i][1];
                        break;
                    case 'B':
                        hash = hash ^ randomNumbers[i][2];
                        break;
                    case 'R':
                        hash = hash ^ randomNumbers[i][3];
                        break;
                    case 'Q':
                        hash = hash ^ randomNumbers[i][4];
                        break;
                    case 'K':
                        hash = hash ^ randomNumbers[i][5];
                        break;
                    case 'p':
                        hash = hash ^ randomNumbers[i][6];
                        break;
                    case 'n':
                        hash = hash ^ randomNumbers[i][7];
                        break;
                    case 'b':
                        hash = hash ^ randomNumbers[i][8];
                        break;
                    case 'r':
                        hash = hash ^ randomNumbers[i][9];
                        break;
                    case 'q':
                        hash = hash ^ randomNumbers[i][10];
                        break;
                    case 'k':
                        hash = hash ^ randomNumbers[i][11];
                        break;
                }
            }
        }
        return hash;
    }


}
