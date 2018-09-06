import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaBigDecimalTest {
    private final PrintStream printStreamOriginal = System.out;
    private final InputStream inputStreamOriginal = System.in;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(printStreamOriginal);
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_SortArray_When21() {
        String input = "2\n" +
                "1\n" +
                "2";
        String expected = "2\n" +
                "1\n";
        String actual;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try {
            System.setIn(inputStream);
            JavaBigDecimal.main();
            actual = outputStream.toString();
            assertEquals(expected, actual);
        } finally {
            System.setIn(inputStreamOriginal);
        }
    }

    @Test
    public void should_PreserveStringRepresentation_When02Dot00() {
        String input = "2\n" +
                "1\n" +
                "02.00";
        String expected = "02.00\n" +
                "1\n";
        String actual;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try {
            System.setIn(inputStream);
            JavaBigDecimal.main();
            actual = outputStream.toString();
            assertEquals(expected, actual);
        } finally {
            System.setIn(inputStreamOriginal);
        }
    }

    @Test
    public void should_PreserveInputOrder_When2Dot002Dot0() {
        String input = "3\n" +
                "1\n" +
                "2.00\n"+
                "2.0";
        String expected = "2.00\n" +
                "2.0\n"+
                "1\n";

        String actual;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try {
            System.setIn(inputStream);
            JavaBigDecimal.main();
            actual = outputStream.toString();
            assertEquals(expected, actual);
        } finally {
            System.setIn(inputStreamOriginal);
        }
    }
    @Test
    public void should_PreserveInputOrder_WhenMinus100_2Dot002Dot0() {
        String input = "4\n" +
                "-100\n"+
                "1\n" +
                "2.00\n"+
                "2.0";
        String expected = "2.00\n" +
                "2.0\n" +
                "1\n" +
                "-100\n";

        String actual;
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());

        try {
            System.setIn(inputStream);
            JavaBigDecimal.main();
            actual = outputStream.toString();
            assertEquals(expected, actual);
        } finally {
            System.setIn(inputStreamOriginal);
        }
    }
}
