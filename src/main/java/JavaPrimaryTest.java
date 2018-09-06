import java.math.BigInteger;
import java.util.Scanner;

public class JavaPrimaryTest {
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            BigInteger number = new BigInteger(scanner.next());
            System.out.printf("%s\n", number.isProbablePrime(1) ? "prime" : "not prime");
        }
    }
}
