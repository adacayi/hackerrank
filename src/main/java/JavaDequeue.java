import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class JavaDequeue {
    public static int find(int[] ints, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> deque = new ArrayDeque<>();
        int n = ints.length, i, max, count, removed;
        for (i = 0; i < m; i++) {
            addToMap(ints, map, deque, i);
        }
        max = map.size();
        for (; i < n; i++) {
            removed = deque.removeFirst();
            count = map.get(removed);
            if (count == 1)
                map.remove(removed);
            else
                map.put(removed, count - 1);

            addToMap(ints, map, deque, i);

            if (max < map.size())
                max = map.size();
        }
        return max;
    }

    private static void addToMap(int[] ints, Map<Integer, Integer> map, Deque<Integer> deque, int i) {
        int added;
        int count;
        added=ints[i];
        deque.add(added);
        count = 1;
        if (map.containsKey(added))
            count = map.get(added) + 1;
        map.put(added, count);
    }
}
