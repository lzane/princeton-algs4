import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf_bw;
    private final boolean[] op;
    private final int n;
    private final int bottom;
    private int count = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must greater than one");
        }
        this.n = n;
        int total = n * n + 2;
        this.bottom = total - 1;
        this.uf = new WeightedQuickUnionUF(total);
        this.uf_bw = new WeightedQuickUnionUF(total);
        this.op = new boolean[total];
        for (int i = 0; i < total; i++) {
            this.op[i] = false;
        }
    }

    // validate row and col
    private void validate(int row, int col) {
        if (row < 1 || col < 1 || row > this.n || col > this.n) {
            throw new IllegalArgumentException("row or col outside its prescribed range");
        }
    }

    private int getIndex(int row, int col) {
        return (row - 1) * this.n + col;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        this.validate(row, col);
        if (!this.isOpen(row, col)) {
            int index = this.getIndex(row, col);
            this.op[index] = true;
            this.count++;

            // first row to virtual node
            if (row == 1) {
                uf.union(TOP, index);
                uf_bw.union(TOP, index);
            }

            // last row to virtual node
            if (row == n) {
                uf.union(bottom, index);
            }

            // top
            if (row > 1 && this.isOpen(row - 1, col)) {
                uf.union(getIndex(row - 1, col), index);
                uf_bw.union(getIndex(row - 1, col), index);
            }

            // bottom
            if (row < n && this.isOpen(row + 1, col)) {
                uf.union(getIndex(row + 1, col), index);
                uf_bw.union(getIndex(row + 1, col), index);
            }

            // left
            if (col > 1 && this.isOpen(row, col - 1)) {
                uf.union(getIndex(row, col - 1), index);
                uf_bw.union(getIndex(row, col - 1), index);
            }

            // right
            if (col < n && this.isOpen(row, col + 1)) {
                uf.union(getIndex(row, col + 1), index);
                uf_bw.union(getIndex(row, col + 1), index);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        this.validate(row, col);
        return this.op[this.getIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        this.validate(row, col);
        return uf_bw.connected(TOP, getIndex(row, col));

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return this.count;

    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(TOP, bottom);

    }

    // test client (optional)
    public static void main(String[] args) {
        System.out.print("hello");
    }
}
