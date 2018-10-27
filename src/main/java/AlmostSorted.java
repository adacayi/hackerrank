import java.util.Arrays;
import java.util.Scanner;

public class AlmostSorted {
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, j, k, n = scanner.nextInt(), temp;
            int[] a, copy;
            scanner.nextLine();
            a = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
            copy = Arrays.copyOf(a, a.length);
            Arrays.sort(copy);
            for (i = 0; i < n && a[i] == copy[i]; i++) ;

            if (i == n) {
                System.out.println("yes");
                return;
            }

            for (j = i + 1; j < n && a[j] != copy[i]; j++) ;

            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            for (k = i + 1; k < n && a[k] == copy[k]; k++) ;

            if (k == n) {
                System.out.printf("yes\nswap %d %d\n", i + 1, j + 1);
                return;
            }

            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            for (k = i; k <= (j + i - 1) / 2; k++) {
                temp = a[k];
                a[k] = a[j - (k - i)];
                a[j - (k - i)] = temp;
            }

            for (k = i + 1; k < n && a[k] == copy[k]; k++) ;

            if (k == n) {
                System.out.printf("yes\nreverse %d %d\n", i + 1, j + 1);
                return;
            }

            System.out.println("no");
        }
    }
}
