import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class JavaList {

    public static void main(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int a, b, i, l = scanner.nextInt(), q;
            List<Integer> list = new ArrayList<>(l);
            String command;

            for (i = 0; i < l; i++)
                list.add(scanner.nextInt());

            q = scanner.nextInt();

            for (i = 0; i < q; i++) {
                command = scanner.next();
                a = scanner.nextInt();
                switch (command) {
                    case "Insert":
                        b = scanner.nextInt();
                        list.add(a, b);
                        break;
                    case "Delete":
                        list.remove(a);
                        break;
                    default:
                }
            }

            System.out.printf("%s\n",list.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
