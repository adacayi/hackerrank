import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JavaBitSetTest {
    private ByteArrayOutputStream outputStream;
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.setOut(originalOut);
        }
    }

    @Test
    public void should_Return_10_When_Flip12() {
        InputStream stream = new ByteArrayInputStream("5 1\nFLIP 1 2".getBytes());
        System.setIn(stream);
        JavaBitSet.main();
        String expected = "1 0\n";
        String actual = outputStream.toString();
        assertEquals(expected, actual);
    }
    @Test
    public void should_Return_1000_When_Flip12And12() {
        InputStream stream = new ByteArrayInputStream("5 2\nFLIP 1 2\nAND 1 2".getBytes());
        System.setIn(stream);
        JavaBitSet.main();
        String expected = "1 0\n0 0\n";
        String actual = outputStream.toString();
        assertEquals(expected, actual);
    }
}
