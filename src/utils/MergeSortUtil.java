package utils;

/**
 * MERGE SORT ALGORITHM
 */
public class MergeSortUtil {

    /**
     * Recursively sorts an array using the merge sort algorithm.
     * @param arr the array to be sorted
     * @param left left  the starting index of the subarray
     * @param right the ending index of the subarray
     */
    public void mergeSort(int[] arr, int left, int right) {

        // check if left >= right, the subarray has 0 or 1 element (already sorted)
        if (left < right) {

            // Find the middle point to divide the array into two parts
            int mid = left + (right - left) / 2;

            // Recursively sort the left half
            mergeSort(arr, left, mid);

            // Recursively sort the right half
            mergeSort(arr, mid + 1, right);

            // Merge the two sorted right and left
            merge(arr, left, mid, right);
        }
    }

    /**
     * Merges two sorted subarrays into one sorted subarray.
     * Left subarray: arr[left...mid]
     * Right subarray: arr[mid+1...right]
     * @param arr the array containing both subarrays
     * @param left the starting index of the left subarray
     * @param mid the ending index of the left subarray
     *  @param right the ending index of the right subarray
     */
    public void merge(int[] arr, int left, int mid, int right) {

        // Calculate sizes of the two subarrays to be merged
        int sizeArray1 = mid - left + 1;
        int sizeArray2 = right - mid;

        // Create temporary arrays to hold the left and right subarrays
        int[] leftArray = new int[sizeArray1];
        int[] rightArray = new int[sizeArray2];

        // Copy data from original array to temporary arrays
        System.arraycopy(arr, left, leftArray, 0, sizeArray1);
        System.arraycopy(arr, mid + 1, rightArray, 0, sizeArray2);

        int i = 0, j = 0, k = left;

        // Merge the temporary arrays back into arr[left...right]
        // Compare elements from both arrays and place the smaller one first
        while (i < sizeArray1 && j < sizeArray2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                j++;
            }
            k++;
        }

        // Copy any remaining elements from leftArray
        while (i < sizeArray1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }

        // Copy any remaining elements from rightArray
        while (j < sizeArray2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
