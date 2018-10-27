import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class LarrysArrayTest {
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
    public void should_MoveLeft_1() {
        int[] a = {2, 3, 5, 4};
        assertTrue(LarrysArray.moveLeft(a, 2, 1));
        assertArrayEquals(new int[]{2, 5, 4, 3}, a);

        a = new int[]{2, 5, 4, 3};
        assertTrue(LarrysArray.moveLeft(a, 1, 1));
        assertArrayEquals(new int[]{5, 4, 2, 3}, a);
    }

    @Test
    public void should_Not_MoveLeft_1() {
        int[] a = {2, 3, 5, 4};
        assertFalse(LarrysArray.moveLeft(a, 3, 1));

        a = new int[]{2, 5, 4, 3};
        assertFalse(LarrysArray.moveLeft(a, 0, 1));
    }

    @Test
    public void should_MoveLeft_2() {
        int[] a = {2, 3, 5, 4};
        assertTrue(LarrysArray.moveLeft(a, 2, 2));
        assertArrayEquals(new int[]{5, 2, 3, 4}, a);

        a = new int[]{2, 5, 4, 3};
        assertTrue(LarrysArray.moveLeft(a, 3, 2));
        assertArrayEquals(new int[]{2, 3, 5, 4}, a);
    }

    @Test
    public void should_NotMoveLeft_2() {
        int[] a = {2, 3, 5, 4};
        assertFalse(LarrysArray.moveLeft(a, 1, 2));
    }

    @Test
    public void should_MoveLeft_Multiple() {
        int[] a = {2, 3, 5, 4};
        assertTrue(LarrysArray.moveLeft(a, 3, 3));
        assertArrayEquals(new int[]{4, 3, 2, 5}, a);
    }

    @Test
    public void should_Sort() {
        String input="1\n" +
                "5\n" +
                "2 3 4 5 1\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        LarrysArray.main();
        String expected="YES";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
    @Test
    public void should_NotSort() {
        String input="1\n" +
                "5\n" +
                "2 3 5 4 1\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        LarrysArray.main();
        String expected="NO";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_Sort_For_Multiple() {
        String input="3\n" +
                "3\n" +
                "3 1 2\n" +
                "4\n" +
                "1 3 4 2\n" +
                "5\n" +
                "1 2 3 5 4";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        LarrysArray.main();
        String expected="YES\r\n" +
                "YES\r\n" +
                "NO";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }    @Test
    public void should_Return_Sort_For_Multiple_With_Multiple_Nos() {
        String input="2\n" +
                "6\n" +
                "1 6 5 2 3 4\n" +
                "4\n" +
                "7 11 8 13";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        LarrysArray.main();
        String expected="NO\r\n" +
                "NO";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
