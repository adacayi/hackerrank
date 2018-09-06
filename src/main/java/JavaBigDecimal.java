import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class JavaBigDecimal {
    static class BigDecimalRepresenter {
        private BigDecimal decimal;
        private String string;

        public BigDecimal getDecimal() {
            return decimal;
        }

        public String getString() {
            return string;
        }

        public BigDecimalRepresenter(String string) {
            this.decimal = new BigDecimal(string);
            this.string = string;
        }
    }

    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Scanner sc = new Scanner(System.in);
            int n = sc.nextInt();
            String[] s = new String[n + 2];
            for (int i = 0; i < n; i++) {
                s[i] = sc.next();
            }
            sc.close();

            Arrays.sort(s, 0, s.length - 2, Comparator.comparing((String s1) -> new BigDecimal(s1)).reversed());
            for (int i = 0; i < n; i++)
                System.out.printf("%s\n", s[i]);
        }
    }
}
