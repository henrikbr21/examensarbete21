package engine;

import java.util.ArrayList;

public class MeasurementData {
    private int[] PVHits;
    private int[] CUTHits;
    private int[] ALLHits;
    private int[] bestMoveIndex;
    private int[] executionTime;

    public MeasurementData(){
        PVHits = new int[6];
        CUTHits = new int[6];
        ALLHits = new int[6];
        bestMoveIndex = new int[6];
        executionTime = new int[6];
    }

    public void incrementPVHits(int depth){
        PVHits[depth]++;
    }

    public void incrementCUTHits(int depth){
        CUTHits[depth]++;
    }

    public void incrementALLHits(int depth){
        ALLHits[depth]++;
    }

    public void setBestMoveIndex(int depth, int index){
        bestMoveIndex[depth] = index;
    }

    public void setExecutionTime(int depth, int time){
        executionTime[depth] = time;
    }
}
