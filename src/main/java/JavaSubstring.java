public class JavaSubstring {

    public static String getMinMax(String input, int length) {
        String min = "";
        String max = "";
        String subString = "";

        if (input.length() == 0 || length < 1 || length > input.length())
            return min + "\n" + max;

        min = input.substring(0, length);
        for (int i = 0; i <= input.length() - length; i++) {
            subString = input.substring(i, i + length);
            if (min.compareTo(subString) > 0)
                min = subString;
            if (max.compareTo(subString) < 0)
                max = subString;
        }

        return min + "\n" + max;
    }
}
