import utils.ComparingUtil;

public class Main {

    public static void main(String[] args) {

        int[] sizes = { 10000000 };

        for (int size : sizes) {
            ComparingUtil.performComparison(size);
        }
    }
}