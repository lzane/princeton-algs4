public class Percolation {
    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must greater than one");
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        return;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return true;

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return true;

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return 1;

    }

    // does the system percolate?
    public boolean percolates() {
        return true;

    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.print("hello");
    }
}
