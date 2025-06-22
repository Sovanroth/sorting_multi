package utils;

import multithreading.MultiThreadSorting;
import singlethreading.SingleThreadSorting;

import java.util.Arrays;
import java.util.Random;

public class ComparingUtil {

    public static void performComparison(int size) {

        int[] originalArray = generateRandomArray(size);

        SingleThreadSorting singleSort = new SingleThreadSorting(originalArray);

        MultiThreadSorting multiSort = new MultiThreadSorting(originalArray);

        // Verify both results are correct
        if (!Arrays.equals(singleSort.getSortedArray(), multiSort.getSortedArray())) {
            System.err.println("ERROR: Sorting results don't match!");
        }

    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random(50);
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }

        return array;
    }
}
