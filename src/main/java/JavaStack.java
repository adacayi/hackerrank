import java.util.*;

public class JavaStack {
    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int i;
            String input;

            while (scanner.hasNextLine()) {
                input = scanner.nextLine();
                Map<Integer, Character> beginMap = new HashMap<>();
                Map<Character, Integer> endMap = new HashMap<>();
                beginMap.put(0, '(');
                beginMap.put(1, '{');
                beginMap.put(2, '[');
                endMap.put(')', 0);
                endMap.put('}', 1);
                endMap.put(']', 2);
                char[] array = input.toCharArray();
                Character read;
                Stack<Character> stack = new Stack<>();

                for (i = 0; i < array.length; i++) {
                    read = array[i];
                    if (beginMap.containsValue(read))
                        stack.push(read);
                    else if (endMap.containsKey(read)) {
                        if (stack.empty() || stack.pop() != beginMap.get(endMap.get(read)))
                            break;
                    } else
                        break;
                }

                if (i == array.length && stack.empty())
                    System.out.println("true");
                else
                    System.out.println("false");
            }
        }
    }
}
