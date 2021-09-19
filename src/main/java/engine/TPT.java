package engine;

import java.util.HashMap;

public class TPT {
    private int nextIndex = 0;
    public long[] hashes;
    public boolean filled = false;
    public HashMap<Long, TPTEntry> entries;
    boolean debug = true;

    public TPT(int size) {
        hashes = new long[size];
        entries = new HashMap<>();
    }

    public TPT() {
        hashes = new long[1000000];
        entries = new HashMap<>();
    }

    public enum EntryType{
        PVNODE, CUTNODE, ALLNODE, NONE
    }

    public class TPTEntry {
        public long hash;
        public double score;
        public int depth;
        public int refCount = 1;
        public Move bestMove;
        public Board board;
        public EntryType nodeType; //0 = PV
        public int playerToMove;

        public TPTEntry(long hash, double score, int depth, Move bestMove, Board board, EntryType type, int playerToMove) {
            this.hash = hash;
            this.score = score;
            this.depth = depth;
            this.bestMove = bestMove;
            this.board = new Board(board);
            this.nodeType = type;
            this.playerToMove = playerToMove;
        }
    }

    public synchronized void put(long hash, double score, int depth, Move bestMove, Board board, EntryType type, int playerToMove) {

        TPTEntry newEntry = entries.get(hash);
        if (newEntry != null) {
            updateEntry(newEntry, hash, score, depth, ++newEntry.refCount, bestMove, board, type, playerToMove);
            if (filled) {
                TPTEntry oldEntry = entries.get(hashes[nextIndex]);
                if (--oldEntry.refCount == 0) {
                    entries.remove(hashes[nextIndex]);
                }
            }
        } else if (filled) {
            TPTEntry entry = entries.get(hashes[nextIndex]);
            if (--entry.refCount == 0) {
                entries.remove(hashes[nextIndex]);
                entries.put(hash, this.updateEntry(entry, hash, score, depth, 1, bestMove, board, type, playerToMove));
            } else {
                entries.put(hash, new TPTEntry(hash, score, depth, bestMove, board, type, playerToMove));
            }
        } else {
            entries.put(hash, new TPTEntry(hash, score, depth, bestMove, board, type, playerToMove));
        }


        hashes[nextIndex] = hash;

        if (nextIndex < (hashes.length - 1)) {
            nextIndex++;
        } else {
            nextIndex = 0;
            filled = true;
        }
    }

    private synchronized TPTEntry updateEntry(TPTEntry entryToBeUpdated, long newHash, double newScore, int newDepth, int newRefCount, Move bestMove, Board board, EntryType type, int playerToMove) {
        entryToBeUpdated.hash = newHash;
        entryToBeUpdated.depth = newDepth;
        entryToBeUpdated.score = newScore;
        entryToBeUpdated.refCount = newRefCount;
        entryToBeUpdated.bestMove = bestMove;
        entryToBeUpdated.board = board;
        entryToBeUpdated.nodeType = type;
        entryToBeUpdated.playerToMove = playerToMove;
        return entryToBeUpdated;
    }

    public synchronized TPTEntry get(long hash) {
        return entries.get(hash);
    }

    public synchronized boolean containsKey(long hash) {
        return entries.containsKey(hash);
    }

    public long updateHash(Board board, long hash, int from, int to) {
        char fromPiece = board.getPiece(from);
        char toPiece = board.getPiece(to);
        int fromPieceIndex;

        int fromColumn = from % 8;
        int toColumn = to % 8;
        if((fromPiece == 'P' || fromPiece == 'p') && toPiece == '_' && fromColumn != toColumn) { //enpassant
            board.makeMove(from, to);
            return hash(board);
        }else if((fromPiece == 'K' || fromPiece == 'k')){
            if((from == 4 && to == 2) || (from == 4 && to == 6) || (from == 60 && to == 58) || (from == 60 && to == 62)){
                board.makeMove(from, to);
                return hash(board);
            }
        }

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

        if(!board.castleWKValid){
            hash ^= AttackSets.randCastleNumbers[0];
        }else if(!board.castleWQValid){
            hash ^= AttackSets.randCastleNumbers[1];
        }else if(!board.castleBKValid){
            hash ^= AttackSets.randCastleNumbers[2];
        }else if(!board.castleBQValid){
            hash ^= AttackSets.randCastleNumbers[3];
        }
        return hash;
    }

}
