import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JavaDateAndTimeTest {
    @Test
    public void shouldReturnMonday() {
        String day = "20";
        String month = "8";
        String year = "2018";
        String expected="MONDAY";
        String actual = new JavaDateAndTime().getDay(day,month,year);
        Assertions.assertEquals(expected, actual);
    }
    @Test
    public void shouldReturnSunday() {
        String day = "19";
        String month = "8";
        String year = "2018";
        String expected="SUNDAY";
        String actual = new JavaDateAndTime().getDay(day,month,year);
        Assertions.assertEquals(expected, actual);
    }
}
