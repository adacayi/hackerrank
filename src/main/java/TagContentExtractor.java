import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagContentExtractor {
    public static String[] extractContent(String line) {
        List<String> results = new ArrayList<>();
        Pattern pattern = Pattern.compile("<\\s*(.+?)\\s*>\\s*([^<]+?)\\s*<\\s*/\\s*\\1\\s*>");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            results.add(matcher.group(2));
        }

        return results.toArray(new String[0]);
    }
}
