import java.util.*;

enum Color {
    RED, GREEN
}

abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);
}

class TreeNode extends Tree {

    private ArrayList<Tree> children = new ArrayList<>();

    public TreeNode(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitNode(this);

        for (Tree child : children) {
            child.accept(visitor);
        }
    }

    public void addChild(Tree child) {
        children.add(child);
    }

    public List<Tree> getChildren() {
        return children;
    }
}

class TreeLeaf extends Tree {

    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}

abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode node);

    public abstract void visitLeaf(TreeLeaf leaf);

}

class SumInLeavesVisitor extends TreeVis {
    private int result;

    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
    }

    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    public static final int MODULO = (int) (Math.pow(10, 9) + 7);
    private long result = 1;

    public int getResult() {
        return (int)result;
    }

    public void visitNode(TreeNode node) {
        int value = node.getValue();
        if (value == 0 || node.getColor() != Color.RED)
            return;
        result = result * value % MODULO;
    }

    public void visitLeaf(TreeLeaf leaf) {
        int value = leaf.getValue();

        if (value == 0 || leaf.getColor() != Color.RED)
            return;
        result = result * value % MODULO;
    }
}

class FancyVisitor extends TreeVis {
    private long sumOfValuesOfNonLeafNodesAtEvenDepth;
    private long sumOfValuesOfGreenLeafNodes;

    public int getResult() {
        return (int) Math.abs(sumOfValuesOfNonLeafNodesAtEvenDepth - sumOfValuesOfGreenLeafNodes);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 != 0)
            return;

        sumOfValuesOfNonLeafNodesAtEvenDepth += node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() != Color.GREEN)
            return;
        sumOfValuesOfGreenLeafNodes += leaf.getValue();
    }
}

public class JavaVisitorPattern {
    static int nodeCount;
    static int values[];
    static Color[] colors;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static final Color[] colorValues = Color.values();

    public static Tree solve() {
        try (Scanner scanner = new Scanner(System.in)) {
            nodeCount = scanner.nextInt();
            values = new int[nodeCount];

            for (int i = 0; i < nodeCount; i++)
                values[i] = scanner.nextInt();

            colors = new Color[nodeCount];

            for (int i = 0; i < nodeCount; i++)
                colors[i] = colorValues[scanner.nextInt()];

            if (nodeCount == 1)
                return new TreeLeaf(values[0], colors[0], 0);

            TreeNode result = new TreeNode(values[0], colors[0], 0);

            for (int i = 0; i < nodeCount - 1; i++) {
                Integer first = scanner.nextInt();
                Integer second = scanner.nextInt();
                List<Integer> children;

                if (!map.containsKey(first))
                    map.put(first, new ArrayList<Integer>());

                children = map.get(first);
                children.add(second);

                if (!map.containsKey(second))
                    map.put(second, new ArrayList<Integer>());

                children = map.get(second);
                children.add(first);
            }

            addChildren(result, 1);
            return result;
        }
    }

    private static void addChildren(TreeNode parent, Integer parentNumber) {
        int depth = parent.getDepth() + 1;
        for (Integer child : map.get(parentNumber)) {
            List<Integer> grandChildren = map.get(child);
            grandChildren.remove(parentNumber);

            if (grandChildren.size() == 0) {
                parent.addChild(new TreeLeaf(values[child - 1], colors[child - 1], depth));
                continue;
            }

            TreeNode childNode = new TreeNode(values[child - 1], colors[child - 1], depth);
            parent.addChild(childNode);
            addChildren(childNode, child);
        }
    }

    public static void main(String[] args) {
        Tree root = solve();
        SumInLeavesVisitor vis1 = new SumInLeavesVisitor();
        ProductOfRedNodesVisitor vis2 = new ProductOfRedNodesVisitor();
        FancyVisitor vis3 = new FancyVisitor();

        root.accept(vis1);
        root.accept(vis2);
        root.accept(vis3);

        int res1 = vis1.getResult();
        int res2 = vis2.getResult();
        int res3 = vis3.getResult();

        System.out.println(res1);
        System.out.println(res2);
        System.out.println(res3);
    }
}
