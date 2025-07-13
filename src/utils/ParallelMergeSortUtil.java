package utils;

/**
 * ParallelMergeSortUtil extend class thread able to sort array with multi-threading
 */
public class ParallelMergeSortUtil extends Thread {

    private static final MergeSortUtil mergeSortUtil = new MergeSortUtil();

    private final int[] array;
    private final int left;
    private final int right;
    private final int maxThreads;
    private static final int THRESHOLD = 1000;

    public ParallelMergeSortUtil(int[] array, int left, int right, int maxThreads) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.maxThreads = maxThreads;
    }

    @Override
    public void run() {
        mergeSort(array, left, right, maxThreads);
    }

    /**
     * Recursive merge sort method
     * @param arr the array to be sorted
     * @param left the starting index
     * @param right the ending index
     * @param threads the number of threads available for this operation
     */
    private void mergeSort(int[] arr, int left, int right, int threads) {

        // return if array already sort
        if (left >= right) {
            return;
        }

        // Use sequential sorting if array is small or threads are limited
        if (right - left < THRESHOLD || threads <= 1) {
            sequentialMergeSort(arr, left, right);
            return;
        }

        // Divide the array into two arrays
        int mid = left + (right - left) / 2;

        // Create threads for parallel processing of left and right array
        ParallelMergeSortUtil leftThread = new ParallelMergeSortUtil(arr, left, mid, threads / 2);
        ParallelMergeSortUtil rightThread = new ParallelMergeSortUtil(arr, mid + 1, right, threads / 2);

        // Start both threads to merge left and right
        leftThread.start();
        rightThread.start();

        try {

            leftThread.join();
            rightThread.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // After both arrays are sorted, merge together
        mergeSortUtil.merge(arr, left, mid, right);
    }

    /**
     * Sequential merge sort implementation used for small arrays or when threads are limited.
     * Falls back to standard recursive approach to avoid thread overhead.
     * @param arr   the array to be sorted
     * @param left  the starting index
     * @param right the ending index
     */
    private void sequentialMergeSort(
            int[] arr,
            int left,
            int right
    ) {

        // Base case: arrays with 0 or 1 element are already sorted
        if (left < right) {

            // Find the middle point of array
            int mid = left + (right - left) / 2;

            // Recursively sort both array
            sequentialMergeSort(arr, left, mid);
            sequentialMergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            mergeSortUtil.merge(arr, left, mid, right);
        }
    }

    /**
     * Method to start the parallel merge sort process.
     * @param array the array to be sorted
     * @param maxThreads the maximum number of threads to use
     */
    public static void sortArray(
            int[] array,
            int maxThreads
    ) {
        if (
                array == null ||
                array.length <= 1
        ) {
            return;
        }

        // Create and start the main sorting thread
        ParallelMergeSortUtil mainThread =
                new ParallelMergeSortUtil(array, 0, array.length - 1, maxThreads);
        mainThread.start();

        try {
            mainThread.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}