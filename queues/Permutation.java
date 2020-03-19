/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            String line = StdIn.readString();
            queue.enqueue(line);
        }

        for (int i = 0; i < k; i++) {
            StdOut.printf("%s\n", queue.dequeue());
        }
    }
}
