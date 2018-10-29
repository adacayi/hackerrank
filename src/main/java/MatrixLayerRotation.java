import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatrixLayerRotation {
    public static int[][] rotate(int begin, int times, int[][] array) {
        if (array == null || array[0] == null)
            return array;

        int i, j, w = array[0].length, h = array.length, w1 = w - 2 * begin, h1 = h - 2 * begin,
                n = 2 * (w1 + h1) - 4;

        if (w1 <= 0 || h1 <= 0)
            return array;

        int[] translated = new int[n];
        int[] rotated = new int[n];

        //region translating
        for (i = 0, j = 0; i < w1; i++, j++)
            translated[j] = array[begin][begin + i];

        for (i = 1; i < h1; i++, j++)
            translated[j] = array[begin + i][begin + w1 - 1];

        for (i = 1; i < w1; i++, j++)
            translated[j] = array[begin + h1 - 1][begin + w1 - 1 - i];

        for (i = 1; i < h1 - 1; i++, j++)
            translated[j] = array[begin + h1 - 1 - i][begin];
        //endregion

        // region rotating
        times = times % n;
        for (i = 0; i < n; i++)
            rotated[i] = translated[(i + times) % n];
        // endregion

        //region translating back
        for (i = 0, j = 0; i < w1; i++, j++)
            array[begin][begin + i] = rotated[j];

        for (i = 1; i < h1; i++, j++)
            array[begin + i][begin + w1 - 1] = rotated[j];

        for (i = 1; i < w1; i++, j++)
            array[begin + h1 - 1][begin + w1 - 1 - i] = rotated[j];

        for (i = 1; i < h1 - 1; i++, j++)
            array[begin + h1 - 1 - i][begin] = rotated[j];

        //endregion
        return array;
    }

    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, h = scanner.nextInt(), w = scanner.nextInt(), times = scanner.nextInt(), min = Math.min(h, w);
            int[][] array = new int[h][];

            scanner.nextLine();

            for (i = 0; i < h; i++)
                array[i] = Arrays.stream(scanner.nextLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

            for (i = 0; 2 * i < min; i++)
                rotate(i, times, array);

            for (i = 0; i < h; i++)
                System.out.println(Arrays.stream(array[i]).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
