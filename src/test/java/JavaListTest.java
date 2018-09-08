import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaListTest {
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
    public void should_InitializeList() {
        String list = "3 2 1 5";
        String input = "4\n" +
                list + "\n" +
                "0";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaList.main();
        String expected = list;
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
    @Test
    public void should_Adding() {
        String list = "3 2 1 5";
        String input = "4\n" +
                list + "\n" +
                "1\n" +
                "Insert\n" +
                "2 7";

        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaList.main();
        String expected = "3 2 7 1 5";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }    @Test
    public void should_Remove() {
        String list = "3 2 1 5";
        String input = "4\n" +
                list + "\n" +
                "1\n" +
                "Delete\n" +
                "1";

        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaList.main();
        String expected = "3 1 5";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
