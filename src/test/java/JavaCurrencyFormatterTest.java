import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class JavaCurrencyFormatterTest {
    JavaCurrencyFormatter formatter;

    @BeforeEach
    public void init() {
        formatter = new JavaCurrencyFormatter();
    }

    @Test
    public void should_ReturnUsCurrency() {
        double value = 100.246;
        String expected = "$100.25";
        String actual = formatter.format(value, Locale.US);
        assertEquals(expected, actual);
    }

    @Test
    public void should_ReturnIndianCurrency() {
        double value = 100.246;
        String expected = "Rs.100.25";
        String actual = formatter.format(value, new Locale.Builder().setLanguage("EN").setRegion("in").build());
        assertEquals(expected, actual);
    }
}
