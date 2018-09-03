import java.util.BitSet;
import java.util.Scanner;

public class JavaBitSet {
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a, b, n, m, i;
            String operation;
            n = scanner.nextInt();
            m = scanner.nextInt();
            BitSet[] sets = new BitSet[2];
            for (i = 0; i < 2; i++)
                sets[i] = new BitSet(n);

            for (i = 0; i < m; i++) {
                operation = scanner.next("[\\w]+");
                a = scanner.nextInt();
                b = scanner.nextInt();

                switch (operation) {
                    case "AND":
                        sets[a - 1].and(sets[b - 1]);
                        break;
                    case "OR":
                        sets[a - 1].or(sets[b - 1]);
                        break;
                    case "XOR":
                        sets[a - 1].xor(sets[b - 1]);
                        break;
                    case "FLIP":
                        sets[a - 1].flip(b);
                        break;
                    case "SET":
                        sets[a - 1].set(b);
                        break;
                }

                System.out.printf("%d %d\n", sets[0].cardinality(), sets[1].cardinality());
            }
        }
    }
}
