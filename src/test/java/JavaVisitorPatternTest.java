import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class JavaVisitorPatternTest {
    @Test
    public void should_ReturnTreeLeafWithValue1ColorGreen() {
        int value = 5;
        Color color = Color.GREEN;
        ByteArrayInputStream input = new ByteArrayInputStream(String.format("1\n%d\n%d", value, color.ordinal()).getBytes());
        System.setIn(input);
        Tree result = JavaVisitorPattern.solve();
        assertNotNull(result, "Should return TreeLeaf instead of null");
        assertTrue(result instanceof TreeLeaf,
                String.format("Result should be a TreeLeaf. Instead it is %s", result.getClass()));
        TreeLeaf leaf = (TreeLeaf) result;
        assertEquals(value, leaf.getValue(), String.format("Value should be equal to %d", value));
        assertEquals(color, leaf.getColor(), String.format("Color should be equal to %s", color));
        assertEquals(0, leaf.getDepth(), "Depth should be equal to 0");

    }

    @Test
    public void should_ReturnTreeNodeWithDepth1() {
        int values[] = {5, 3};
        Color[] colors = {Color.GREEN, Color.RED};
        ByteArrayInputStream input = new ByteArrayInputStream(
                String.format("2\n%s\n%s\n1 2", joinValues(values), joinValues(colors)).getBytes());
        System.setIn(input);
        Tree result = JavaVisitorPattern.solve();
        assertNotNull(result, "Should return TreeNode instead of null");
        assertTrue(result instanceof TreeNode,
                String.format("Result should be a TreeNode. Instead it is %s", result.getClass()));
        TreeNode node = (TreeNode) result;
        assertEquals(values[0], node.getValue(), String.format("Value should be equal to %d", values[0]));
        assertEquals(colors[0], node.getColor(), String.format("Color should be equal to %s", colors[0]));
        assertEquals(0, node.getDepth(), "Root depth should be equal to 0");
        List<Tree> children = node.getChildren();
        assertNotNull(children, "Tree should have more than one node");
        assertEquals(1, children.size(), "Tree should have only two nodes");
        assertTrue(children.get(0) instanceof TreeLeaf, "Tree should have only two nodes");
        TreeLeaf leaf = (TreeLeaf) children.get(0);
        assertEquals(values[1], leaf.getValue(), String.format("Value should be equal to %d", values[1]));
        assertEquals(colors[1], leaf.getColor(), String.format("Color should be equal to %s", colors[1]));
        assertEquals(1, leaf.getDepth(), "Root depth should be equal to 1");
    }

    private String joinValues(int[] valueArray) {
        return Arrays.stream(valueArray).mapToObj(Integer::toString).
                collect(Collectors.joining(" "));
    }

    private String joinValues(Color[] valueArray) {
        return Arrays.stream(valueArray).map(x -> Integer.toString(x.ordinal())).
                collect(Collectors.joining(" "));
    }
}
