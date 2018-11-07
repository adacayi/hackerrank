import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class TheLoveLetterMysteryTest {
    private static final PrintStream originalPrintStream = System.out;
    private static final InputStream originalInputStream = System.in;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @Before
    public void init() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @After
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
    public void should_Return_0_When_Palindrome() {
        String input = "5\n" +
                "\n" +
                "u\n" +
                "dd\n" +
                "wbw\n" +
                "teet\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        TheLoveLetterMystery.main();
        String expected = "0\n0\n0\n0\n0";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_2_When_Palindrome() {
        String input = "5\n" +
                "ac\n" +
                "ca\n" +
                "edg\n" +
                "lbj\n" +
                "acdb\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        TheLoveLetterMystery.main();
        String expected = "2\n2\n2\n2\n2";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
