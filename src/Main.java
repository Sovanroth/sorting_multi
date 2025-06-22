import utils.ComparingUtil;

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            int[] sizes = { 10000000, 100000000 };

            for (int size : sizes) {
                System.out.println("\n============SORTING " + size + " Array ============");
                ComparingUtil.performComparison(size);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}