package engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

public class MeasurementData {
    private List<int[]> TPHits;
    private List<int[]> PVHits;
    private List<int[]> CUTHits;
    private List<int[]> ALLHits;
    private List<int[]> bestMoveIndex;
    private List<long[]>executionTime;
    private List<Long> gcCollectionTime;
    private List<Long> gcCollectionCount;
    private List<long[]> memoryUsage;
    private List<Double> scores;

    public MeasurementData(){
        TPHits = new ArrayList<>(500);
        PVHits = new ArrayList<>(500);
        CUTHits = new ArrayList<>(500);
        ALLHits = new ArrayList<>(500);
        bestMoveIndex = new ArrayList<>(500);
        executionTime = new ArrayList<>(500);
        gcCollectionTime = new ArrayList<>(500);
        gcCollectionCount = new ArrayList<>(500);
        memoryUsage = new ArrayList<>(500);
        scores = new ArrayList<Double>(500);
        for(int i = 0; i < 500; i++){
            TPHits.add(i, new int[7]);
            PVHits.add(i, new int[7]);
            CUTHits.add(i, new int[7]);
            ALLHits.add(i, new int[7]);
            int[] add = new int[7];
            for(int j = 0; j < 7; j++){
                add[j] = -1;
            }
            bestMoveIndex.add(i, add);
            memoryUsage.add(i, new long[3]);
            executionTime.add(i, new long[7]);
            gcCollectionTime.add(i, 0L);
            gcCollectionCount.add(i, 0L);
            scores.add(i, 0D);
        }
    }

    public void incrementTPHits(int searchNbr, int depth){
        TPHits.get(searchNbr)[depth]++;
    }

    public void incrementPVHits(int searchNbr, int depth){
        PVHits.get(searchNbr)[depth]++;
    }

    public void incrementCUTHits(int searchNbr, int depth){
        CUTHits.get(searchNbr)[depth]++;
    }

    public void incrementALLHits(int searchNbr, int depth){
        ALLHits.get(searchNbr)[depth]++;
    }

    public void setBestMoveIndex(int searchNbr, int depth, int index){
        bestMoveIndex.get(searchNbr)[depth] = index;
    }

    public void setExecutionTime(int searchNbr, int depth, long time){
        executionTime.get(searchNbr)[depth] = time;
    }

    public void setMemoryUsage(int searchNbr, int depth, long amountMemory){
        long[] memory = memoryUsage.get(searchNbr);
        memory[depth] = amountMemory;
    }

    public void setGcCollectionTime(int searchNbr, long time){
        gcCollectionTime.remove(searchNbr);
        gcCollectionTime.add(searchNbr, time);
    }
    public void setGcCollectionCount(int searchNbr, long count){
        gcCollectionCount.remove(searchNbr);
        gcCollectionCount.add(searchNbr, count);
    }

    public void setScore(int searchNbr, double score){
        scores.remove(searchNbr);
        scores.add(searchNbr, score);
    }

    public void printToFile(){
        File file = new File("C:\\Users\\Gurkburk\\Desktop\\filename.txt");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("C:\\Users\\Gurkburk\\Desktop\\filename.csv");
            for(int i = 0; i < 10; i++){
                fw.write("Search_" + i);
                fw.write("\n");

                for(int j = 0; j < 7; j++){
                    fw.write("TPHits;" + TPHits.get(i)[j]);
                    fw.write("\n");
                }
                for(int j = 0; j < 7; j++){
                    fw.write("PVHits;" + PVHits.get(i)[j]);
                    fw.write("\n");
                }
                for(int j = 0; j < 7; j++){
                    fw.write("CUTHits;" + CUTHits.get(i)[j]);
                    fw.write("\n");
                }
                for(int j = 0; j < 7; j++){
                    fw.write("ALLHits;" + ALLHits.get(i)[j]);
                    fw.write("\n");
                }
                for(int j = 0; j < 6; j++){
                    fw.write("bestMoveIndex;" + bestMoveIndex.get(i)[j]);
                    fw.write("\n");
                }
                for(int j = 2; j < 7; j+=2){
                    fw.write("ExecutionTime;" + executionTime.get(i)[j]/1000);
                    fw.write("\n");
                }
                for(int j = 0; j < 3; j++){
                    fw.write("Memory usage: " + memoryUsage.get(i)[j]);
                    fw.write("\n");
                }
                fw.write("Score;" + Double.toString(scores.get(i)));
                fw.write("\n");
                fw.write("GC_collection_time;" + Long.toString(gcCollectionTime.get(i)));
                fw.write("\n");
                fw.write("GC_collection_count;" + Long.toString(gcCollectionCount.get(i)));
                fw.write("\n");
                fw.write("\n");

            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

