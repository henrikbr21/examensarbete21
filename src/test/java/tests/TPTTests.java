package tests;

import static org.junit.Assert.*;
import engine.*;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;

public class TPTTests{
    private TPT tpt;

    @Before
    public void setUp(){
        tpt = new TPT(10);
    }

    @Test
    public void testPutUntilMaxSize(){
        for(long i = 0; i < 10; i++){
            tpt.put(i, i, 5);
        }

        for(long i = 10; i < 16; i++){
            tpt.put(i, i, 5);
        }

        tpt.put(10, 10, 5);
        assertTrue(tpt.get(tpt.hashes[0]).hash == 10);
    }

    @Test
    public void test(){
        for(long i = 0; i < 10; i++){
            tpt.put(i, i, 5);
        }

        tpt.put(10, 10, 5);
        for(long i = 11; i < 16; i++){
            tpt.put(i, i, 5);
        }

        tpt.put(10, 10, 5);

        System.out.println("RefCount: " + tpt.get(10).refCount);
        assertTrue(tpt.get(10).refCount == 2);
    }

    @Test
    public void testEntryValidity(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, false, tpt);
        ArrayList<Move> pv = new ArrayList<>();
        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
        assertTrue(pv.size()==4);

        char[][] testBoard = {
                {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
                {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {'P', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
                {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'}
        };
        Board board2 = new Board(testBoard);
        long hash = tpt.hash(board2);
        assertTrue(tpt.containsKey(hash));
        double score1 = tpt.get(hash).score;
        assertTrue(tpt.get(hash).depth == 3);

        TPT tpt2 = new TPT(300000);
        Engine engine2 = new Engine("WHITE", board, true, tpt2);
        engine2.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
        assertTrue(tpt2.containsKey(hash));
        assertTrue(tpt2.get(hash).score == score1);
    }

    @Test
    public void testEntryValidity2(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, false, tpt);
        ArrayList<Move> pv = new ArrayList<>();
        ArrayList<Move> pv2 = new ArrayList<>();

        engine.alphaBetaMin(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);

        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1, 0L);

        for(int i = 0; i < pv.size(); i++){
            assertTrue(pv.get(i).from == pv2.get(i).from);
            assertTrue(pv.get(i).to == pv2.get(i).to);
        }
    }

    @Test
    public void testEntryValidity3(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, false, tpt);
        ArrayList<Move> pv = new ArrayList<>();
        ArrayList<Move> pv2 = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            engine.alphaBetaMax(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, -1, 0L);
        }

        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, -1, 0L);

        for(int i = 0; i < pv.size(); i++){
            assertTrue(pv.get(i).from == pv2.get(i).from);
            assertTrue(pv.get(i).to == pv2.get(i).to);
        }
    }

}
