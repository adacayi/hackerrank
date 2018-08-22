import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PrimeCheckerTest {
    private PrimeChecker checker;
    private ByteArrayOutputStream outStream;
    private final PrintStream originalOut = System.out;
    private static final String NEW_LINE = "\r\n";

    @BeforeEach
    public void init() {
        checker = new PrimeChecker();
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @Test
    public void should_Print2_When_2() {
        checker.checkPrime(2);
        String expected = "2" + NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void should_Print3_When_3() {
        checker.checkPrime(3);
        String expected = "3" + NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void should_PrintNone_When_1() {
        checker.checkPrime(1);
        String expected = NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void should_PrintNone_When_4() {
        checker.checkPrime(4);
        String expected = NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void should_Print5_When_5_6() {
        checker.checkPrime(5, 6);
        String expected = "5" + NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void should_Print5_When_7_10_19() {
        checker.checkPrime(7, 10,19);
        String expected = "7 19" + NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }   @Test
    public void should_Print5_When_2_3() {
        checker.checkPrime(2, 3);
        String expected = "2 3" + NEW_LINE;
        String actual = outStream.toString();
        Assertions.assertEquals(expected, actual);
    }
}
