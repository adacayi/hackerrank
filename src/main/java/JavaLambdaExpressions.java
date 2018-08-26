interface PerformOperation {
    public boolean check(int i);
}

class MyMath {
    public PerformOperation isOdd() {
        return a -> a % 2 == 1;
    }

    public PerformOperation isPrime() {
        return a -> {
            if (a < 2)
                return false;
            if(a==2)
                return true;

            for (int i = 2; i < Math.sqrt(a) + 1; i++)
                if (a % i == 0)
                    return false;
            return true;
        };
    }

    public PerformOperation isPalindrome() {
        return a -> {
            char[] characters = String.valueOf(a).toCharArray();
            int length = characters.length;
            for (int i = 0; i < length / 2; i++)
                if (characters[i] != characters[length - 1 - i])
                    return false;
            return true;
        };
    }
}

public class JavaLambdaExpressions {

}
