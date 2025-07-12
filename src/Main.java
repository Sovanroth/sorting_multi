import utils.ComparingUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            int[] sizes = {100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 500000000 };

            for (int size : sizes) {
                System.out.println("\n============ SORTING " + size + " Array ============");
                ComparingUtil.performComparison(size);
                System.out.println("\n====================================================");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}