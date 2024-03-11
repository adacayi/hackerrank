package hackerrank;

import java.util.List;

public class ArrayManipulation {
    private ArrayManipulation() {
    }

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        var array = new long[n];

        for (var query : queries) {
            int a = query.get(0) - 1;
            int b = query.get(1) - 1;
            int k = query.get(2);
            array[a] += k;

            if (b < n - 1) {
                array[b + 1] -= k;
            }
        }

        long max = 0L;
        long current = 0L;

        for (int i = 0; i < n; i++) {
            current += array[i];

            if (current > max) {
                max = current;
            }
        }

        return max;
    }
}
