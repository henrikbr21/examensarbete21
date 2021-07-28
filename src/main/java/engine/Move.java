package engine;

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
}

