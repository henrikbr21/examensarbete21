package engine;

public class Move {
    final public int from;
    final public int to;
    public int score;
    public char fromPiece;
    public char toPiece;
    public boolean checking;

    public Move(int from, int to){
        this.from = from;
        this.to = to;
        this.fromPiece = 'U';
        this.toPiece = 'U';
        this.checking = false;
        this.score = 0;
    }

    public Move(int from, int to, char fromPiece, char toPiece, boolean checking){
        this.from = from;
        this.to = to;
        this.fromPiece = fromPiece;
        this.toPiece = toPiece;
        this.checking = checking;
        this.score = 0;
    }
}
