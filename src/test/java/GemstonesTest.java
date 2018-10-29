import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GemstonesTest {
    private static final PrintStream originalPrintStream=System.out;
    private static final InputStream originalInputStream=System.in;
    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;

    @BeforeEach
    public void init() {
        outputStream=new ByteArrayOutputStream();
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

        if(inputStream!=null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void should_Return_2() {
        String input = "3\n" +
                "dkeds\n" +
                "qjikde\n" +
                "oske\n";
        inputStream=new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Gemstones.main();
        assertEquals("2", outputStream.toString().trim());
    }
}
