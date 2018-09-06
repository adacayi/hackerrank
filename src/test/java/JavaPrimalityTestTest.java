import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaPrimalityTestTest {
    private final PrintStream printStreamOriginal = System.out;
    private final InputStream inputStreamOriginal = System.in;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tear() {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.setOut(printStreamOriginal);
        }
    }

    @Test
    public void should_ReturnPrime_When_2() {
        String input = "2";
        String expected = "prime\n";
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            JavaPrimaryTest.main();
            String actual = outputStream.toString();
            assertEquals(expected, actual);
        } finally {
            System.setIn(inputStreamOriginal);
        }
    }

    @Test
    public void should_ReturnNotPrime_When_81() {
        String input = "81";
        String expected = "not prime\n";
        try {
            System.setIn(new ByteArrayInputStream(input.getBytes()));
            JavaPrimaryTest.main();
            String actual = outputStream.toString();
            assertEquals(expected, actual);
        } finally {
            System.setIn(inputStreamOriginal);
        }
    }
}

