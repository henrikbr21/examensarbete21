package engine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used solely for the purpose of measuring the performance of the engine.
 */

public class MeasurementData {
    private List<int[]> TPHits;
    private List<int[]> PVHits;
    private List<int[]> CUTHits;
    private List<int[]> ALLHits;
    private List<int[]> nodes;
    private List<double[]> bestMoveIndex;
    private List<long[]> executionTime;
    private List<Long> gcCollectionTime;
    private List<Long> gcCollectionCount;
    private List<long[]> memoryUsage;
    private List<Double> scores;
    private double[] avgTPHits;
    private double[] avgPVHits;
    private double[] avgCUTHits;
    private double[] avgALLHits;
    private double[] avgBestMoveIndex;
    private long[] avgExecutionTime;
    private long avgGCCollectionTime;
    private long avgGCCollectionCount;
    private long[] avgMemoryUsage;

    public MeasurementData() {
        TPHits = new ArrayList<>(600);
        PVHits = new ArrayList<>(600);
        CUTHits = new ArrayList<>(600);
        ALLHits = new ArrayList<>(600);
        nodes = new ArrayList<>(600);
        bestMoveIndex = new ArrayList<>(600);
        executionTime = new ArrayList<>(600);
        gcCollectionTime = new ArrayList<>(600);
        gcCollectionCount = new ArrayList<>(600);
        memoryUsage = new ArrayList<>(600);
        scores = new ArrayList<Double>(600);
        for (int i = 0; i < 600; i++) {
            TPHits.add(i, new int[7]);
            PVHits.add(i, new int[7]);
            CUTHits.add(i, new int[7]);
            ALLHits.add(i, new int[7]);
            int[] add = new int[7];
            for (int j = 0; j < 7; j++) {
                add[j] = 0;
            }
            double[] add2 = new double[7];
            for (int j = 0; j < 7; j++) {
                add2[j] = 0;
            }
            bestMoveIndex.add(i, add2);
            nodes.add(i, add);
            memoryUsage.add(i, new long[3]);
            executionTime.add(i, new long[7]);
            gcCollectionTime.add(i, 0L);
            gcCollectionCount.add(i, 0L);
            scores.add(i, 0D);
            avgTPHits = new double[7];
            avgPVHits = new double[7];
            avgCUTHits = new double[7];
            avgALLHits = new double[7];
            avgBestMoveIndex = new double[7];
            avgExecutionTime = new long[7];
            avgMemoryUsage = new long[3];
        }
    }

    public void incrementTPHits(int searchNbr, int depth) {
        TPHits.get(searchNbr)[depth]++;
    }

    public void incrementPVHits(int searchNbr, int depth) {
        PVHits.get(searchNbr)[depth]++;
    }

    public void incrementCUTHits(int searchNbr, int depth) {
        CUTHits.get(searchNbr)[depth]++;
    }

    public void incrementALLHits(int searchNbr, int depth) {
        ALLHits.get(searchNbr)[depth]++;
    }

    public void incrementNodes(int searchNbr, int depth) {
        nodes.get(searchNbr)[depth]++;
    }

    public void addBestMoveIndex(int searchNbr, int depth, int index) {
        bestMoveIndex.get(searchNbr)[depth] += index;
        nodes.get(searchNbr)[depth]++;
    }

    public void setExecutionTime(int searchNbr, int depth, long time) {
        executionTime.get(searchNbr)[depth] = time;
    }

    public void setMemoryUsage(int searchNbr, int depth, long amountMemory) {
        long[] memory = memoryUsage.get(searchNbr);
        memory[depth] = amountMemory;
    }

    public void setGcCollectionTime(int searchNbr, long time) {
        gcCollectionTime.remove(searchNbr);
        gcCollectionTime.add(searchNbr, time);
    }

    public void setGcCollectionCount(int searchNbr, long count) {
        gcCollectionCount.remove(searchNbr);
        gcCollectionCount.add(searchNbr, count);
    }

    public void setScore(int searchNbr, double score) {
        scores.remove(searchNbr);
        scores.add(searchNbr, score);
    }

    private void calculateAverages(int nbrSearches) {
        for (int i = 0; i < nbrSearches; i++) {
            for (int j = 0; j < 7; j++) {
                avgTPHits[j] += TPHits.get(i)[j];
                avgPVHits[j] += PVHits.get(i)[j];
                avgCUTHits[j] += CUTHits.get(i)[j];
                avgALLHits[j] += ALLHits.get(i)[j];
                avgBestMoveIndex[j] += bestMoveIndex.get(i)[j];
                avgExecutionTime[j] += executionTime.get(i)[j];
            }
            for (int j = 0; j < 3; j++) {
                avgMemoryUsage[j] = memoryUsage.get(i)[j];
            }
            avgGCCollectionTime += gcCollectionTime.get(i);
            avgGCCollectionCount += gcCollectionCount.get(i);
        }

        int nbrNodes[] = new int[7];
        for (int i = 0; i < nbrSearches; i++) {
            for (int j = 0; j < 7; j++)
                nbrNodes[j] += nodes.get(i)[j];
        }

        for (int i = 0; i < 7; i++) {
            avgTPHits[i] /= nbrSearches;
            avgPVHits[i] /= nbrSearches;
            avgCUTHits[i] /= nbrSearches;
            avgALLHits[i] /= nbrSearches;
            avgExecutionTime[i] /= nbrSearches;
            avgBestMoveIndex[i] /= nbrNodes[i];
        }
        avgGCCollectionTime /= nbrSearches;
        avgGCCollectionCount /= nbrSearches;
    }

    private void averageBestMoveIndex(int nbrSearches) {
        for (int i = 0; i < nbrSearches; i++) {
            for (int j = 0; j < 7; j++) {
                if (bestMoveIndex.get(i)[j] != 0 && nodes.get(i)[j] != 0)
                    bestMoveIndex.get(i)[j] /= nodes.get(i)[j];
            }
        }
    }

    public void printToFile(int nbrSearches) {
        calculateAverages(nbrSearches);
        averageBestMoveIndex(nbrSearches);

        File file = new File("C:\\Users\\Gurkburk\\Desktop\\filename.txt");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("C:\\Users\\Gurkburk\\Desktop\\filename.csv");

            fw.write(
                    "Search,Score,executionTime_2,executionTime_4,executionTime_6,memoryUsage_2,memoryUsage_4,memoryUsage_6,bestMoveIndex_0,bestMoveIndex_1,bestMoveIndex_2,bestMoveIndex_3,bestMoveIndex_4,bestMoveIndex_5,TPHits_0,TPHits_1,TPHits_2,TPHits_3,TPHits_4,TPHits_5,TPHits_6,PVHits_0,PVHits_1,PVHits_2,PVHits_3,PVHits_4,PVHits_5,PVHits_6,CUTHits_0,CUTHits_1,CUTHits_2,CUTHits_3,CUTHits_4,CUTHits_5,CUTHits_6,ALLHits_0,ALLHits_1,ALLHits_2,ALLHits_3,ALLHits_4,ALLHits_5,ALLHits_6,gcCollectionTime,gcCollectionCount");
            fw.write("\n");

            //Averages
            fw.write("Average" + ",");

            fw.write("0,");
            for (int j = 2; j < 7; j += 2) {
                fw.write(avgExecutionTime[j] + ",");
            }

            for (int j = 0; j < 3; j++) {
                fw.write(avgMemoryUsage[j] + ",");
            }

            for (int j = 0; j < 6; j++) {
                fw.write(avgBestMoveIndex[j] + ",");
            }

            for (int j = 0; j < 7; j++) {
                fw.write(avgTPHits[j] + ",");
            }

            for (int j = 0; j < 7; j++) {
                fw.write(avgPVHits[j] + ",");
            }
            for (int j = 0; j < 7; j++) {
                fw.write(avgCUTHits[j] + ",");
            }
            for (int j = 0; j < 7; j++) {
                fw.write(avgALLHits[j] + ",");
            }
            fw.write(Long.toString(avgGCCollectionTime) + ",");
            fw.write(Long.toString(avgGCCollectionCount) + ",");
            fw.write("\n");

            //Individual searches
            for (int i = 0; i < nbrSearches; i++) {
                fw.write(i + ",");

                fw.write(Double.toString(scores.get(i)) + ",");

                for (int j = 2; j < 7; j += 2) {
                    fw.write(executionTime.get(i)[j] + ",");
                }

                for (int j = 0; j < 3; j++) {
                    fw.write(memoryUsage.get(i)[j] + ",");
                }

                for (int j = 0; j < 6; j++) {
                    fw.write(bestMoveIndex.get(i)[j] + ",");
                }

                for (int j = 0; j < 7; j++) {
                    fw.write(TPHits.get(i)[j] + ",");
                }

                for (int j = 0; j < 7; j++) {
                    fw.write(PVHits.get(i)[j] + ",");
                }
                for (int j = 0; j < 7; j++) {
                    fw.write(CUTHits.get(i)[j] + ",");
                }
                for (int j = 0; j < 7; j++) {
                    fw.write(ALLHits.get(i)[j] + ",");
                }

                fw.write(Long.toString(gcCollectionTime.get(i)) + ",");
                fw.write(Long.toString(gcCollectionCount.get(i)) + ",");
                fw.write("\n");
            }


            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

