import java.lang.reflect.Field;
import java.util.*;

public class DeterminingDNAHealth {
    public static Gene[] getGenes(String[] geneStrings, int[] healths) {
        int i, n = geneStrings.length;
        Gene[] result = new Gene[n];
        for (i = 0; i < n; i++)
            result[i] = new Gene(geneStrings[i], healths[i], false, i);
        Arrays.sort(result, Comparator.nullsFirst(Comparator.comparing(Gene::getNameString)).thenComparing(Gene::getIndex));
        for (i = 0; i < n - 1; i++)
            if (Arrays.equals(result[i].name, result[i + 1].name))
                result[i].isNextSame = true;

        return result;
    }

    public static class Gene {
        private String nameString;
        private char[] name;
        private int health;
        private boolean isNextSame;
        private int index;

        public String getNameString() {
            return nameString;
        }

        public char[] getName() {
            return name;
        }

        public int getHealth() {
            return health;
        }

        public boolean isNextSame() {
            return isNextSame;
        }

        public int getIndex() {
            return index;
        }

        public void setNextSame(boolean nextSame) {
            isNextSame = nextSame;
        }

        public Gene(String nameString, int health, boolean isNextSame, int index) {
            this.name = nameString.toCharArray();
            this.nameString = nameString;
            this.health = health;
            this.isNextSame = isNextSame;
            this.index = index;
        }

        @Override
        public int hashCode() {
            Field[] fields = this.getClass().getDeclaredFields();
            int i, n = fields.length;
            Object[] values = new Object[n];
            Object obj;
            for (i = 0; i < n; i++) {
                try {
                    obj = fields[i].get(this);
                    if (fields[i].getType().isArray()) {
                        values[i] = arrayHashCode(obj);
                    } else
                        values[i] = obj;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return Arrays.hashCode(values);
        }

        @Override
        public boolean equals(Object obj) {
            if (!this.getClass().isInstance(obj))
                return false;

            Field[] fields = this.getClass().getDeclaredFields();
            Object first, second;
            for (Field field : fields) {
                try {
                    first = field.get(this);
                    second = field.get(obj);
                    if (field.getType().isArray()) {
                        if (!Objects.deepEquals(first, second))
                            return false;
                    } else if (!Objects.equals(first, second))
                        return false;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    }

    public static int arrayHashCode(Object obj) {
        if (obj instanceof Object[])
            return Arrays.deepHashCode((Object[]) obj);
        if (obj instanceof byte[])
            return Arrays.hashCode((byte[]) obj);
        if (obj instanceof short[])
            return Arrays.hashCode((short[]) obj);
        if (obj instanceof int[])
            return Arrays.hashCode((int[]) obj);
        if (obj instanceof long[])
            return Arrays.hashCode((long[]) obj);
        if (obj instanceof char[])
            return Arrays.hashCode((char[]) obj);
        if (obj instanceof float[])
            return Arrays.hashCode((float[]) obj);
        if (obj instanceof double[])
            return Arrays.hashCode((double[]) obj);
        return Arrays.hashCode((boolean[]) obj);
    }

    public static void main(String... args) {
        int i, m, start, end;
        long min = Long.MAX_VALUE, max = 0, current;
        String strand;
        Gene[] genes;
        String[] geneStrings;
        int[] healths;

        try (Scanner scanner = new Scanner(System.in)) {
            scanner.nextInt();
            scanner.nextLine();
            geneStrings = scanner.nextLine().split(" ");
            healths = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            m = scanner.nextInt();
            genes = getGenes(geneStrings, healths);

            for (i = 0; i < m; i++) {
                start = scanner.nextInt();
                end = scanner.nextInt();
                strand = scanner.next();
                current = findHealth(start, end, strand, genes);
                min = Math.min(min, current);
                max = Math.max(max, current);
            }
            System.out.printf("%d %d", min, max);
        }
    }

    public static long findHealth(int start, int end, String strand, Gene[] genes) {
        long value = 0;
        int i, j, k, l = strand.length(), n = genes.length, index;
        char[] strandArray = strand.toCharArray();
        char first;
        Gene gene;
        for (i = 0; i < l; i++) {
            first = strandArray[i];
            index = binarySearch(genes, 0, n, first);
            if (index < 0)
                continue;
            for (j = index; j < n && genes[j].name[0] == first; j++) {
                gene = genes[j];

                if (start > gene.index || end < gene.index)
                    continue;

                for (k = 0; k < gene.name.length && i + k < l && strandArray[i + k] == gene.name[k]; k++) ;

                if (k != gene.name.length) {
                    for (; j < n && genes[j].isNextSame; j++) ;
                    if (i + k < l && strandArray[i + k] < gene.name[k])
                        break;
                    continue;
                }

                do {
                    value += genes[j].health;
                    j++;
                } while (j < n && genes[j - 1].isNextSame);
                j--;
            }
        }

        return value;
    }

    private static int binarySearch(Gene[] genes, int fromIndex, int toIndex, char key) {
        int low = fromIndex, high = toIndex - 1, mid, result = -1;
        char midVal;
        while (low <= high) {
            mid = (low + high) >>> 1;
            midVal = genes[mid].name[0];

            if (midVal < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
                if (midVal == key)
                    result = mid;
            }
        }

        if (result > -1)
            return result;

        return ~low;
    }
}