public class PrimeChecker {
    public void checkPrime(int... numbers) {
        boolean isPrinted = false;

        for (int number :
                numbers) {

            if (printIfPrime(number, isPrinted))
                isPrinted = true;
        }
        System.out.println();
    }

    private boolean printIfPrime(int number, boolean isPrinted) {
        if (number < 2)
            return false;

        String space = isPrinted ? " " : "";
        if (number == 2) {
            printNumber(number, space);
            return true;
        }

        for (int i = 2; i <= number / 2; i++) {
            if (number % i == 0)
                return false;
        }

        printNumber(number, space);
        return true;
    }

    private void printNumber(int number, String space) {
        System.out.printf("%s%d", space, number);
    }
}
