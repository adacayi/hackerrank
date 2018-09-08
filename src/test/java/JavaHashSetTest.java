import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaHashSetTest {
    private static final PrintStream printStreamOriginal = System.out;
    private static final InputStream inputStreamOriginal = System.in;
    private ByteArrayOutputStream outputStream;
    private InputStream inputStream;

    @BeforeEach
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(printStreamOriginal);
        System.setIn(inputStreamOriginal);
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (inputStream != null)
                inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_Return12233_WhenTwoRepeatingAdded() {
        String input = "5\n" +
                "john tom\n" +
                "john mary\n" +
                "john tom\n" +
                "mary anna\n" +
                "mary anna";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaHashSet.main();
        String expected = "1\n" +
                "2\n" +
                "2\n" +
                "3\n" +
                "3\n";
        String actual = outputStream.toString();
        assertEquals(expected, actual);
    }
}
