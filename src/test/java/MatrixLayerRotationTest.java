import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatrixLayerRotationTest {
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
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void should_Rotate_Once() {
        int[][] rotated = MatrixLayerRotation.rotate(0, 1, new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}});
        assertArrayEquals(new int[][]{{2, 3, 4, 5}, {1, 13, 14, 6}, {12, 16, 15, 7}, {11, 10, 9, 8}}, rotated);
        rotated = MatrixLayerRotation.rotate(1, 1, new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}});
        assertArrayEquals(new int[][]{{1, 2, 3, 4}, {12, 14, 15, 5}, {11, 13, 16, 6}, {10, 9, 8, 7}}, rotated);
    }

    @Test
    public void should_Rotate_Multiple() {
        int[][] rotated = MatrixLayerRotation.rotate(0, 12, new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}});
        assertArrayEquals(new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}}, rotated);
        rotated = MatrixLayerRotation.rotate(0, 22, new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}});
        assertArrayEquals(new int[][]{{11, 12, 1, 2}, {10, 13, 14, 3}, {9, 16, 15, 4}, {8, 7, 6, 5}}, rotated);
        rotated = MatrixLayerRotation.rotate(1, 7, new int[][]{{1, 2, 3, 4}, {12, 13, 14, 5}, {11, 16, 15, 6}, {10, 9, 8, 7}});
        assertArrayEquals(new int[][]{{1, 2, 3, 4}, {12, 16, 13, 5}, {11, 15, 14, 6}, {10, 9, 8, 7}}, rotated);
    }

    @Test
    public void should_Rotate_Matrix_Once() {
        String input = "4 4 1\n" +
                "1 2 3 4\n" +
                "12 13 14 5\n" +
                "11 16 15 6\n" +
                "10 9 8 7";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        MatrixLayerRotation.main();
        String expected = "2 3 4 5\r\n" +
                "1 14 15 6\r\n" +
                "12 13 16 7\r\n" +
                "11 10 9 8\r\n";
        String actual = outputStream.toString();
        assertEquals(expected, actual);
    }
}
