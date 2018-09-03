import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaDequeueTest {
    @Test
    public void should_Return3() {
        int expected=3;
        int actual = JavaDequeue.find(new int[]{5, 3, 5, 2, 3, 2}, 3);
        Assertions.assertEquals(expected, actual);
    }
}
