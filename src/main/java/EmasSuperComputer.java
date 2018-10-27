import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EmasSuperComputer {
    public static void main(String... args) {
        int i, j, k, l, n, m, max = 0;
        Integer[] first, second;
        int[][] grid = getGrid();
        List<Integer[]> pluses = new ArrayList<>();
        getPlusSizes(grid);
        n = grid.length;
        m = grid[0].length;

        for (i = 0; i < n; i++)
            for (j = 0; j < m; j++)
                for (k = grid[i][j]; k > 0; k -= 2)
                    pluses.add(new Integer[]{k, i, j});

        pluses.sort(Comparator.comparingInt(x -> -x[0]));
        l = pluses.size();
        for (i = 0; i < l; i++) {
            first = pluses.get(i);
            for (j = i + 1; j < l; j++) {
                second = pluses.get(j);

                if (intersects(first[0], second[0], first[1], first[2], second[1], second[2]))
                    continue;

                max = Math.max(max, (2 * first[0] - 1) * (2 * second[0] - 1));
            }
        }

        System.out.println(max);
    }

    public static boolean intersects(int first, int second, int i, int j, int i1, int j1) {
        if (i == i1 && j == j1)
            return true;

        first = (first - 1) / 2;
        second = (second - 1) / 2;

        if (i == i1)
            return ((j < j1 && j + first >= j1 - second) || (j > j1 && j1 + second >= j - first));
        if (j == j1)
            return ((i < i1 && i + first >= i1 - second) || (i > i1 && i1 + second >= i - first));

        if (i >= i1 - second && i <= i1 + second && j1 >= j - first && j1 <= j + first)
            return true;

        return j >= j1 - second && j <= j1 + second && i1 >= i - first && i1 <= i + first;
    }

    public static int[][] getGrid() {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, j, n = scanner.nextInt(), m = scanner.nextInt();
            int[][] result = new int[n][];
            char[] row;
            scanner.nextLine();

            for (i = 0; i < n; i++) {
                row = scanner.nextLine().toCharArray();
                result[i] = new int[m];
                for (j = 0; j < m; j++)
                    result[i][j] = row[j] == 'G' ? 1 : 0;
            }

            return result;
        }
    }

    public static int[][] getPlusSizes(int[][] grid) {
        int i, j, n = grid.length, m = grid[0].length, count;
        for (i = 0; i < n; i++)
            for (j = 0; j < m; j++) {
                if (grid[i][j] == 0)
                    continue;

                for (count = 1; i - count >= 0 && i + count < n && j - count >= 0 && j + count < m; count++) {
                    if (grid[i - count][j] == 0 || grid[i + count][j] == 0 || grid[i][j - count] == 0 || grid[i][j + count] == 0)
                        break;
                }
                count--;
                grid[i][j] = 2 * count + 1;
            }

        return grid;
    }
}
