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
        AttackSets.init();
    }

    @Test
    public void testPutUntilMaxSize(){
        for(long i = 0; i < 10; i++){
            tpt.put(i, i, 5, null, null, null);
        }

        for(long i = 10; i < 16; i++){
            tpt.put(i, i, 5, null, null, null);
        }

        tpt.put(10, 10, 5, null, null, null);
        assertTrue(tpt.get(tpt.hashes[0]).hash == 10);
    }

    @Test
    public void test(){
        for(long i = 0; i < 10; i++){
            tpt.put(i, i, 5, null, null, null);
        }

        tpt.put(10, 10, 5, null, null, null);
        for(long i = 11; i < 16; i++){
            tpt.put(i, i, 5, null, null, null);
        }

        tpt.put(10, 10, 5, null, null, null);

        System.out.println("RefCount: " + tpt.get(10).refCount);
        assertTrue(tpt.get(10).refCount == 2);
    }

    @Test
    public void testEntryValidity(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, tpt);
        PrincipalVariation pv = new PrincipalVariation();
        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, 0L, false);
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
        Engine engine2 = new Engine("WHITE", board, tpt2);
        engine2.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, 0L, false);
        assertTrue(tpt2.containsKey(hash));
        assertTrue(tpt2.get(hash).score == score1);
    }

    @Test
    public void testEntryValidity2(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, tpt);
        PrincipalVariation pv = new PrincipalVariation();
        PrincipalVariation pv2 = new PrincipalVariation();
        PrincipalVariation pv3 = new PrincipalVariation();

        double result3 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv3, 0, 0L, false);
        engine.alphaBetaMin(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, 0L, false);

        double result2 = engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, 0L, false);

        for(int i = 0; i < pv2.size(); i++){
            System.out.println("pv2: " + Util.convertNumToCoord(pv3.get(i).from) + Util.convertNumToCoord(pv3.get(i).to));
            System.out.println("pv3: " + Util.convertNumToCoord(pv2.get(i).from) + Util.convertNumToCoord(pv2.get(i).to));
        }

        for(int i = 0; i < pv.size(); i++){
            //assertTrue(pv3.get(i).from == pv2.get(i).from);
            //assertTrue(pv3.get(i).to == pv2.get(i).to);
            assertTrue(result3 == result2);
        }
    }

    @Test
    public void testEntryValidity3(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, tpt);
        PrincipalVariation pv = new PrincipalVariation();
        PrincipalVariation pv2 = new PrincipalVariation();

        for(int i = 0; i < 5; i++){
            engine.alphaBetaMax(board, i, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, 0L, false);
        }

        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, 0L, false);

        for(int i = 0; i < pv.size(); i++){
            assertTrue(pv.get(i).from == pv2.get(i).from);
            assertTrue(pv.get(i).to == pv2.get(i).to);
        }
    }

    @Test
    public void testPVOrdering(){
        Board board = new Board();
        TPT tpt = new TPT(300000);
        Engine engine = new Engine("WHITE", board, tpt);
        PrincipalVariation pv = new PrincipalVariation();
        PrincipalVariation pv2 = new PrincipalVariation();

        engine.alphaBetaMax(board, 4, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv, 0, 0L, false);
        board.makeMove(pv.get(pv.size()-1).from, pv.get(pv.size()-1).to);
        engine.alphaBetaMin(board, 3, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, pv2, 0, 0L, false);
        long hash = tpt.hash(board);
        TPT.TPTEntry entry = tpt.get(hash);

        assertTrue(pv2.get(pv2.size()-1).from == entry.bestMove.from);
        assertTrue(pv2.get(pv2.size()-1).to == entry.bestMove.to);

    }




}
