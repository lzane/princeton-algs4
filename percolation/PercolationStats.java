import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final int trails;
    private final int n;
    private final double[] res;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        this.trails = trials;
        this.n = n;
        this.res = new double[trails];
        for (int i = 0; i < trails; i++) {
            this.res[i] = 0;
        }
        run();
    }

    private void run() {
        for (int i = 0; i < this.trails; i++) {
            Percolation perc = new Percolation(n);
            while (!perc.percolates()) {
                int rowToOpen = (int) (StdRandom.uniform() * n + 1);
                int colToOpen = (int) (StdRandom.uniform() * n + 1);
                perc.open(rowToOpen, colToOpen);
            }

            res[i] = (double) perc.numberOfOpenSites() / (n * n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(res);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(res);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(trails);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(trails);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trails = Integer.parseInt(args[1]);

        if (n <= 0 || trails <= 0) {
            throw new IllegalArgumentException("invalid arguments");
        }

        PercolationStats stats = new PercolationStats(n, trails);

        StdOut.printf("mean                    = %f\n", stats.mean());
        StdOut.printf("stddev                  = %f\n", stats.stddev());
        StdOut.printf("95%% confidence interval = [%f, %f]\n", stats.confidenceLo(),
                      stats.confidenceHi());
    }

}
