import java.util.Scanner;

public class TheLoveLetterMystery {
    public static void main(String... args) {
        int i, j, l, n, count;
        String text;
        char[] textArray;
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
            scanner.nextLine();
            for (i = 0; i < n; i++) {
                text = scanner.nextLine();
                count = 0;
                textArray = text.toCharArray();
                l = textArray.length;
                for (j = 0; j < textArray.length / 2; j++)
                    count += Math.abs(textArray[j] - textArray[l - j - 1]);
                System.out.printf("%d\n", count);
            }
        }
    }
}
