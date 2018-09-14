import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaSHA256Test {
    @Test
    public void should_ReturnHashed_When_HelloWorld()  {
        String expected = "872e4e50ce9990d8b041330c47c9ddd11bec6b503ae9386a99da8584e9bb12c4";
        String actual = JavaSHA256.hash("HelloWorld");
        Assertions.assertEquals(expected,actual);
    }
}
