import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class JavaMD5Test {
    @Test
    public void should_ReturnHashed_When_HelloWorld() throws NoSuchAlgorithmException {
        String expected = "68e109f0f40ca72a15e05cc22786f8e6";
        String actual = JavaMD5.hash("HelloWorld");
        Assertions.assertEquals(expected,actual);
    }
}
