import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaSubstringTest {
    @Test
    public void should_Returnabbc_When_abcAndLength2() {
        String expected="ab\nbc";
        String actual = JavaSubstring.getMinMax("abc", 2);
        Assertions.assertEquals(expected,actual);
    }
}
