package utils;

import multithreading.MultiThreadSorting;
import singlethreading.SingleThreadSorting;

import java.util.Arrays;
import java.util.Random;

public class ComparingUtil {

    public static void performComparison(int size) {
        // Generate random array
        int[] originalArray = generateRandomArray(size);

        try {
            // Single threaded sorting
            SingleThreadSorting singleSort = new SingleThreadSorting(originalArray);
            long singleTime = singleSort.sort();

            // Multi threaded sorting
            MultiThreadSorting multiSort = new MultiThreadSorting(originalArray);
            long multiTime = multiSort.sort();

            // Verify both results are correct and identical
            if (!Arrays.equals(singleSort.getSortedArray(), multiSort.getSortedArray())) {
                System.err.println("ERROR: Sorting results don't match!");
            }

        } catch (InterruptedException e) {
            System.err.println("Thread interrupted: " + e.getMessage());
        }
    }

    public static int[] generateRandomArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducible results
        int[] array = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(1000);
        }

        return array;
    }
}
