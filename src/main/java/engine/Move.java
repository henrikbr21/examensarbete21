package engine;

/**
 * This class represents a move in chess. The score attribute is used for move ordering according to MVV-LVA.
 */

public class Move {
    public int from;
    public int to;
    public char fromPiece;
    public char toPiece;
    public boolean checking;
    public int score;

    public Move(int from, int to) {
        this.from = from;
        this.to = to;
        this.fromPiece = 'U';
        this.toPiece = 'U';
        this.checking = false;
        this.score = 0;
    }

    public Move(int from, int to, char fromPiece, char toPiece, boolean checking, int score) {
        this.from = from;
        this.to = to;
        this.fromPiece = fromPiece;
        this.toPiece = toPiece;
        this.checking = checking;
        this.score = score;
    }

    public Move(Move move) {
        this.from = move.from;
        this.to = move.to;
        this.fromPiece = move.fromPiece;
        this.toPiece = move.toPiece;
        this.checking = move.checking;
        this.score = move.score;
    }

    public boolean equals(Move move) {
        return this.from == move.from && this.to == move.to;
    }

}

