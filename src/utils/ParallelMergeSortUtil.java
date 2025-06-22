package utils;

import java.util.concurrent.RecursiveAction;

public class ParallelMergeSortUtil extends RecursiveAction {

    private final int[] array;
    private final int left;
    private final int right;
    private final int maxThreads;
    private static final int THRESHOLD = 1000; // Switch to sequential below this size

    public ParallelMergeSortUtil(int[] array, int left, int right, int maxThreads) {
        this.array = array;
        this.left = left;
        this.right = right;
        this.maxThreads = maxThreads;
    }

    @Override
    protected void compute() {
        if (right - left < THRESHOLD || maxThreads <= 1) {
            // Use sequential merge sort for small arrays or when threads are limited
            sequentialMergeSort(array, left, right);
        } else {
            // Divide the work
            int mid = left + (right - left) / 2;

            // Create subtasks
            ParallelMergeSortUtil leftTask = new ParallelMergeSortUtil(array, left, mid, maxThreads / 2);
            ParallelMergeSortUtil rightTask = new ParallelMergeSortUtil(array, mid + 1, right, maxThreads / 2);

            // Execute in parallel
            invokeAll(leftTask, rightTask);

            // Merge the results
            merge(array, left, mid, right);
        }
    }

    private void sequentialMergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sequentialMergeSort(arr, left, mid);
            sequentialMergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
