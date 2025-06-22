import utils.ComparingUtil;

public class Main {

    public static void main(String[] args) {

        ComparingUtil comparingUtil = new ComparingUtil();

        System.out.println("=== MULTITHREADED SORTING PERFORMANCE COMPARISON ===\n");

        // Test with different array sizes
        int[] sizes = {10000000};

//        System.out.printf("%-12s %-15s %-15s %-12s\n", "Array Size", "Single Thread", "Multi Thread", "Speedup");
//        System.out.println("--------------------------------------------------------");

        for (int size : sizes) {
            comparingUtil.performComparison(size);
        }

//        System.out.println("\n=== DETAILED EXAMPLE WITH SMALL ARRAY ===");
//        comparingUtil.demonstrateWithSmallArray();


    }
}