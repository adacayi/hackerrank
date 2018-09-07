import java.util.Scanner;

public class Java1DArrayPart2 {
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, j, k, l, leap, n = scanner.nextInt(), array[];

            for (i = 0; i < n; i++) {
                l = scanner.nextInt();
                leap = scanner.nextInt();
                array = new int[l];

                for (j = 0; j < l; j++)
                    array[j] = scanner.nextInt();

                array[0] = 2;

                if (leap >= l) {
                    System.out.println("YES");
                    continue;
                }

                if (leap == l - 1 && array[l - 1] == 0) {
                    System.out.println("YES");
                    continue;
                }

                for (j = 1; j < l - leap; j++) {
                    if (array[j] != 0)
                        continue;

                    if (array[j - 1] == 2) {
                        array[j] = 2;
                        continue;
                    }

                    if (j - leap < 0 || array[j - leap] != 2)
                        continue;

                    for (k = j; array[k] != 1 && k >= j - leap; k--)
                        array[k] = 2;
                }

                for (; j < l; j++) {
                    if (array[j] != 0)
                        continue;

                    if (array[j - 1] == 2) {
                        array[j] = 2;
                        break;
                    }

                    if (j - leap < 0 || array[j - leap] != 2)
                        continue;

                    break;
                }

                if (j < l || array[l - 1] == 2)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }
        }
    }
}
