package multithreading;

import utils.ParallelMergeSortUtil;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

public class MultiThreadSorting {

    private int[] array;
    private int[] sortedArray;
    private final int numThreads;

    public MultiThreadSorting(int[] inputArray) {
        this.array = Arrays.copyOf(inputArray, inputArray.length);
        this.sortedArray = new int[inputArray.length];
        this.numThreads = Runtime.getRuntime().availableProcessors();
    }

    public long sort() throws InterruptedException {
        System.out.println("=== Multi Thread Sorting (Using All CPU Cores) ===");
        System.out.println("Available CPU Cores: " + numThreads);
//        System.out.println("Original Array: " + Arrays.toString(array));
        System.out.println("Main Thread: " + Thread.currentThread().getName());

        long startTime = System.nanoTime();

        // Use ForkJoinPool to utilize all available cores
        ForkJoinPool forkJoinPool = new ForkJoinPool(numThreads);

        try {
            // Copy array for sorting
            int[] workArray = Arrays.copyOf(array, array.length);

            // Create and execute parallel merge sort task
            ParallelMergeSortUtil task = new ParallelMergeSortUtil(workArray, 0, workArray.length - 1, numThreads);
            forkJoinPool.invoke(task);

            // Copy sorted result
            System.arraycopy(workArray, 0, sortedArray, 0, workArray.length);

        } finally {
            forkJoinPool.shutdown();
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

//        System.out.println("Final sorted array: " + Arrays.toString(sortedArray));
        System.out.println("Threads used: " + numThreads);
        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " ms");
        System.out.println();

        return executionTime;
    }

    public int[] getSortedArray() {
        return sortedArray;
    }

    public int getThreadsUsed() {
        return numThreads;
    }
}
