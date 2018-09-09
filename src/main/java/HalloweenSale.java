import java.util.Scanner;

public class HalloweenSale {
    public static int sum(int p, int d, int n) {
        return (p + (p - d * (n - 1))) * n / 2;
    }

    public static int solve(int p, int d, int s) {
        return (int) ((p + d / 2. - Math.sqrt(Math.pow(p + d / 2., 2) - 2 * (d * s))) / d);
    }

    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int p, d, m, s, count;
            p = scanner.nextInt();
            d = scanner.nextInt();
            m = scanner.nextInt();
            s = scanner.nextInt();

            count = (p - m) / d + 1;

            int sum = sum(p, d, count);
            if (s < sum) {
                int firstPart = solve(p, d, s);
                System.out.println(firstPart);
            }
            else {
                int secondPart = (s - sum) / m;
                System.out.println(count + secondPart);
            }

        }
    }
}
