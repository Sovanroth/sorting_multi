package singlethreading;

import utils.MergeSortUtil;

import java.util.Arrays;

public class SingleThreadSorting {

    MergeSortUtil mergeSortUtil = new MergeSortUtil();

    private final int[] array;
    private int[] sortedArray;

    public SingleThreadSorting(int[] inputArray) {
        this.array = Arrays.copyOf(inputArray, inputArray.length);
        this.sortedArray = new int[inputArray.length];
    }

    public void sort() {
        System.out.println("=== Single Thread Sorting ===");

        long startTime = System.nanoTime();

        // Use merge sort algorithm
        mergeSortUtil.mergeSort(array, 0, array.length - 1);
        sortedArray = Arrays.copyOf(array, array.length);

//        System.out.println("Original Array: " + Arrays.toString(array));
//        System.out.println("Sorted Array: " + Arrays.toString(sortedArray));

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " ms");

    }

    public int[] getSortedArray() {
        return sortedArray;
    }
}
