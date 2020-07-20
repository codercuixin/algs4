package fundementals;

/**
 * * @Author: cuixin
 * * @Date: 2020/6/28 13:40
 */
public class VisualAccumulator {
    private double total;
    private int N;
    public VisualAccumulator(int trials, double max){
        StdDraw.setXscale(0, trials);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(.005);
    }
    public void addDataValue(double val){
        N++;
        total += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, total/N);
    }

    /**
     * Returns the mean of the data values.
     * @return the mean of the data values
     */
    public double mean() {
        return total/N;
    }

    /**
     * Returns the sample variance of the data values.
     * @return the sample variance of the data values
     */
    public double var() {
        if (N <= 1) return Double.NaN;
        return total / (N - 1);
    }

    /**
     * Returns the sample standard deviation of the data values.
     * @return the sample standard deviation of the data values
     */
    public double stddev() {
        return Math.sqrt(this.var());
    }

    public String toString() {
        return "N = " + N + ", mean = " + mean() + ", stddev = " + stddev();
    }

}
