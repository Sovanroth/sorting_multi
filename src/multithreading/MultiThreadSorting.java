package multithreading;

import utils.ParallelMergeSortUtil;

import java.util.Arrays;

public class MultiThreadSorting {

    private final int[] array;
    private final int[] sortedArray;
    private final int numThreads;

    public MultiThreadSorting(int[] inputArray) {
        this.array = Arrays.copyOf(inputArray, inputArray.length);
        this.sortedArray = new int[inputArray.length];
        this.numThreads = Runtime.getRuntime().availableProcessors();
    }

    public void sort() {
        System.out.println("=== Multi Thread Sorting (Using " + numThreads + " Cores) ===");

        // can command when test speed benchmark
        System.out.println("Original Array: " + Arrays.toString(array));
        //==================================================

        long startTime = System.nanoTime();

        // Copy array for sorting
        int[] workArray = Arrays.copyOf(array, array.length);

        // sort array
        ParallelMergeSortUtil.sortArray(workArray, numThreads);

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // can command when test speed benchmark
        System.arraycopy(workArray, 0, sortedArray, 0, workArray.length);
        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));

        //===================================================
        System.out.println("Threads used: " + numThreads);
        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " ms");
    }

    public int[] getSortedArray() {
        return sortedArray;
    }
}
