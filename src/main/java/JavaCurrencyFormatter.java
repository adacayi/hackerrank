import java.text.NumberFormat;
import java.util.Locale;

public class JavaCurrencyFormatter {
    public String format(double value, Locale locale) {
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(locale);
        String formatted = currencyInstance.format(value);
        return formatted;
    }
}
