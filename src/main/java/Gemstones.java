import java.util.Scanner;

public class Gemstones {

    public static final int ALPHABET_LENGTH = 'z' - 'a' + 1;

    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i, j, n = scanner.nextInt(), count = 0;
            byte[] repeat = new byte[ALPHABET_LENGTH];
            byte[] repeatRow;
            scanner.nextLine();
            for (i = 0; i < n; i++) {
                repeatRow = new byte[ALPHABET_LENGTH];
                for (char c : scanner.nextLine().toCharArray())
                    repeatRow[c - 'a'] = 1;
                for (j = 0; j < ALPHABET_LENGTH; j++)
                    repeat[j] += repeatRow[j];
            }

            for (i = 0; i < ALPHABET_LENGTH; i++)
                if (repeat[i] == n)
                    count++;

            System.out.println(count);
        }
    }
}
