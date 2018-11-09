import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DeterminingDNAHealthTest {
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
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void should_Return_19_When_Find_Health() {
        long expected = 19;
        DeterminingDNAHealth.Gene[] genes = DeterminingDNAHealth.getGenes(
                new String[]{"a", "b", "c", "aa", "d", "b"}, new int[]{1, 2, 3, 4, 5, 6});
        long actual = DeterminingDNAHealth.findHealth(1, 5, "caaab",
                genes);
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_0_19_When_Main() {
        String input = "6\n" +
                "a b c aa d b\n" +
                "1 2 3 4 5 6\n" +
                "3\n" +
                "1 5 caaab\n" +
                "0 4 xyz\n" +
                "2 4 bcdybc";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        DeterminingDNAHealth.main();
        String expected = "0 19";
        String actual = outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_1823728075410_1823728075410() throws IOException, URISyntaxException {
        Path inputPath = Paths.get(this.getClass().getResource("DeterminingDNAHealthInput.txt").toURI());
        try (InputStream in = Files.newInputStream(inputPath)) {
            System.setIn(in);
            DeterminingDNAHealth.main();
            String expected = "1823728075410 1823728075410";
            String actual = outputStream.toString().trim();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void should_Return_3218660_11137051() throws IOException, URISyntaxException {
        Path inputPath = Paths.get(this.getClass().getResource("DeterminingDNAHealthInput2.txt").toURI());
        try (InputStream in = Files.newInputStream(inputPath)) {
            System.setIn(in);
            DeterminingDNAHealth.main();
            String expected = "3218660 11137051";
            String actual = outputStream.toString().trim();
            assertEquals(expected, actual);
        }
    }

    @Test
    public void should_Get_Sorted_Genes() {
        String[] geneStrings = {"a", "b", "c", "aa", "d", "b"};
        int[] healths = {1, 2, 3, 4, 5, 6};
        DeterminingDNAHealth.Gene[] expected = {
                new DeterminingDNAHealth.Gene("a", 1, false, 0)
                , new DeterminingDNAHealth.Gene("aa", 4, false, 3)
                , new DeterminingDNAHealth.Gene("b", 2, true, 1)
                , new DeterminingDNAHealth.Gene("b", 6, false, 5)
                , new DeterminingDNAHealth.Gene("c", 3, false, 2)
                , new DeterminingDNAHealth.Gene("d", 5, false, 4)
        };
        DeterminingDNAHealth.Gene[] actual = DeterminingDNAHealth.getGenes(geneStrings, healths);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void should_Return_Same_HashCode() {
        String[] first = {"Ahmet", "Mustafa", "Muhammed"}, second = {"Ahmet", "Mustafa", "Muhammed"};
        assertEquals(DeterminingDNAHealth.arrayHashCode(first), DeterminingDNAHealth.arrayHashCode(second));
    }
}
