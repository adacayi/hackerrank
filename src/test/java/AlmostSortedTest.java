import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlmostSortedTest {
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
    public void should_Return_No() {
        String input = "3\n" +
                "2 3 1";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "no";
        AlmostSorted.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_Yes() {
        String input = "3\n" +
                "1 3 5 9";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "yes";
        AlmostSorted.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_Swap_Adjacent() {
        String input = "3\n" +
                "1 3 9 5";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "yes\nswap 3 4";
        AlmostSorted.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
    @Test
    public void should_Return_Swap_Not_Adjacent() {
        String input = "8\n" +
                "1 3 9 6 7 5 11 12";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "yes\nswap 3 6";
        AlmostSorted.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_Reverse() {
        String input = "9\n" +
                "1 3 4 8 7 6 5 11 12";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "yes\nreverse 4 7";
        AlmostSorted.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

}
