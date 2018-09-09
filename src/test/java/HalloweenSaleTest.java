import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HalloweenSaleTest {
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
    public void should_Return3() {
        String input = "20 3 6 51";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        HalloweenSale.main();
        String expected = "3";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
    @Test
    public void should_Return6() {
        String input = "20 3 6 80";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        HalloweenSale.main();
        String expected = "6";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
