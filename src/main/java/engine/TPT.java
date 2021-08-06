package engine;

import java.util.HashMap;
import java.util.Random;

public class TPT extends HashMap<Long, TPT.TPTEntry> {
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
    private int size = 0;

    public class TPTEntry {
        long hash;
        double score;
        int depth;
        int generation;
        int turnToMove;

        public TPTEntry(long hash, double score, int depth, int generation, int turnToMove) {
            this.hash = hash;
            this.score = score;
            this.depth = depth;
            this.generation = generation;
            this.turnToMove = turnToMove;
        }
    }

    public TPT() {

    }

    public void put(long hash, TPT.TPTEntry tptEntry){
        if(this.size < 5000000){
            super.put(hash, tptEntry);
            size++;
        }else{
            this.clear();
            System.out.println("TPT CLEARED");
            this.size = 0;
        }

    }

    public void remove(long hash){
        super.remove(hash);
        this.size--;
    }

    public long updateHash(Board board, long hash, int from, int to) {
        char fromPiece = board.getPiece(from);
        char toPiece = board.getPiece(to);
        int fromPieceIndex = -1;

        hash ^= AttackSets.randomNumbers[from][12];

        switch (fromPiece) {
            case 'P':
                hash ^= AttackSets.randomNumbers[from][0];
                fromPieceIndex = 0;
                break;
            case 'N':
                hash ^= AttackSets.randomNumbers[from][1];
                fromPieceIndex = 1;
                break;
            case 'B':
                hash ^= AttackSets.randomNumbers[from][2];
                fromPieceIndex = 2;
                break;
            case 'R':
                hash ^= AttackSets.randomNumbers[from][3];
                fromPieceIndex = 3;
                break;
            case 'Q':
                hash ^= AttackSets.randomNumbers[from][4];
                fromPieceIndex = 4;
                break;
            case 'K':
                hash ^= AttackSets.randomNumbers[from][5];
                fromPieceIndex = 5;
                break;
            case 'p':
                hash ^= AttackSets.randomNumbers[from][6];
                fromPieceIndex = 6;
                break;
            case 'n':
                hash ^= AttackSets.randomNumbers[from][7];
                fromPieceIndex = 7;
                break;
            case 'b':
                hash ^= AttackSets.randomNumbers[from][8];
                fromPieceIndex = 8;
                break;
            case 'r':
                hash ^= AttackSets.randomNumbers[from][9];
                fromPieceIndex = 9;
                break;
            case 'q':
                hash ^= AttackSets.randomNumbers[from][10];
                fromPieceIndex = 10;
                break;
            case 'k':
                hash ^= AttackSets.randomNumbers[from][11];
                fromPieceIndex = 11;
                break;
            default:
                throw new IllegalArgumentException("Piece type:" + fromPiece + " Illegal move:" + Util.convertNumToCoord(from) + Util.convertNumToCoord(to));
        }

        hash ^= AttackSets.randomNumbers[to][fromPieceIndex];
        switch (toPiece) {
            case '_':
                hash ^= AttackSets.randomNumbers[to][12];
                break;
            case 'P':
                hash ^= AttackSets.randomNumbers[to][0];
                break;
            case 'N':
                hash ^= AttackSets.randomNumbers[to][1];
                break;
            case 'B':
                hash ^= AttackSets.randomNumbers[to][2];
                break;
            case 'R':
                hash ^= AttackSets.randomNumbers[to][3];
                break;
            case 'Q':
                hash ^= AttackSets.randomNumbers[to][4];
                break;
            case 'K':
                hash ^= AttackSets.randomNumbers[to][5];
                break;
            case 'p':
                hash ^= AttackSets.randomNumbers[to][6];
                break;
            case 'n':
                hash ^= AttackSets.randomNumbers[to][7];
                break;
            case 'b':
                hash ^= AttackSets.randomNumbers[to][8];
                break;
            case 'r':
                hash ^= AttackSets.randomNumbers[to][9];
                break;
            case 'q':
                hash ^= AttackSets.randomNumbers[to][10];
                break;
            case 'k':
                hash ^= AttackSets.randomNumbers[to][11];
                break;
            default:
                throw new IllegalArgumentException("Piece type:" + fromPiece + " Illegal move:" + Util.convertNumToCoord(from) + Util.convertNumToCoord(to));

        }
        return hash;

    }

    public long hash(Board board) {
        long hash = 0L;

        for (int i = 0; i < 64; i++) {
            char piece = board.getPiece(i);
            switch (piece) {
                case '_':
                    hash ^= AttackSets.randomNumbers[i][12];
                    break;
                case 'P':
                    hash ^= AttackSets.randomNumbers[i][0];
                    break;
                case 'N':
                    hash ^= AttackSets.randomNumbers[i][1];
                    break;
                case 'B':
                    hash ^= AttackSets.randomNumbers[i][2];
                    break;
                case 'R':
                    hash ^= AttackSets.randomNumbers[i][3];
                    break;
                case 'Q':
                    hash ^= AttackSets.randomNumbers[i][4];
                    break;
                case 'K':
                    hash ^= AttackSets.randomNumbers[i][5];
                    break;
                case 'p':
                    hash ^= AttackSets.randomNumbers[i][6];
                    break;
                case 'n':
                    hash ^= AttackSets.randomNumbers[i][7];
                    break;
                case 'b':
                    hash ^= AttackSets.randomNumbers[i][8];
                    break;
                case 'r':
                    hash ^= AttackSets.randomNumbers[i][9];
                    break;
                case 'q':
                    hash ^= AttackSets.randomNumbers[i][10];
                    break;
                case 'k':
                    hash ^= AttackSets.randomNumbers[i][11];
                    break;

            }
        }
        return hash;
    }


}
