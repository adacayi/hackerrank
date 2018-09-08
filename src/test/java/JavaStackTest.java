import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class JavaStackTest {
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
    public void should_ReturnTrue_When_Empty() {
        String input = "\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaStack.main();
        String expected = "true";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnTrue_When_Paranthesis() {
        String input = "((()))";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaStack.main();
        String expected = "true";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFalse_When_OpeningParanthesisMore() {
        String input = "(((()))";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaStack.main();
        String expected = "false";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFalse_When_ClosingParanthesisMore() {
        String input = "((())))";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaStack.main();
        String expected = "false";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnFalse_When_ParanthesisClosingIsInDifferentOrder() {
        String input = "((())))(";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        JavaStack.main();
        String expected = "false";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
