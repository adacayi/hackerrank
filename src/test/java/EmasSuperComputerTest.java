import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmasSuperComputerTest {
    private static final PrintStream originalPrintStream = System.out;
    private static final InputStream originalInputStream = System.in;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalPrintStream);
        System.setIn(originalInputStream);

        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void should_Return_Grid_Array() {
        String input = "3 5\n" +
                "GGBBG\n" +
                "BGBGB\n" +
                "GGGBB\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        int[][] expected = {{1, 1, 0, 0, 1}, {0, 1, 0, 1, 0}, {1, 1, 1, 0, 0}};
        int[][] actual = EmasSuperComputer.getGrid();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_Return_Plus_Sizes() {
        String input = "3 5\n" +
                "GGBBG\n" +
                "GGGGB\n" +
                "GGGBB\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        int[][] expected = {{1, 1, 0, 0, 1}, {1, 3, 1, 1, 0}, {1, 1, 1, 0, 0}};
        int[][] grid = EmasSuperComputer.getGrid();
        int[][] actual = EmasSuperComputer.getPlusSizes(grid);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_Return_5() {
        String input = "3 5\n" +
                "GGBBG\n" +
                "GGGGB\n" +
                "GGGBB\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        int expected = 5;
        EmasSuperComputer.main();
        int actual = Integer.parseInt(outputStream.toString().trim());
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_81() {
        String input = "12 12\n" +
                "GGGGGGGGGGGG\n" +
                "GBGGBBBBBBBG\n" +
                "GBGGBBBBBBBG\n" +
                "GGGGGGGGGGGG\n" +
                "GGGGGGGGGGGG\n" +
                "GGGGGGGGGGGG\n" +
                "GGGGGGGGGGGG\n" +
                "GBGGBBBBBBBG\n" +
                "GBGGBBBBBBBG\n" +
                "GBGGBBBBBBBG\n" +
                "GGGGGGGGGGGG\n" +
                "GBGGBBBBBBBG\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        int expected = 81;
        EmasSuperComputer.main();
        int actual = Integer.parseInt(outputStream.toString().trim());
        assertEquals(expected, actual);
    }

    @Test
    public void should_Intersect() {
        assertTrue(EmasSuperComputer.intersects(5, 3, 2, 2, 2, 5));
        assertTrue(EmasSuperComputer.intersects(3, 5, 2, 5, 2, 2));

        assertTrue(EmasSuperComputer.intersects(5, 3, 2, 2, 5, 2));
        assertTrue(EmasSuperComputer.intersects(3, 5, 5, 2, 2, 2));

        assertTrue(EmasSuperComputer.intersects(5, 3, 2, 2, 1, 4));
        assertTrue(EmasSuperComputer.intersects(3, 5, 1, 4, 2, 2));
        assertTrue(EmasSuperComputer.intersects(5, 3, 2, 2, 3, 4));
        assertTrue(EmasSuperComputer.intersects(3, 5, 3, 4, 2, 2));

        assertTrue(EmasSuperComputer.intersects(5, 3, 3, 2, 1, 3));
        assertTrue(EmasSuperComputer.intersects(3, 5, 1, 3, 3, 2));
        assertTrue(EmasSuperComputer.intersects(5, 3, 3, 2, 5, 1));
        assertTrue(EmasSuperComputer.intersects(3, 5, 5, 1, 3, 2));

        assertTrue(EmasSuperComputer.intersects(3, 1, 3, 3, 2, 3));
        assertTrue(EmasSuperComputer.intersects(3, 1, 3, 3, 3, 2));

        assertTrue(EmasSuperComputer.intersects(1, 3, 2, 3, 3, 3));
        assertTrue(EmasSuperComputer.intersects(1, 3, 3, 2, 3, 3));
        assertTrue(EmasSuperComputer.intersects(1, 1, 1, 1, 1, 1));
    }

    @Test
    public void should_NotIntersect() {
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 3, 3));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 2, 4));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 1, 5));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 2, 6));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 3, 7));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 4, 8));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 5, 9));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 6, 8));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 6, 8));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 7, 7));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 8, 6));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 8, 6));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 9, 5));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 8, 4));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 7, 3));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 6, 2));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 5, 1));
        assertFalse(EmasSuperComputer.intersects(5, 3, 5, 5, 4, 2));

        assertFalse(EmasSuperComputer.intersects(3, 5, 3, 3,5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 2, 4, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 1, 5, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 2, 6, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 3, 7, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 4, 8, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 5, 9, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 6, 8, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 6, 8, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 7, 7, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 8, 6, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 8, 6, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 9, 5, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 8, 4, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 7, 3, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 6, 2, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 5, 1, 5, 5));
        assertFalse(EmasSuperComputer.intersects(3, 5, 4, 2, 5, 5));
    }

}
