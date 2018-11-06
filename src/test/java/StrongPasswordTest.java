import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StrongPasswordTest {
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
    public void should_Return_True_When_Contains_Digit() {
        assertTrue(StrongPassword.containsDigit("1asdf"));
        assertTrue(StrongPassword.containsDigit("a1sdf"));
        assertTrue(StrongPassword.containsDigit("a1"));
        assertTrue(StrongPassword.containsDigit("1"));
    }

    @Test
    public void should_Return_False_When_Not_Contains_Digit() {
        assertFalse(StrongPassword.containsDigit("asdf"));
        assertFalse(StrongPassword.containsDigit(".*.*.asdf"));
        assertFalse(StrongPassword.containsDigit("a."));
        assertFalse(StrongPassword.containsDigit("*"));
    }

    @Test
    public void should_Return_True_When_Contains_Lower_Character() {
        assertTrue(StrongPassword.containsLowerCharacter("1asAf"));
        assertTrue(StrongPassword.containsLowerCharacter("a1sdf"));
        assertTrue(StrongPassword.containsLowerCharacter("a1"));
    }

    @Test
    public void should_Return_False_When_Not_Contains_Lower_Character() {
        assertFalse(StrongPassword.containsLowerCharacter("1ASAF"));
        assertFalse(StrongPassword.containsLowerCharacter("A1FDS"));
        assertFalse(StrongPassword.containsLowerCharacter(""));
    }

    @Test
    public void should_Return_True_When_Contains_Upper_Character() {
        assertTrue(StrongPassword.containsUpperCharacter("1asAf"));
        assertTrue(StrongPassword.containsUpperCharacter("A1sdf"));
        assertTrue(StrongPassword.containsUpperCharacter("A1"));
    }

    @Test
    public void should_Return_False_When_Not_Contains_Upper_Character() {
        assertFalse(StrongPassword.containsUpperCharacter("1asd"));
        assertFalse(StrongPassword.containsUpperCharacter("a1d"));
        assertFalse(StrongPassword.containsUpperCharacter(""));
    }

    @Test
    public void should_Return_True_When_Contains_Special_Character() {
        assertTrue(StrongPassword.containsSpecialCharacter("1a!sAf"));
        assertTrue(StrongPassword.containsSpecialCharacter("A@1sdf"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1#"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1$"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1%"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1^"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1&"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1*"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1("));
        assertTrue(StrongPassword.containsSpecialCharacter("A1)"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1-"));
        assertTrue(StrongPassword.containsSpecialCharacter("A1+"));
    }

    @Test
    public void should_Return_False_When_Not_Contains_Special_Character() {
        assertFalse(StrongPassword.containsSpecialCharacter("1asd"));
        assertFalse(StrongPassword.containsSpecialCharacter("a1d"));
        assertFalse(StrongPassword.containsSpecialCharacter(""));
    }

    @Test
    public void should_Return_5_When_1_Character() {
        String input = "1\n" +
                "d";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        StrongPassword.main();
        String expected="5";
        String actual=outputStream.toString().trim();
        assertEquals(expected, actual);
    }

    @Test
    public void should_Return_3() {
        String input = "3\n" +
                "1av";
        inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
        StrongPassword.main();
        String expected="3";
        String actual=outputStream.toString().trim();
        assertEquals(expected, actual);
    }
}
