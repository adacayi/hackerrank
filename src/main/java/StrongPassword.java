import java.util.Scanner;

public class StrongPassword {
    public static void main(String... args) {
        int needed = 0;
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextLine();
            input = scanner.nextLine();

            if (!containsDigit(input))
                needed++;

            if (!containsLowerCharacter(input))
                needed++;

            if (!containsUpperCharacter(input))
                needed++;

            if (!containsSpecialCharacter(input))
                needed++;
        }

        if (needed + input.length() < 6)
            needed = 6 - input.length();

        System.out.println(needed);
    }

    public static boolean containsDigit(String input) {
        return input.matches(".*\\d.*");
    }

    public static boolean containsLowerCharacter(String input) {
        return input.matches(".*[a-z].*");
    }

    public static boolean containsUpperCharacter(String input) {
        return input.matches(".*[A-Z].*");
    }

    public static boolean containsSpecialCharacter(String input) {
        return input.matches(".*[!@#$%^&*()+\\-].*");
    }
}
