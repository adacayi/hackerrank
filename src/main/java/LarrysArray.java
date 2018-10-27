import java.util.Arrays;
import java.util.Scanner;

public class LarrysArray {
    public static boolean moveLeft(int[] a, int index, int times) {
        int tmp, move;
        if (times < 0 || index - times < 0)
            return false;

        if (times == 0)
            return true;

        if (times > 2)
            if (!moveLeft(a, index, move = Math.max(2, times - 2)))
                return false;
            else {
                times -= move;
                index -= move;
            }

        if (times == 2) {
            tmp = a[index];
            a[index] = a[index - 1];
            a[index - 1] = a[index - 2];
            a[index - 2] = tmp;
            return true;
        }

        if (index == a.length - 1)
            return false;

        tmp = a[index];
        a[index] = a[index + 1];
        a[index + 1] = a[index - 1];
        a[index - 1] = tmp;
        return true;
    }

    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, k, j, n, t = scanner.nextInt();
            int[] a, sorted;
            main: for (i = 0; i < t; i++) {
                n = scanner.nextInt();
                scanner.nextLine();
                a = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
                sorted = Arrays.copyOf(a, a.length);
                Arrays.sort(sorted);
                for (j = 0; j < n; j++) {
                    for (k = j; k < n && a[k] != sorted[j]; k++) ;
                    if (!moveLeft(a, k, k - j)) {
                        System.out.println("NO");
                        continue main;
                    }
                }
                System.out.println("YES");
            }
        }
    }
}
