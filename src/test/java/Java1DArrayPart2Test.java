import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Java1DArrayPart2Test {
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
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void should_ReturnYes_When_00100111() {
        String input = "1\n" +
                "8 4\n" +
                "0 0 1 0 0 1 1 1";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Java1DArrayPart2.main();
        String expected = "YES";
        String actual = outputStream.toString();
        actual = actual.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnNo_When_001001111() {
        String input = "1\n" +
                "9 4\n" +
                "0 0 1 0 0 1 1 1 1";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Java1DArrayPart2.main();
        String expected = "NO";
        String actual = outputStream.toString();
        actual = actual.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnYes_When_001001110() {
        String input = "1\n" +
                "9 4\n" +
                "0 0 1 0 0 1 1 1 0";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Java1DArrayPart2.main();
        String expected = "YES";
        String actual = outputStream.toString();
        actual = actual.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnYes_When_001001101() {
        String input = "1\n" +
                "9 4\n" +
                "0 0 1 0 0 1 1 0 1";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Java1DArrayPart2.main();
        String expected = "YES";
        String actual = outputStream.toString();
        actual = actual.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnYes_When_HackerRankCase11() {
        String input = "1\n" +
                "70 82\n" +
                "0 1 0 0 0 1 0 0 0 0 0 1 0 1 0 1 0 0 0 1 1 1 0 1 0 0 0 0 0 0 0 1 0 0 0 0 1 0 0 1 0 1 1 0 0 1 1 0 0 0 1 1 1 0 0 1 0 1 1 1 1 1 0 1 1 0 1 0 0 0";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        Java1DArrayPart2.main();
        String expected = "YES";
        String actual = outputStream.toString();
        actual = actual.trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnYes_When_HackerRankValues() throws IOException, URISyntaxException {
        Path inputPath = Paths.get(this.getClass().getResource("Java1DInput.txt").toURI());
        Path resultPath = Paths.get(this.getClass().getResource("Java1DExpected.txt").toURI());
        inputStream = Files.newInputStream(inputPath);
        System.setIn(this.inputStream);
        Java1DArrayPart2.main();
        try (Scanner scanner = new Scanner(outputStream.toString())) {
            int i = 0;
            for (String line : Files.readAllLines(resultPath)) {
                i++;
                assertEquals(line, scanner.nextLine(), String.format("There is a difference in the %d line", i));
            }
        }
    }
}
