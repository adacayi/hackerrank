import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _3DSurfaceAreaTest {
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
    public void should_Return_Independent_Cubes_When_Not_Intersecting() {
        String input = "4 4\n" +
                "5 0 0 3\n" +
                "0 7 0 0\n" +
                "2 0 0 0\n" +
                "0 3 0 11\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "136";
        _3DSurfaceArea.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_Area() {
        String input = "4 4\n" +
                "5 2 0 3\n" +
                "0 7 0 0\n" +
                "2 0 0 12\n" +
                "0 3 0 11\n";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        String expected = "166";
        _3DSurfaceArea.main();
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
