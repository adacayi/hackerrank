import java.util.Arrays;
import java.util.Scanner;

public class _3DSurfaceArea {
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, j, h = scanner.nextInt(), w = scanner.nextInt(), total = 0, height;
            int[][] array = new int[h][];
            scanner.nextLine();

            for (i = 0; i < h; i++)
                array[i] = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            for (i = 0; i < h; i++)
                for (j = 0; j < w; j++) {
                    if ((height = array[i][j]) == 0)
                        continue;

                    total += 2;

                    if (j == 0)
                        total += height;
                    else
                        total += Math.max(0, height - array[i][j - 1]);
                    if (i == 0)
                        total += height;
                    else
                        total += Math.max(0, height - array[i - 1][j]);
                    if (j == w - 1)
                        total += height;
                    else
                        total += Math.max(0, height - array[i][j + 1]);
                    if (i == h - 1)
                        total += height;
                    else
                        total += Math.max(0, height - array[i + 1][j]);
                }

            System.out.println(total);
        }
    }
}
