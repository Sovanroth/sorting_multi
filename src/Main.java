import utils.ComparingUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            int[] sizes = {100000000};

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