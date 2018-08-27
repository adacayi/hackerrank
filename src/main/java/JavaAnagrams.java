public class JavaAnagrams {

    public static boolean isAnagram(String first, String second) {
        short[] countFirst = new short[256];
        short[] countSecond = new short[256];

        if (first == null && second == null)
            return true;

        if (first == null || second == null)
            return false;

        if (first.length() != second.length())
            return false;

        char[] firstArray = first.toCharArray();
        char[] secondArray = second.toCharArray();

        getFrequency(firstArray, countFirst);
        getFrequency(secondArray, countSecond);

        for (int i = 65; i <= 89; i++)
            if (countFirst[i] != countSecond[i])
                return false;
        return true;
    }

    private static void getFrequency(char[] array, short[] count) {
        for (char c : array) {
            count[Character.toUpperCase(c)]++;
        }
    }
}
