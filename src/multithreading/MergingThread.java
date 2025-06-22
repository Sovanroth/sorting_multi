package multithreading;

public class MergingThread extends Thread {

    private int[] leftArray;
    private int[] rightArray;
    private int[] mergedArray;

    public MergingThread(int[] leftArray, int[] rightArray, int[] mergedArray, String name) {
        super(name);
        this.leftArray = leftArray;
        this.rightArray = rightArray;
        this.mergedArray = mergedArray;
    }

    @Override
    public void run() {
        System.out.println("Merging thread " + getName() + " started");
        merge();
        System.out.println("Merging thread " + getName() + " completed");
    }

    private void merge() {
        int i = 0, j = 0, k = 0;

        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                mergedArray[k] = leftArray[i];
                i++;
            } else {
                mergedArray[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < leftArray.length) {
            mergedArray[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < rightArray.length) {
            mergedArray[k] = rightArray[j];
            j++;
            k++;
        }
    }
}
