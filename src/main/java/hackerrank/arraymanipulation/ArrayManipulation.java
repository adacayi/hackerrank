package hackerrank.arraymanipulation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArrayManipulation {
    public static void main(String[] args) {
        // Specify the file path
        String filePath = "src/main/java/hackerrank/arraymanipulation/input07.txt";

        Path path = Paths.get(filePath);

        try {
            List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);

            // Print each line
            var n = Integer.parseInt(lines.get(0).split(" ")[0]);
            var queries = new ArrayList<List<Integer>>();

            for (int i = 1; i < lines.size(); i++) {
                var query = new ArrayList<Integer>();
                for (var parameter : lines.get(i).split(" ")) {
                    query.add(Integer.parseInt(parameter));
                }
                queries.add(query);
            }
            var start = LocalDateTime.now();
            System.out.println(arrayManipulation(n, queries));
            var duration = Duration.between(start, LocalDateTime.now());
            System.out.println("Duration " + duration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static long arrayManipulation(int n, List<List<Integer>> queries) {
        // Write your code here
        var arr = new long[n];
        long max = 0;
        int size = queries.size();

        for (int i = 0; i < size; i++) {
            max = process(arr, queries.get(i), max);
        }

        return max;
    }

    public static Long process(long[] arr, List<Integer> query, long max) {
        int a = query.get(0);
        int b = query.get(1);
        int k = query.get(2);

        for (int i = a - 1; i < b; i++) {
            long value = arr[i] + k;
            arr[i] = value;

            if (value > max) {
                max = value;
            }
        }

        return max;
    }
}
